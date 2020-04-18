package com.example.douyindemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.douyindemo.fragment.VideoFragment;

import java.util.List;

public class VerticalViewPagerAdapter extends FragmentPagerAdapter {

    private List<VideoFragment> fragmentList;


    public VerticalViewPagerAdapter(@NonNull FragmentManager fm, List<VideoFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
