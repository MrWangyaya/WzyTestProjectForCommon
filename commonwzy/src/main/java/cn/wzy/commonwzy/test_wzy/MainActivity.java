package cn.wzy.commonwzy.test_wzy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.wzy.commonwzy.base_view.BaseActivity;


/**
 * 这是一个示例
 */

public class MainActivity extends BaseActivity {

    private FrameLayout fragmentlayout;
    private CommonTabLayout tabLayout;

//
//    private String[] mTitles = {"首页", "美女","视频","关注"};
//    private int[] mIconUnselectIds = {
//            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
//    private int[] mIconSelectIds = {
//            R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
//    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

//    private NewsMainFragment newsMainFragment;
//    private PhotosMainFragment photosMainFragment;
//    private VideoMainFragment videoMainFragment;
//    private CareMainFragment careMainFragment;
    private static int tabLayoutHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setLayoutID() {
//        return R.layout.acty_navigation;
        return 0;
    }

    @Override
    public void initView() {
//        fragmentlayout = (FrameLayout) findViewById(R.id.fragmentlayout);
//        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);
    }


    /**
     * 初始化tab
     */
    private void initTab() {
//        for (int i = 0; i < mTitles.length; i++) {
//            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
//        }
//        tabLayout.setTabData(mTabEntities);
//        //点击监听
//        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                SwitchTo(position);
//            }
//            @Override
//            public void onTabReselect(int position) {
//            }
//        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        int currentTabPosition = 0;
//        if (savedInstanceState != null) {
//            newsMainFragment = (NewsMainFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
//            photosMainFragment = (PhotosMainFragment) getSupportFragmentManager().findFragmentByTag("photosMainFragment");
//            videoMainFragment = (VideoMainFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
//            careMainFragment = (CareMainFragment) getSupportFragmentManager().findFragmentByTag("careMainFragment");
//            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
//        } else {
//            newsMainFragment = new NewsMainFragment();
//            photosMainFragment = new PhotosMainFragment();
//            videoMainFragment = new VideoMainFragment();
//            careMainFragment = new CareMainFragment();
//
//            transaction.add(R.id.fl_body, newsMainFragment, "newsMainFragment");
//            transaction.add(R.id.fl_body, photosMainFragment, "photosMainFragment");
//            transaction.add(R.id.fl_body, videoMainFragment, "videoMainFragment");
//            transaction.add(R.id.fl_body, careMainFragment, "careMainFragment");
//        }
//        transaction.commit();
//        SwitchTo(currentTabPosition);
//        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        switch (position) {
//            //首页
//            case 0:
//                transaction.hide(photosMainFragment);
//                transaction.hide(videoMainFragment);
//                transaction.hide(careMainFragment);
//                transaction.show(newsMainFragment);
//                transaction.commitAllowingStateLoss();
//                break;
//            //美女
//            case 1:
//                transaction.hide(newsMainFragment);
//                transaction.hide(videoMainFragment);
//                transaction.hide(careMainFragment);
//                transaction.show(photosMainFragment);
//                transaction.commitAllowingStateLoss();
//                break;
//            //视频
//            case 2:
//                transaction.hide(newsMainFragment);
//                transaction.hide(photosMainFragment);
//                transaction.hide(careMainFragment);
//                transaction.show(videoMainFragment);
//                transaction.commitAllowingStateLoss();
//                break;
//            //关注
//            case 3:
//                transaction.hide(newsMainFragment);
//                transaction.hide(photosMainFragment);
//                transaction.hide(videoMainFragment);
//                transaction.show(careMainFragment);
//                transaction.commitAllowingStateLoss();
//                break;
//            default:
//                break;
//        }
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
