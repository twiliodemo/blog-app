package com.blog1.paylod;

import java.util.Date;

public class ErrorDetail {
    private Date timeStamp;
    private String message;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetail(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    private String details;


}
