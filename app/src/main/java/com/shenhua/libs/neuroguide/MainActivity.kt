package com.shenhua.libs.neuroguide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.shenhua.libs.neuroguide.core.GuidePage
import com.shenhua.libs.neuroguide.core.HighLight
import com.shenhua.libs.neuroguide.core.PageController
import com.shenhua.libs.neuroguide.listener.OnGuideChangedListener
import com.shenhua.libs.neuroguide.listener.OnPageChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnGuideChangedListener, OnPageChangedListener {

    override fun onPageChanged(page: Int) {
        println("--------- currentPage:$page")
    }

    override fun onGuideShow(controller: PageController) {
        println("--------- onGuideShow")
    }

    override fun onGuideDismiss(controller: PageController) {
        println("--------- onGuideDismiss")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guidePage = GuidePage()
                .addHighLight(tvOval, HighLight.Shape.OVAL)
                .addHighLight(btnRoundRect, HighLight.Shape.ROUND_RECTANGLE)
                .addHighLight(tvRect)
                .addHighLight(tvCircle, 20)
                .setContentView(R.layout.view_guide_1)
                .setNextPageView(R.id.tvNext)
                .setExitView(R.id.tvDismiss)

        val guidePage2 = GuidePage()
                .addHighLight(tvOval, HighLight.Shape.OVAL, true)
                .addHighLight(btnRoundRect, HighLight.Shape.ROUND_RECTANGLE, true)
                .addHighLight(tvRect, true)
                .addHighLight(tvCircle, 20, true)
                .setContentView(R.layout.view_guide_2)
                .setNextPageView(R.id.tvNext)

        NeuroGuide.with(this, "main")
                .alwaysShow(true)
                .addGuidePage(guidePage)
                .addGuidePage(guidePage2)
                .guideChangedListener(this)
                .pageChangedListener(this)
                .show()

        btnRoundRect.setOnClickListener { Toast.makeText(this, "µ„Œ“∏…¬Ô", Toast.LENGTH_SHORT).show() }
    }
}
