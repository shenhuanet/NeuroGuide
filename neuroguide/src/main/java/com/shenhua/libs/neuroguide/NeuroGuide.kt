package com.shenhua.libs.neuroguide

import android.app.Activity
import android.app.Fragment
import android.content.Context
import com.shenhua.libs.neuroguide.core.Builder

/**
 * Created by shenhua on 2018/7/18.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
object NeuroGuide {

    fun with(activity: Activity, label: String): Builder {
        return Builder(activity, label)
    }

    fun with(fragment: Fragment, label: String): Builder {
        return Builder(fragment, label)
    }

    fun with(v4Fragment: android.support.v4.app.Fragment, label: String): Builder {
        return Builder(v4Fragment, label)
    }

    /**
     * 重置标签的显示次数
     *
     * @param context
     * @param label   标签名
     */
    fun resetLabel(context: Context, label: String) {
        val sp = context.getSharedPreferences("NeuroGuide-$label", Activity.MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}