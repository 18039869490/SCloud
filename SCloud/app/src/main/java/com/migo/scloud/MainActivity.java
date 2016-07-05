package com.migo.scloud;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.migo.scloud.adapter.MainAdapter;
import com.migo.scloud.fragment.MeFragment;
import com.migo.scloud.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAppCompatActivity {

    //----------基本View控件--------
    private TabLayout tabMain;
    private ViewPager vpMain;

    //----------基本变量--------
    private MainAdapter adapter;
    private List<Fragment> fragments = new ArrayList<Fragment>(); //主页面Fragment容器
    private List<String> titles = new ArrayList<String>(); //主界面标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void resetTranslucentStatus() {
        setTranslucentStatus(2, true, R.color.searchBar);
    }


    private void init() {
        tabMain = (TabLayout) findViewById(R.id.tab_main);
        vpMain = (ViewPager) findViewById(R.id.vp_main);

        titles.add("搜索");
        titles.add("我的");

        SearchFragment searchFragment = new SearchFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(searchFragment);
        fragments.add(meFragment);

        tabMain.addTab(tabMain.newTab().setText(titles.get(0)));
        tabMain.addTab(tabMain.newTab().setText(titles.get(1)));

        vpMain.setOffscreenPageLimit(1); //每次只加载一个fragment

        adapter = new MainAdapter(getSupportFragmentManager(), fragments, titles);
        vpMain.setAdapter(adapter);

        tabMain.setupWithViewPager(vpMain);
        tabMain.setTabMode(TabLayout.MODE_FIXED);
    }


}
