package com.hw.utils;

import lombok.Data;

/**
 * 统一的返回结果类
 */
@Data
public class UniformResult <T>{
    private boolean success; //是否成功
    private int code; //状态码
    private String message; //返回消息
    private T data;  //返回数据

    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private UniformResult(){}

    /**
     * 成功执行，不返回数据
     * @return
     */
    public static<T> UniformResult<T> ok(){
        UniformResult<T> result = new UniformResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }
    /**
     * 成功执行，并返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static<T> UniformResult<T> ok(T data){
        UniformResult<T> result = new UniformResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static<T> UniformResult<T> error(){
        UniformResult<T> result = new UniformResult<T>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }
    /**
     * 设置是否成功
     * @param success
     * @return
     */
    public UniformResult<T> success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    /**
     * 设置状态码
     * @param code
     * @return
     */
    public UniformResult<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    /**
     * 设置返回消息
     * @param message
     * @return
     */
    public UniformResult<T> message(String message){
        this.setMessage(message);
        return this;
    }
    /**
     * 是否存在
     * @return
     */
    public static<T> UniformResult<T> exist(){
        UniformResult<T> result = new UniformResult<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }


}
