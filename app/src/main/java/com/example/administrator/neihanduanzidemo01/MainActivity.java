package com.example.administrator.neihanduanzidemo01;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.administrator.neihanduanzidemo01.HomeFragment.DiscoverFragment;
import com.example.administrator.neihanduanzidemo01.HomeFragment.HomeFragment;
import com.example.administrator.neihanduanzidemo01.HomeFragment.MessageFragment;
import com.example.administrator.neihanduanzidemo01.HomeFragment.ReviewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
private List<Fragment>mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments=new ArrayList<>();
        FragmentManager manager=getSupportFragmentManager();
        if(savedInstanceState==null)
        {
            Fragment fragment=new HomeFragment();
            mFragments.add(fragment);
            fragment=new ReviewFragment();
            mFragments.add(fragment);
            fragment=new DiscoverFragment();
            mFragments.add(fragment);
            fragment=new MessageFragment();
            mFragments.add(fragment);
            FragmentTransaction transaction = manager.beginTransaction();
            int index=0;
            for(Fragment f:mFragments)
            {
                transaction.add(R.id.home_container,f,"tag"+index);
            transaction.hide(f);
                index++;

            }
            //第一个显示
            transaction.show(mFragments.get(0));
            transaction.commit();

        }
        else{
            //不是第一次创建Fragment会自动的，会由Activity创建好
            //FragmentManager的管理
            for (int i = 0; i <4 ; i++) {
                String tag="tag"+i;
                Fragment f=manager.findFragmentByTag(tag);
                if (f != null) {
                    mFragments.add(f);
                }
            }
        }
        //Tan切换
        RadioGroup group= (RadioGroup) findViewById(R.id.home_tab_bar);
        if (group != null) {
            group.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;
        switch (checkedId) {
            case R.id.home_tab_rb1:
                index=0;
                break;
            case R.id.home_tab_rb2:
                index=1;
                break;
            case R.id.home_tab_rb3:
                index=2;
                break;
            case R.id.home_tab_rb4:
                index=3;
                break;
        }
        switchFragment(index);
    }

    private void switchFragment(int index) {
        if(index>=0&&index<mFragments.size())
        {
            int size=mFragments.size();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            for (int i = 0; i <size ; i++) {
                Fragment f=mFragments.get(i);
                if(i==index)
                {
                    transaction.show(f);
                }
                else{
                    transaction.hide(f);
                }
                transaction.commit();
            }
        }
    }
}


