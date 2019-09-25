package com.xncoding.pos.common.annotion;

import java.lang.annotation.*;

/**
 * 多数据源标识
 * 创建拦截设置数据源的注解
 * 用来在Service方法上面注解使用哪个数据源
 * @author xiongneng
 * @since 2017年3月5日 上午9:44:24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {

    String name() default "";
}
