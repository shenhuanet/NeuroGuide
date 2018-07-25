package com.shenhua.libs.neuroguide.core

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class GuideView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var dashedPaint: Paint? = null
    private var guidePage: GuidePage? = null
    private var pageController: PageController? = null
    /**
     * 高亮边与虚线边之间的距离
     */
    private var mDashedPadding = 8f
    private var mStrokeWidth = 2f

    private var nextView: View? = null
    private var exitView: View? = null

    init {
        paint = Paint()
        paint!!.isAntiAlias = true
        paint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint!!.color = Color.TRANSPARENT

        dashedPaint = Paint()
        dashedPaint!!.isDither = true
        dashedPaint!!.isAntiAlias = true
        dashedPaint!!.color = Color.WHITE
        dashedPaint!!.style = Paint.Style.STROKE
        dashedPaint!!.strokeWidth = mStrokeWidth
        dashedPaint!!.pathEffect = DashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), 0f)

        setWillNotDraw(false)
    }

    fun setGuidePage(guidePage: GuidePage) {
        this.guidePage = guidePage
    }

    fun setPageController(pageController: PageController) {
        this.pageController = pageController
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val contentView = LayoutInflater.from(context).inflate(guidePage!!.getContentView(), null)
        addView(contentView)
        nextView = contentView.findViewById(guidePage!!.getNextPageView())
        exitView = contentView.findViewById(guidePage!!.getExitView())
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                if (nextView == null && exitView == null) {
                    return true
                }
                if (nextView != null) {
                    val locationInView = HighlightView.getLocationInView(this, nextView)
                    if (locationInView.contains(ev.x.toInt(), ev.y.toInt())) {
                        pageController!!.nextPage()
                    }
                }
                if (exitView != null) {
                    val locationInView = HighlightView.getLocationInView(this, exitView)
                    if (locationInView.contains(ev.x.toInt(), ev.y.toInt())) {
                        pageController!!.remove()
                    }
                }
            }
            else -> {
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(DEFAULT_BACKGROUND_COLOR)
        drawHighlights(canvas)
    }

    private fun drawHighlights(canvas: Canvas) {
        val highLights = guidePage!!.highLights

        for (highLight in highLights) {
            val rectF = highLight.getRectF(parent as ViewGroup)
            when (highLight.shape) {
                HighLight.Shape.CIRCLE -> {
                    canvas.drawCircle(rectF.centerX(), rectF.centerY(), highLight.radius, paint!!)
                    if (highLight.isDashed) {
                        canvas.drawCircle(rectF.centerX(), rectF.centerY(), highLight.radius + mDashedPadding, dashedPaint!!)
                    }
                }
                HighLight.Shape.ROUND_RECTANGLE -> {
                    canvas.drawRoundRect(rectF, highLight.round.toFloat(), highLight.round.toFloat(), paint!!)
                    if (highLight.isDashed) {
                        canvas.drawRoundRect(dashedRect(rectF), highLight.round.toFloat(), highLight.round.toFloat(), dashedPaint!!)
                    }
                }
                HighLight.Shape.RECTANGLE -> {
                    canvas.drawRect(rectF, paint!!)
                    if (highLight.isDashed) {
                        canvas.drawRect(dashedRect(rectF), dashedPaint!!)
                    }
                }
                HighLight.Shape.OVAL -> {
                    canvas.drawOval(rectF, paint!!)
                    if (highLight.isDashed) {
                        canvas.drawOval(dashedRect(rectF), dashedPaint!!)
                    }
                }
            }
        }
    }

    private fun dashedRect(rectF: RectF): RectF {
        return RectF(rectF.left - mDashedPadding, rectF.top - mDashedPadding,
                rectF.right + mDashedPadding, rectF.bottom + mDashedPadding)
    }

    companion object {

        const val DEFAULT_BACKGROUND_COLOR = -0x71000000
    }
}
