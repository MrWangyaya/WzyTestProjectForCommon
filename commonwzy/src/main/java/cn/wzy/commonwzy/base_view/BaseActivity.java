package cn.wzy.commonwzy.base_view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import java.io.Serializable;

import cn.wzy.commonwzy.R;
import cn.wzy.commonwzy.base_utils.StatusBarCompat;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


/**
 * Created by wangzeya on 16/10/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public ProgressDialog pd;
    public AlertDialog.Builder dialog;
    private Dialog loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(FLAG_, FLAG_FULLSCREEN);
        setContentView(setLayoutID());
        initView();
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void setStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void translucentStatusBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    protected void setToolBar(Toolbar toolBar, CharSequence title) {
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    private void onBackPressedSupport() {
        onBackPressed();
    }


    public void actyLoad(){}

    protected abstract int setLayoutID();

    //推荐插件 findviewbyme
    public abstract void initView();


    /**
     * 开启浮动加载进度条
     */
    public void showLoading() {
        if(pd == null) pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void showLoading(String msg) {

        if(msg == null || msg == "") return;
        if(pd == null) pd = new ProgressDialog(this);
        pd.setMessage(msg);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    /**
     * 停止浮动加载进度条
     */
    public void hideLoading()
    {
        if(pd != null) pd.cancel();
    }

    public void showNetError() {
        if(pd != null) pd.cancel();

        if(dialog == null) {
            dialog = new AlertDialog.Builder(this);
            dialog.setMessage(R.string.net_error)
                    .setTitle(R.string.hint)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
        }

        dialog.show();
    }


    public void startActy(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    public <T extends Serializable> void startActy(String key, Class<?> cls , T t) {
        Intent intent = new Intent(this,cls);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key,t);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
