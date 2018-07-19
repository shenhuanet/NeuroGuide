package com.shenhua.libs.neuroguide.core

import android.app.Activity
import android.app.Fragment
import android.view.View
import com.shenhua.libs.neuroguide.listener.OnGuideChangedListener
import com.shenhua.libs.neuroguide.listener.OnPageChangedListener

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class Builder {

    var activity: Activity? = null
    var fragment: Fragment? = null
    var v4Fragment: android.support.v4.app.Fragment? = null
    var alwaysShow: Boolean = false
    var anchor: View? = null
    var label: String? = null

    var guidePages: ArrayList<GuidePage> = ArrayList()
    var onPageChangedListener: OnPageChangedListener? = null
    var onGuideChangedListener: OnGuideChangedListener? = null

    constructor(activity: Activity, label: String) {
        this.activity = activity
        this.label = label
    }

    constructor(fragment: Fragment, label: String) {
        this.fragment = fragment
        this.activity = fragment.activity
        this.label = label
    }

    constructor(v4Fragment: android.support.v4.app.Fragment, label: String) {
        this.v4Fragment = v4Fragment
        this.activity = v4Fragment.activity
        this.label = label
    }

    /**
     * 引导层显示的锚点，即根布局，不设置的话默认是 decorView
     *
     * @param anchor root
     */
    fun anchor(anchor: View): Builder {
        this.anchor = anchor
        return this
    }

    /**
     * 是否总是显示引导层，即是否无限次的显示。
     * 默认为 false，如果设置了 true，则默认跳过SharedPreferences数据。
     *
     * @param b
     */
    fun alwaysShow(b: Boolean): Builder {
        this.alwaysShow = b
        return this
    }

    /**
     * 添加引导页
     */
    fun addGuidePage(page: GuidePage): Builder {
        guidePages.add(page)
        // 引导页唯一标识符，将进行存储
        return this
    }

    /**
     * 设置引导层隐藏，显示监听
     */
    fun guideChangedListener(listener: OnGuideChangedListener): Builder {
        this.onGuideChangedListener = listener
        return this
    }

    /**
     * 设置引导页切换监听
     */
    fun pageChangedListener(listener: OnPageChangedListener): Builder {
        this.onPageChangedListener = listener
        return this
    }

    /**
     * 显示
     */
    fun show(): PageController {
        val pageController = PageController(this)

        if (!alwaysShow) {
            val sp = activity?.application?.getSharedPreferences("NeuroGuide-$label", Activity.MODE_PRIVATE)
            val boolean = sp?.getBoolean("show", false)
            if (boolean != null && boolean) {
                return pageController
            }
            sp?.edit()?.putBoolean("show", true)?.apply()
        }

        pageController.show()
        return pageController
    }
}
