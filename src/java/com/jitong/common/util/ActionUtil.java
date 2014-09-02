package com.jitong.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jitong.common.action.JITActionBase;

public class ActionUtil {

	private static Logger logger = Logger.getLogger(ActionUtil.class);

	public static Class<?> retireDomainClassNameFromAction(JITActionBase action) {
		String businessClassName = action.businessClass;
		if (action.businessClass == null) {
			String actionClassName = action.getClass().getName();
			businessClassName = actionClassName
					.replaceFirst("action", "domain");
			businessClassName = businessClassName.replaceFirst("Action", "");
		}
		try {
			return Class.forName(businessClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("找不到Business Class[" + businessClassName
					+ "]. 请检查struts中关于" + action.getClass().getName()
					+ "的相关配置，或者将Action重新命名。");
		}
	}



	/*static String retrieveCategoryItemFromAction(JITActionBase action) {
		logger.debug("action class: "+action.getClass().getName());
		String categoryId = action.getCategoryId();
		if (StringUtil.isEmpty(categoryId)) {
			String actionClassName = action.getClass().getName();
			String[] tokens = actionClassName.split("\\.");
			logger.debug("tokens length: " + tokens.length);
			
			if (tokens.length == 5) {
				
				String topCategory = tokens[2];
				logger.debug("topCategory: "+topCategory);
				if ("".equals(topCategory)) {
					categoryId = null; // in common package, no associated
										// category.
				} else {
					String subCategory = tokens[4];
					logger.debug("topCategory: "+subCategory);
					int idxAction = subCategory.lastIndexOf("Action");
					if (idxAction >= 0) {
						subCategory = subCategory.substring(0, idxAction)
								.toLowerCase();
					}
					categoryId = topCategory + "." + subCategory;
					logger.debug("full: "+categoryId);
					if (ContentItemManager.getCategoryItem(categoryId) == null) {
						categoryId = topCategory;
					}
				}
			}
		}
		if (ContentItemManager.getCategoryItem(categoryId) == null) {
			return null;
		}
		return categoryId;
	}*/
	

	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
