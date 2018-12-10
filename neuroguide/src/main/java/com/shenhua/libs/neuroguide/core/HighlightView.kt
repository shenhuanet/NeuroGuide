package com.shenhua.libs.neuroguide.core

import android.app.Activity
import android.graphics.Rect
import android.graphics.RectF
import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class HighlightView internal constructor(private val mHole: View?, override val shape: HighLight.Shape, override val round: Int,
                                         /**
                                          * 高亮相对view的padding
                                          */
                                         private val padding: Int, override val isDashed: Boolean) : HighLight {
    private var rectF: RectF? = null

    override val radius: Float
        get() {
            if (mHole == null) {
                return 0f
            }
            return (Math.max(mHole.width / 2, mHole.height / 2) + padding).toFloat()
        }

    override fun getRectF(view: View): RectF {
        if (mHole == null) {
            return RectF()
        }
        if (rectF == null) {
            rectF = RectF()
            val locationInView = getLocationInView(view, mHole)
            rectF!!.left = (locationInView.left - padding).toFloat()
            rectF!!.top = (locationInView.top - padding).toFloat()
            rectF!!.right = (locationInView.right + padding).toFloat()
            rectF!!.bottom = (locationInView.bottom + padding).toFloat()
        }
        return rectF!!
    }

    companion object {

        fun getLocationInView(parent: View?, child: View?): Rect {
            if (child == null || parent == null) {
                return Rect()
            }

            var decorView: View? = null
            val context = child.context
            if (context is Activity) {
                decorView = context.window.decorView
            }

            val result = Rect()
            val tmpRect = Rect()

            var tmp = child

            if (child === parent) {
                child.getHitRect(result)
                return result
            }
            while (tmp !== decorView && tmp !== parent) {
                tmp!!.getHitRect(tmpRect)
                if ("NoSaveStateFrameLayout" != tmp.javaClass.toString()) {
                    result.left += tmpRect.left
                    result.top += tmpRect.top
                }
                tmp = tmp.parent as View
                if (tmp.parent != null && tmp.parent is ViewPager) {
                    tmp = tmp.parent as View
                }
            }
            result.right = result.left + child.measuredWidth
            result.bottom = result.top + child.measuredHeight
            return result
        }
    }

}