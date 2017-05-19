package com.cxy.rpc.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @desc RPC调用类的封装
 * @author chenxy
 * @date createTime:May 16, 2017 11:36:23 PM
 */
@SuppressWarnings("rawtypes")
public class Invocation implements Serializable {
  private static final long serialVersionUID = 7804109759685583745L;
  /** 需要调用的接口 */
  private Class<?> interf;
  /** 远程调用的方法 */
  private String methodName;
  /** 调用的参数 */
  private Object[] params;
  /** 调用的参数类型 */
  private Class[] paramTypes;

  public Invocation() {

  }

  public Invocation(Class<?> clazz, String methodName, Object[] params, Class[] paramsTypes) {
    this.interf = clazz;
    this.methodName = methodName;
    this.params = params;
    this.paramTypes = paramsTypes;
  }

  public Class<?> getInterf() {
    return interf;
  }

  public void setInterf(Class<?> interf) {
    this.interf = interf;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getParams() {
    return params;
  }

  public void setParams(Object[] params) {
    this.params = params;
  }

  public Class[] getParamTypes() {
    return paramTypes;
  }

  public void setParamTypes(Class[] paramTypes) {
    this.paramTypes = paramTypes;
  }

  @Override
  public String toString() {
    return "Invocation [interf=" + interf + ", methodName=" + methodName + ", params="
        + Arrays.toString(params) + ", paramTypes=" + Arrays.toString(paramTypes) + "]";
  }
}
