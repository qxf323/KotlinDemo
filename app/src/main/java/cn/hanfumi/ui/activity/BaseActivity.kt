package cn.hanfumi.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import cn.hanfumi.common.AppManager

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

//    //获取Window中视图content
//    val contentView: View
//        get() {
//            val content = find<FrameLayout>(android.R.id.content)
//            return content.getChildAt(0)
//        }
}