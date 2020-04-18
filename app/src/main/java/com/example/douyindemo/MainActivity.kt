package com.example.douyindemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View.OVER_SCROLL_NEVER
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.douyindemo.adapter.VerticalViewPagerAdapter
import com.example.douyindemo.fragment.VideoFragment
import com.example.douyindemo.view.DefaultTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var prePager = -1
    var curPager = 0
    val fragmentList = arrayListOf<VideoFragment>()

    lateinit var verticalViewPagerAdapter: VerticalViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        v_viewpager.setPageTransformer(true, DefaultTransformer())
        v_viewpager.overScrollMode = OVER_SCROLL_NEVER

        verticalViewPagerAdapter =
            VerticalViewPagerAdapter(supportFragmentManager, fragmentList)
        v_viewpager.adapter = verticalViewPagerAdapter

        addData()

        v_viewpager.offscreenPageLimit = 3
        v_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            @SuppressLint("MissingSuperCall")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                Log.e("A-onPageSelected", position.toString())
                prePager = curPager
                curPager = position

                Log.e("onPageSelectedqqq_之前：", prePager.toString())
                Log.e("onPageSelectedqqq-当前：", curPager.toString())

                fragmentList[prePager].pauseVideo()
                fragmentList[curPager].playVideo()

                if (position == fragmentList.size - 1) {
                    addData()
                }
            }
        })

        Handler().postDelayed(object : Runnable {
            override fun run() {
                runOnUiThread {
                    if(curPager == 0){
                        fragmentList[0].playVideo()
                    }
                }
            }
        }, 1000);
    }


    /**
     * 添加数据
     */
    fun addData() {
        val dataList = arrayListOf<String>()
        dataList.add("http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4")
        dataList.add("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4")
        dataList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4")
        dataList.add("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4")

        for (data in dataList) {
            val videoFragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString("data", data);
            videoFragment.arguments = bundle;//数据传递到fragment中
            fragmentList.add(videoFragment)
        }

        verticalViewPagerAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        fragmentList[curPager].playVideo()
    }
    override fun onPause() {
        super.onPause()
        fragmentList[curPager].pauseVideo()
    }
}
