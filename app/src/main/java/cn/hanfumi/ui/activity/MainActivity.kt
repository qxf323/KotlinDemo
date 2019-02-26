package cn.hanfumi.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.hanfumi.R
import cn.hanfumi.common.AppManager
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import com.kotlin.mall.ui.fragment.MessageFragment
import com.kotlin.mall.ui.fragment.SignInFragment
import java.util.*
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import cn.hanfumi.ui.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_bottom_navigation.*


/*
    主界面
 */
class MainActivity : BaseActivity() {

    private var pressTime:Long = 0
    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }
    //签到FragmentSign
    private val mSignInFragment by lazy { SignInFragment() }
    //消息Fragment
    private val mMsgFragment by lazy { MessageFragment() }
    //"我的"Fragment
    private val mMeFragment by lazy { MeFragment() }

    private var mCurrentFragment = Fragment();

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.getItemId()) {
                R.id.navigation_home -> {
                    switchFragment(mCurrentFragment, mStack.get(0), item.getTitle())
                    return true
                }
                R.id.navigation_dashboard -> {
                    switchFragment(mCurrentFragment, mStack.get(1), item.getTitle())
                    return true
                }
                R.id.navigation_center ->
                    //由 BottomNavigationView 上面的 imageview 处理点击事件
                    return true
                R.id.navigation_notifications -> {
                    switchFragment(mCurrentFragment, mStack.get(2), item.getTitle())
                    return true
                }
                R.id.navigation_person -> {
                    switchFragment(mCurrentFragment, mStack.get(3), item.getTitle())
                    return true
                }
            }
            return false
        }

    }

    fun switchFragment(from: Fragment, to: Fragment, title: CharSequence) {
        if (mCurrentFragment !== to) {
            mCurrentFragment = to
            val transaction = supportFragmentManager.beginTransaction()
//            mToolbar.setTitle(title)
            if (!to.isAdded) {
                transaction.hide(from).add(R.id.mContaier, to).commit()
            } else {
                transaction.hide(from).show(to).commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()
    }

    /*
        初始化Fragment栈管理
     */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier,mHomeFragment)
        manager.add(R.id.mContaier,mSignInFragment)
//        manager.add(R.id.mContaier,mCartFragment)
        manager.add(R.id.mContaier,mMsgFragment)
        manager.add(R.id.mContaier,mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mSignInFragment)
//        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
        mCurrentFragment = mStack.get(0);

        BottomNavigationViewHelper.disableShiftMode(mBottomNavBarnew)
    }

    /*
        初始化底部导航切换事件
     */
    private fun initBottomNav(){

        mBottomNavBarnew.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
//            override fun onTabReselected(position: Int) {
//            }
//
//            override fun onTabUnselected(position: Int) {
//            }
//
//            override fun onTabSelected(position: Int) {
//                changeFragment(position)
//            }
//        })

//        mBottomNavBar.checkMsgBadge(false)
    }

    /*
        切换Tab，切换对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }

    /*
        初始化监听，购物车数量变化及消息标签是否显示
     */
    private fun initObserve(){
    }

    /*
        加载购物车数量
     */
    private fun loadCartSize(){
    }

    /*
        取消Bus事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    /*
        重写Back事件，双击退出
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000){
           // toast("再按一次退出程序")
            pressTime = time
        } else{
            AppManager.instance.exitApp(this)
        }
    }
}
