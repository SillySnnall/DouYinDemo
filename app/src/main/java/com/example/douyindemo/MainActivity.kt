package com.example.douyindemo

import android.os.Bundle
import android.view.View.OVER_SCROLL_NEVER
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.douyindemo.adapter.VerticalViewPagerAdapter
import com.example.douyindemo.fragment.VideoFragment
import com.example.douyindemo.view.DefaultTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        v_viewpager.setPageTransformer(true, DefaultTransformer())
        v_viewpager.overScrollMode = OVER_SCROLL_NEVER



        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(VideoFragment())
        fragmentList.add(VideoFragment())
        fragmentList.add(VideoFragment())
        fragmentList.add(VideoFragment())
        fragmentList.add(VideoFragment())
        val verticalViewPagerAdapter =
            VerticalViewPagerAdapter(supportFragmentManager, fragmentList)
        v_viewpager.adapter = verticalViewPagerAdapter
    }
}
