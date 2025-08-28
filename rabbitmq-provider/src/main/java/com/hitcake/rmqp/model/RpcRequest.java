package com.hitcake.rmqp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RpcRequest {
    private String clazz;
    private String method;
    private Object[] params;
}
