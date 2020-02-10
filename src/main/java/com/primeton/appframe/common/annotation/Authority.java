package com.primeton.appframe.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * ClassName: Authority <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:06:36 <br/>
 * 权限注解
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authority {
	String opCode();

	String opName();

	int opSeq() default 1;
}
