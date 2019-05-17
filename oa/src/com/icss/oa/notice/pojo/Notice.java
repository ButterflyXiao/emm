package com.icss.oa.notice.pojo;

import java.util.Date;

public class Notice {
    private Integer noticeId;

    private String noticeTopic;

    private String noticeContent;

    private Date noticeTime;

    private Boolean noticeIsToTop;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTopic() {
        return noticeTopic;
    }

    public void setNoticeTopic(String noticeTopic) {
        this.noticeTopic = noticeTopic == null ? null : noticeTopic.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Boolean getNoticeIsToTop() {
        return noticeIsToTop;
    }

    public void setNoticeIsToTop(Boolean noticeIsToTop) {
        this.noticeIsToTop = noticeIsToTop;
    }
}