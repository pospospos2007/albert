package com.zdcf.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by William Suen on 2016/7/20.
 */
public class SpringBeanFactoryUtils implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     *
     * @param applicationContext ApplicationContext 对象.
     * @throws BeansException
     * @author wangdf
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanFactoryUtils.appCtx = applicationContext;
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     * @author wangdf
     */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     *
     * @param beanName bean的名字
     * @return 返回一个bean对象
     * @author wangdf
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

}