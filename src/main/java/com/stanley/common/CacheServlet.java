package com.stanley.common;

import javax.servlet.ServletConfig;

import com.stanley.common.util.DateUtil;
import com.stanley.common.util.StringUtil;

public class CacheServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public void init(ServletConfig config) {		
		loadData();
		if(!StringUtil.matchLisence(JitongConstants.lisence)){
			System.out.println("Lisence is invalid.");
			System.exit(1);
		}else{
			System.out.println("Lisence is valid.");
		}
	}

	private void loadData() {
		System.out.println("Start to load system data to cache ... "+DateUtil.getCurrentTime("yyyy-MM-dd hh:mm:ss"));
		try {
			JitongConstants.init(); 
			//SysCache.loadSysCache();
			 
		} catch (Exception e) {
		}
		System.out.println("Load system data to cache end. "+DateUtil.getCurrentTime("yyyy-MM-dd hh:mm:ss"));
	}
}
