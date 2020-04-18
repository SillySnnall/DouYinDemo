package com.example.douyindemo.fragment

import android.view.SurfaceHolder
import android.view.SurfaceView
import com.aliyun.player.AliPlayerFactory
import com.aliyun.player.IPlayer.*
import com.aliyun.player.bean.ErrorInfo
import com.aliyun.player.bean.InfoBean
import com.aliyun.player.nativeclass.TrackInfo
import com.aliyun.player.source.UrlSource
import com.example.douyindemo.R
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : BaseFragment() {

    override fun setLayoutView(): Int {
        return R.layout.fragment_video
    }

    override fun initView() {}
    override fun initData() {
        val aliyunVodPlayer = AliPlayerFactory.createAliPlayer(activity)


        aliyunVodPlayer.setOnCompletionListener(OnCompletionListener {
            //播放完成事件
        })
        aliyunVodPlayer.setOnErrorListener(OnErrorListener {
            //出错事件
        })
        aliyunVodPlayer.setOnPreparedListener(OnPreparedListener {
            //准备成功事件
        })
        aliyunVodPlayer.setOnVideoSizeChangedListener(OnVideoSizeChangedListener { width, height ->
            //视频分辨率变化回调
        })
        aliyunVodPlayer.setOnRenderingStartListener(OnRenderingStartListener {
            //首帧渲染显示事件
        })
        aliyunVodPlayer.setOnInfoListener(object : OnInfoListener {
            override fun onInfo(p0: InfoBean?) {
                //其他信息的事件，type包括了：循环播放开始，缓冲位置，当前播放位置，自动播放开始等
            }
        })
        aliyunVodPlayer.setOnLoadingStatusListener(object : OnLoadingStatusListener {
            override fun onLoadingBegin() { //缓冲开始。
            }

            override fun onLoadingProgress(percent: Int, kbps: Float) { //缓冲进度
            }

            override fun onLoadingEnd() { //缓冲结束
            }
        })
        aliyunVodPlayer.setOnSeekCompleteListener(OnSeekCompleteListener {
            //拖动结束
        })
        aliyunVodPlayer.setOnSubtitleDisplayListener(object : OnSubtitleDisplayListener {
            override fun onSubtitleShow(id: Long, data: String) { //显示字幕
            }

            override fun onSubtitleHide(id: Long) { //隐藏字幕
            }
        })
        aliyunVodPlayer.setOnTrackChangedListener(object : OnTrackChangedListener {
            override fun onChangedSuccess(trackInfo: TrackInfo?) { //切换音视频流或者清晰度成功
            }

            override fun onChangedFail(
                trackInfo: TrackInfo?,
                errorInfo: ErrorInfo?
            ) { //切换音视频流或者清晰度失败
            }
        })
        aliyunVodPlayer.setOnStateChangedListener(OnStateChangedListener {
            //播放器状态改变事件
        })
        aliyunVodPlayer.setOnSnapShotListener(OnSnapShotListener { bm, with, height ->
            //截图事件
        })


        val urlSource = UrlSource()
        urlSource.uri =
            "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"

        //设置播放源
        aliyunVodPlayer.setDataSource(urlSource);
        //准备播放
        aliyunVodPlayer.prepare();



        surfaceview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                aliyunVodPlayer.setDisplay(holder)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                aliyunVodPlayer.redraw()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                aliyunVodPlayer.setDisplay(null)
            }
        })
        // 开始播放。
        aliyunVodPlayer.start()
    }

    override fun initEvent() {}
}