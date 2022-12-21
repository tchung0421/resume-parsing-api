package com.snva.resumeprocessor.exception;

public class FileStorageException extends RuntimeException  {

    String msg;
    public FileStorageException(String msg) {
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
