package com.shenhua.libs.neuroguide.core

import android.app.Activity
import android.app.Fragment
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.shenhua.libs.neuroguide.listener.OnGuideChangedListener
import com.shenhua.libs.neuroguide.listener.OnPageChangedListener


/**
 * 引导页控制器，控制引导层的显示与回退，下一步等
 * Created by shenhua on 2018/7/18.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class PageController(builder: Builder) {

    private var activity: Activity? = null
    private var fragment: Fragment? = null
    private var v4Fragment: android.support.v4.app.Fragment? = null

    private var alwaysShow: Boolean = false
    private var anchor: View? = null

    private var guidePages: ArrayList<GuidePage> = ArrayList()
    private var onGuideChangedListener: OnGuideChangedListener? = null
    private var onPageChangedListener: OnPageChangedListener? = null

    /**
     * 显示引导页的父容器
     */
    private var mParentView: FrameLayout
    /**
     * 使用 anchor 时记录的在父布局的位置
     */
    private var indexOfChild = -1
    private var mCurrentPage = 0
    private var mCurrentGuideView: GuideView? = null
    private var isShow = false

    init {
        this.activity = builder.activity
        this.fragment = builder.fragment
        this.v4Fragment = builder.v4Fragment

        this.alwaysShow = builder.alwaysShow
        this.anchor = builder.anchor

        this.guidePages = builder.guidePages
        this.onGuideChangedListener = builder.onGuideChangedListener
        this.onPageChangedListener = builder.onPageChangedListener

        if (activity == null) {
            activity = fragment?.activity
        }
        if (activity == null) {
            activity = v4Fragment?.activity
        }
        if (activity == null) {
            throw IllegalStateException("activity is null, please make sure that fragment is showing when call NeuroGuide.")
        }
        // 设置锚点view为空时，默认取系统decorView
        if (anchor == null) {
            anchor = activity?.findViewById(android.R.id.content)
        }
        if (anchor is FrameLayout) {
            mParentView = anchor as FrameLayout
        } else {
            val frameLayout = FrameLayout(activity)
            val parent = anchor?.parent as ViewGroup
            indexOfChild = parent.indexOfChild(anchor)
            parent.removeView(anchor)
            if (indexOfChild >= 0) {
                parent.addView(frameLayout, indexOfChild, anchor!!.layoutParams)
            } else {
                parent.addView(parent, anchor!!.layoutParams)
            }
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            frameLayout.addView(anchor, layoutParams)
            mParentView = frameLayout
        }

        mParentView.isFocusable = true
        mParentView.isFocusableInTouchMode = true
        mParentView.setOnKeyListener { _, keyCode, _ ->
            isShow && keyCode == KeyEvent.KEYCODE_BACK
        }
    }

    fun show() {
        if (guidePages.size == 0) {
            return
        }
        mCurrentPage = 0
        onGuideChangedListener?.onGuideShow(this)
        isShow = true
        showGuidePage()
    }

    private fun showGuidePage() {
        val guidePage = guidePages[mCurrentPage]
        val guideView = GuideView(activity!!.applicationContext)
        guideView.setGuidePage(guidePage)
        guideView.setPageController(this)
        if (mCurrentGuideView != null) {
            mParentView.removeView(mCurrentGuideView)
        }
        mCurrentGuideView = guideView
        mParentView.addView(guideView, FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        onPageChangedListener?.onPageChanged(mCurrentPage)
    }

    /**
     * 上一页
     */
    private fun previewPage() {

    }

    /**
     * 下一页
     */
    fun nextPage() {
        if (mCurrentPage < guidePages.size - 1) {
            mCurrentPage++
            showGuidePage()
        } else {
            remove()
        }
    }

    fun remove() {
        var view: View
        for (index in mParentView.childCount - 1 downTo 1) {
            view = mParentView.getChildAt(index)
            if (view != null && view is GuideView) {
                mParentView.removeView(view)
            }
        }
        onGuideChangedListener?.onGuideDismiss(this)
        isShow = false
    }
}