package cn.wzy.commonwzy.base_view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.wzy.commonwzy.R;

/**
 * Created by wangzeya on 16/10/27.
 */

public abstract class BaseFragment extends Fragment {

    public ProgressDialog pd;
    public AlertDialog.Builder dialog;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),null);
        initView(view);
        getData();
        return view;
    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    public abstract void getData();


    /**
     * 开启浮动加载进度条
     */
    public void showLoading() {
        if(pd == null) pd = new ProgressDialog(getActivity());
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
        if(pd == null) pd = new ProgressDialog(getActivity());
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
            dialog = new AlertDialog.Builder(getActivity());
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




}
