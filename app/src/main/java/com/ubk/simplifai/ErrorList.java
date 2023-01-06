package com.ubk.simplifai;

public class ErrorList {
    String type, code, userMessage;

    public ErrorList() {
    }

    public ErrorList(String type, String code, String userMessage) {
        this.type = type;
        this.code = code;
        this.userMessage = userMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
