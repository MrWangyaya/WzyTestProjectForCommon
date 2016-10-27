package cn.wzy.wzytestprojectforcommon;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.wzy.commonwzy.base_view.BaseFragment;
import cn.wzy.commonwzy.base_view.LazyBaseFragment;
import sj.mblog.L;

/**
 * Created by wangzeya on 16/10/27.
 */

public class ThreaFragmentLazy extends BaseFragment {


    private RelativeLayout activityMain;
    private TextView tv;



    @Override
    public int getLayoutId() {
        L.i("layoutId:"+R.id.activity_main);
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        activityMain = (RelativeLayout)view.findViewById(R.id.activity_main);
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    public void getData() {
        tv.setText("第3页");
    }
}
