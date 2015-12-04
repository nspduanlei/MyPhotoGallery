package com.duanlei.myphotogallery.view.activity;

import android.support.v4.app.Fragment;

import com.duanlei.myphotogallery.view.fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
    
}
