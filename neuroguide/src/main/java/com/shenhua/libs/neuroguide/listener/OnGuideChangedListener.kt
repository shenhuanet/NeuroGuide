package com.shenhua.libs.neuroguide.listener

import com.shenhua.libs.neuroguide.core.PageController

/**
 * 引导层显示和消失的监听
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
interface OnGuideChangedListener {
    /**
     * 当引导层显示时回调
     *
     * @param controller
     */
    fun onGuideShow(controller: PageController)

    /**
     * 当引导层消失时回调
     *
     * @param controller
     */
    fun onGuideDismiss(controller: PageController)
}