package com.jitong.console.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.Feedback;
import com.jitong.console.domain.User;
import com.opensymphony.xwork2.Preparable;

public class FeedbackAction extends JITActionBase implements Preparable {
	private Feedback feedback;
	private String feedbackid;

	private String qryFixed;
	private String qrySev;
	
	
	private Map fixedList;
	private List sevList;
	public String getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}

	public void prepare() throws JTException {
		if (!StringUtil.isEmpty(feedbackid)) {
			BaseService service = new BaseService();
			feedback = (Feedback) service.findBoById(Feedback.class, feedbackid);
		}
		fixedList = new HashMap();
		fixedList.put("", "");
		fixedList.put("0", "未解决");
		fixedList.put("1", "已解决");
		sevList = new ArrayList();
		sevList.add("");
		sevList.add("高");
		sevList.add("中");
		sevList.add("低");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() throws JTException {
		return INPUT;
	}

	public String edit() throws JTException {
		return "edit";
	}

	/**
	 * 提交反馈
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return INPUT;
		}
		
		BaseService service = new BaseService();
		if (feedback.getId() == null || "".equals(feedback.getId())) {
			feedback.setRespondentId(u.getId());
			feedback.setTime(DateUtil.getCurrentTime());
			feedback.setIsFixed("0");
			service.createBo(feedback);
		} else {
			feedback.setOperatorId(u.getId());
			service.updateBo(feedback);
		}
		return list();
	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from Feedback me where 1=1 ";
		if(!StringUtil.isEmpty(qryFixed)){
			hql+=" and me.isFixed='"+qryFixed+"'";
		}
		if(!StringUtil.isEmpty(qrySev)){
			hql+=" and me.sev='"+qrySev+"'";
		}
		hql+=" order by me.time desc";
		return hql;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Map getFixedList() {
		return fixedList;
	}

	public void setFixedList(Map fixedList) {
		this.fixedList = fixedList;
	}

	public List getSevList() {
		return sevList;
	}

	public void setSevList(List sevList) {
		this.sevList = sevList;
	}

	public String getQryFixed() {
		return qryFixed;
	}

	public void setQryFixed(String qryFixed) {
		this.qryFixed = qryFixed;
	}

	public String getQrySev() {
		return qrySev;
	}

	public void setQrySev(String qrySev) {
		this.qrySev = qrySev;
	}

}
