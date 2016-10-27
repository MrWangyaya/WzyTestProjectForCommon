package cn.wzy.commonwzy;

import java.io.Serializable;

/**
 * Created by wangzeya on 16/10/21.
 */

public class BaseEntity implements Serializable
{
    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
