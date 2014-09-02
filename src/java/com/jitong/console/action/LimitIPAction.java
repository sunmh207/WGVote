package com.jitong.console.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.LimitIP;
import com.jitong.console.domain.User;
import com.opensymphony.xwork2.Preparable;

public class LimitIPAction extends JITActionBase implements Preparable {
	private String id;
	private String ip;
	private String newIP;
	private String newCaption;
	private static BaseService service;

	private String searchIP;

	/*
	 * public String getCategoryId() { return "console.ip"; }
	 */

	public String getIp() {
		return ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNewIP() {
		return newIP;
	}

	public void setNewIP(String newIP) {
		this.newIP = newIP;
	}

	public String getNewCaption() {
		return newCaption;
	}

	public void setNewCaption(String newCaption) {
		this.newCaption = newCaption;
	}

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
	}

	public String getSearchIP() {
		return searchIP;
	}

	public void setSearchIP(String searchIP) {
		this.searchIP = searchIP;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from LimitIP ip ";
		if (!StringUtil.isEmpty(searchIP)) {
			hql += " where ip.ip like '%" + searchIP + "%'";
		}
		hql += " order by ip.ip";
		return hql;
	}

	public String delete() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (!StringUtil.isEmpty(id)) {
			LimitIP limitIP = (LimitIP) service.findBoById(LimitIP.class, id);
			if (!u.getId().equals(limitIP.getCreatorId())) {
				addActionError(limitIP.getIp() + "不是您创建的，不能删除。");
				return list();
			}
			service.deleteBo(LimitIP.class, id);
			SysCache.removePermittedIP(limitIP.getIp());
		}
		return list();
	}

	public String add() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (StringUtil.isEmpty(newIP)) {
			addActionError("IP不能为空");
			return list();
		}
		if (StringUtil.isEmpty(newCaption)) {
			addActionError("服务器名不能为空");
			return list();
		}
		if (service.objectsExist("from LimitIP ip where ip.ip='" + newIP + "'")) {
			addActionError("IP:" + newIP + "已经存在");
			return list();
		}
		LimitIP limitIp = new LimitIP();
		limitIp.setIp(newIP);
		limitIp.setCaption(newCaption);
		limitIp.setCreatorId(u.getId());
		service.createBo(limitIp);
		SysCache.updatePermittedIP(limitIp);
		return list();
	}

}
