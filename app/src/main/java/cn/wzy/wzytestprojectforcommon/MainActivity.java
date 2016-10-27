package cn.wzy.wzytestprojectforcommon;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.wzy.commonwzy.base_view.BaseActivity;

public class MainActivity extends BaseActivity {

    private FrameLayout fragmentlayout;
    private CommonTabLayout tabLayout;

    private String[] mTitles = {"第一页", "第二页","第三页"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private OneFragmentLazy oneFragment;
    private TwoFragmentLazy twoFragment;
    private ThreaFragmentLazy threaFragment;
    private static int tabLayoutHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragment(savedInstanceState);
        tabLayout.measure(0,0);
        tabLayoutHeight=tabLayout.getMeasuredHeight();


    }

    @Override
    protected int setLayoutID() {
        return R.layout.acty_navigation;
    }

    @Override
    public void initView() {
        fragmentlayout = (FrameLayout) findViewById(R.id.fragmentlayout);
        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);

        initTab();


    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            oneFragment = (OneFragmentLazy) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            twoFragment = (TwoFragmentLazy) getSupportFragmentManager().findFragmentByTag("photosMainFragment");
            threaFragment = (ThreaFragmentLazy) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            oneFragment = new OneFragmentLazy();
            twoFragment = new TwoFragmentLazy();
            threaFragment = new ThreaFragmentLazy();

            transaction.add(R.id.fragmentlayout, oneFragment, "oneFragment");
            transaction.add(R.id.fragmentlayout, twoFragment, "twoFragment");
            transaction.add(R.id.fragmentlayout, threaFragment, "twoFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.hide(twoFragment);
                transaction.hide(threaFragment);
                transaction.show(oneFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(oneFragment);
                transaction.hide(threaFragment);
                transaction.show(twoFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(oneFragment);
                transaction.hide(twoFragment);
                transaction.show(threaFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
