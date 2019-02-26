package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.hanfumi.R
import cn.hanfumi.ui.fargment.BaseFragment


/*
    主界面Fragment
 */
class HomeFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container,savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (view != null) {
            super.onViewCreated(view, savedInstanceState)
        }
        initView()
        initBanner()
        initNews()
    }

    /*
        初始化视图
     */
    private fun initView() {

    }

    /*
        初始化Banner
     */
    private fun initBanner() {

    }

    /*
        初始化公告
     */
    private fun initNews(){
        //公告
    }

}
