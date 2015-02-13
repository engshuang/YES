/**
 * AppException.java
 *
 * 功能：(类的名称或者用一句话描述类的功能)
 * 类名：AppException
 * 
 * ver     变更日                  公司              作者                   变更内容
 * ————————————————————————————————————————
 * V1.0.0  2014年11月10日   优弈数据      ChenYingshuang   初版
 *
 * Copyright (c) 2014,2016 优弈数据版本所有.
 * LICENSE INFORMATION
 */
package net.yes.exception;

/**
 * 本类是系统异常类
 * 
 * @author ChenYingshuang
 * @version Ver 1.0 2014年11月10日 创建
 * @since Ver 1.0
 *
 */
public class SysException extends RuntimeException {

    /**
     * 序列化版本号
     *
     * @since Ver 1.0
     */
    private static final long serialVersionUID = -7551042494985664466L;

    /**
     * 初始化系统异常
     *
     * @since Ver 1.0
     */
    public SysException(String message) {
        super(message);
    }

    /**
     * 初始化系统异常
     *
     * @since Ver 1.0
     */
    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

}
