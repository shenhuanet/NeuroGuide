package com.shenhua.libs.neuroguide.core

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.View
import java.util.*

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class GuidePage {

    var contentView: Int = 0
    var nextPageView: Int = 0
    var exitView: Int = 0
    val highLights = ArrayList<HighLight>()

    /**
     * 添加矩形高亮
     *
     * @param view   view
     * @param dashed 是否添加虚线框
     * @return GuidePage
     */
    @JvmOverloads
    fun addHighLight(view: View, dashed: Boolean = false): GuidePage {
        return addHighLight(view, HighLight.Shape.RECTANGLE, 0, 0, dashed)
    }

    /**
     * 添加圆形高亮
     *
     * @param view   view
     * @param radius 高亮外围半径
     * @param dashed 是否添加虚线框
     * @return GuidePage
     */
    @JvmOverloads
    fun addHighLight(view: View, radius: Int, dashed: Boolean = false): GuidePage {
        return addHighLight(view, HighLight.Shape.CIRCLE, 0, radius, dashed)
    }

    /**
     * 添加高亮
     *
     * @param view   view
     * @param shape  shape
     * @param dashed 是否添加虚线框
     * @return GuidePage
     */
    @JvmOverloads
    fun addHighLight(view: View, shape: HighLight.Shape, dashed: Boolean = false): GuidePage {
        return addHighLight(view, shape, 10, 4, dashed)
    }

    /**
     * 添加圆角矩形高亮
     *
     * @param view    需要高亮的view
     * @param shape   高亮形状[com.shenhua.libs.neuroguide.core.HighLight.Shape]
     * @param round   圆角尺寸，单位dp，仅[com.shenhua.libs.neuroguide.core.HighLight.Shape.ROUND_RECTANGLE]有效
     * @param padding 高亮相对view的padding,单位px
     * @param dashed  是否添加虚线框
     */
    @JvmOverloads
    fun addHighLight(view: View, shape: HighLight.Shape, round: Int, padding: Int, dashed: Boolean = false): GuidePage {
        val highlight = HighlightView(view, shape, round, padding, dashed)
        highLights.add(highlight)
        return this
    }

    /**
     * 设置ContentView
     *
     * @return GuidePage
     */
    fun setContentView(@LayoutRes layoutRes: Int): GuidePage {
        this.contentView = layoutRes
        return this
    }

    /**
     * 设置下一页按钮
     *
     * @param nextId 按钮id
     * @return GuidePage
     */
    fun setNextPageView(@IdRes nextId: Int): GuidePage {
        this.nextPageView = nextId
        return this
    }

    /**
     * 设置退出、跳过按钮
     *
     * @return GuidePage
     */
    fun setExitView(@IdRes exitId: Int): GuidePage {
        this.exitView = exitId
        return this
    }
}