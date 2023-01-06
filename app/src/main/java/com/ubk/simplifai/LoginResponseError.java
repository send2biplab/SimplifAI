package com.ubk.simplifai;

public class LoginResponseError {
    ErrorList[] errors;

    public LoginResponseError() {
    }

    public LoginResponseError(ErrorList[] errors) {
        this.errors = errors;
    }

    public ErrorList[] getErrors() {
        return errors;
    }

    public void setErrors(ErrorList[] errors) {
        this.errors = errors;
    }
}
