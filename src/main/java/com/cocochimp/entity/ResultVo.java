package com.cocochimp.entity;

import lombok.Data;

@Data
public class ResultVo<T> {
    private String mess;
    private boolean isOk;
    private T data;
}
