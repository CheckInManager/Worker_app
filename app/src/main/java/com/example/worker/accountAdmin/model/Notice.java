package com.example.worker.accountAdmin.model;

public class Notice {

    private String keyValue;
    private String memo;
    private String noticeName;
    private long time;
    private String worksiteName;
    private String worksiteKeyValue;

    public Notice(){

    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getWorksiteName() {
        return worksiteName;
    }

    public void setWorksiteName(String worksiteName) {
        this.worksiteName = worksiteName;
    }

    public String getWorksiteKeyValue() {
        return worksiteKeyValue;
    }

    public void setWorksiteKeyValue(String worksiteKeyValue) {
        this.worksiteKeyValue = worksiteKeyValue;
    }
}
