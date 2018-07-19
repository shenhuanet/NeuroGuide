package com.shenhua.libs.neuroguide.listener

/**
 * Created by shenhua on 2018/7/18.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
interface OnPageChangedListener {
    /**
     * @param page 当前引导页的position，第一页为0
     */
    fun onPageChanged(page: Int)
}
