/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.service;

/**
 * 类ServiceException.java的实现描述： Service层公用的Exception,
 * 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author arron 2017年10月30日 下午4:04:39
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
