package com.ssrv.fms.util.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

public class BeanUtil{
	
	public static void copyBeans(Object target, Object src) throws InvocationTargetException, IllegalAccessException{
		
		java.util.Date defaultValue = null;
	    Converter converter = new DateConverter(defaultValue);
	    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
	    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
		System.out.println("Copying properties from fromBean to toBean");
		BeanUtils.copyProperties(target, src);
		System.out.println("objects copied");
	}
	
	@SuppressWarnings("unchecked")
	public static <D, S> void copyBeans(Collection<D> destList, Collection<S> srcList, D tempDestObj) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException{
		
		for(S src : srcList){
			tempDestObj = (D)tempDestObj.getClass().newInstance();
			copyBeans(tempDestObj, src);
			destList.add(tempDestObj);
		}
	}
	
	/*public static <D, S> void copyBeansRecursive(Collection<S> srcList, D tempDestObj) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException{
		
		String srcClassName = srcList.getClass().getName();
		String descClassName = srcClassName + "View";
		Class descClass = Class.forName(descClassName);
		descClass descObj = descClass.newInstance();
		for(S src : srcList){
			tempDestObj = (D)tempDestObj.getClass().newInstance();
			copyBeans(tempDestObj, src);
			destList.add(tempDestObj);
		}
	}*/

}
