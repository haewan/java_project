package com.springboot.jagent.response;

public class JSONResponse
{
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() { return result; }

    private boolean status;
    private String result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
}
