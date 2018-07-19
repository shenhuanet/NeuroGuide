package com.shenhua.libs.neuroguide.core

import android.graphics.RectF
import android.view.View

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
interface HighLight {

    /**
     * 获取高亮区域形状
     *
     * @return shape
     */
    val shape: Shape

    /**
     * 当shape为circle时调用此方法获取半径
     *
     * @return 半径值
     */
    val radius: Float

    /**
     * 获取圆角，仅当shape = Shape.ROUND_RECTANGLE才调用次方法
     *
     * @return 圆角大小
     */
    val round: Int

    /**
     * 是否绘制外围虚线框
     *
     * @return boolean
     */
    val isDashed: Boolean

    /**
     * 获取矩形区域
     *
     * @param view anchor view
     * @return highlight's rectF
     */
    fun getRectF(view: View): RectF

    enum class Shape {
        /**
         * 圆形
         */
        CIRCLE,
        /**
         * 矩形
         */
        RECTANGLE,
        /**
         * 椭圆
         */
        OVAL,
        /**
         * 圆角矩形
         */
        ROUND_RECTANGLE
    }
}
