package com.biner.chatwithyou;

/**
 * Function
 * <p/>
 * Created by Biner on 2016/3/7.
 */
public class ListData {
    private String content;
    private int flag;
    private String time;

    public ListData(String content,int flag,String time) {
        setContent(content);
        setFlag(flag);
        setTime(time);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
