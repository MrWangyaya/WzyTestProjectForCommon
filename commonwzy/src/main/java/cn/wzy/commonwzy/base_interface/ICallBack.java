package cn.wzy.commonwzy.base_interface;

/**
 * Created by wangzeya on 16/10/23.
 */

public interface ICallBack
{
    <T> void onSuccess(T type);
    void onError();
}
