/**
 * 
 */
package com.hotdog.springboot.common.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 持有BeanFactory的引用，方便容器外访问
 * @author chailiangzhi
 * @date 2015-7-15
 * 
 */
public class BeanFactoryKeeper implements BeanFactoryAware {

	/**
	 * BeanFactory的引用
	 */
	static BeanFactory beanFactory = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		beanFactory = arg0;
	}

	/**
	 * 获取Bean
	 * @param arg0
	 * @return
	 */
	public static Object getBean(String arg0) {
		return beanFactory.getBean(arg0);
	}

	/**
	 * 获取BeanFactory
	 * @return
	 */
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}

}
