package com.example.douyindemo.fragment

import android.net.Uri
import com.example.douyindemo.R
import com.example.douyindemo.media.IRenderView
import kotlinx.android.synthetic.main.fragment_video.*
import tv.danmaku.ijk.media.player.IMediaPlayer

class VideoFragment : BaseFragment() {
    override fun setLayoutView(): Int {
        return R.layout.fragment_video
    }

    override fun initView() {}
    override fun initData() {
        video_view.setVideoURI(Uri.parse("http://stream1.grtn.cn/tvs2/sd/live.m3u8?_ts&time=1518428696629"))

        video_view.setOnPreparedListener(IMediaPlayer.OnPreparedListener { video_view.start() })
    }

    override fun initEvent() {}
}