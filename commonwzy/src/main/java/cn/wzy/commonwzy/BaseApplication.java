package cn.wzy.commonwzy;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Message;

import java.util.Stack;

import cn.wzy.commonwzy.base_utils.CrashHandler;


/**
 * Created by wangzeya on 16/10/21.
 */

public class BaseApplication extends Application {
    private static final boolean ISDEBUG = false;
    private static BaseApplication sContext;
    private  static String TAG=BaseApplication.class.getName();
    private Stack<Activity> activityStack = new Stack<Activity>();
    /**
     * 全局消息
     */
    private static Handler main;
    public  void  setmainHandler(Handler handler)
    {
        main = handler;
    }
    public  void  sendHandler(int msg)
    {
        Message message = new Message();
        message.what = msg;
        main.sendMessage(message);
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
        sContext = this;
        if(ISDEBUG){
            setCrashHandler();
        }
        initLog();
    }

    private void initLog() {


    }


    public static synchronized BaseApplication context() {
        return (BaseApplication) sContext;
    }



    /**
     * 捕获收集全局异常
     */
    private void setCrashHandler()
    {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }


//    /*打印出一些app的参数*/
//    private void printAppParameter(){
//        Log.d(TAG, "OS : "+ Build.VERSION.RELEASE + " ( " + Build.VERSION.SDK_INT + " )");
//        DeviceMgr.ScrSize realSize = DeviceMgr.getScreenRealSize(this);
//        Log.d(TAG, "Screen Size: " + realSize. + " X " + realSize.h);
//    }

    public void addActivity(final Activity curAT)
    {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    public void removeActivity(final Activity curAT)
    {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }

    //获取最后一个Activity
    public Activity currentActivity()
    {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回寨内Activity的总数
    public int howManyActivities()
    {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities()
    {
        for (int i = 0, size = activityStack.size(); i < size; i++)
        {
            if (null != activityStack.get(i))
            {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void exit()
    {
        finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
