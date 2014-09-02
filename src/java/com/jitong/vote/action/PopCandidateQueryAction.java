package com.jitong.vote.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.vote.domain.Candidate;
import com.jitong.vote.service.VoteService;
import com.opensymphony.xwork2.Preparable;

public class PopCandidateQueryAction extends JITActionBase implements Preparable {
	private String qryName;
	private String voteId;
	private VoteService service;

	public void prepare() throws JTException {
		if (service == null) {
			service = new VoteService();
		}
	}

	public String list() throws JTException {
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr==null||pageSizeStr.trim().length()==0){
			setPageSize(500);
		}
		this.setBusinessClass("com.jitong.vote.domain.Candidate");

		return super.list();
	}

	public String select() throws JTException {
		String[] ids = request.getParameterValues("chk");
		if (ids != null && ids.length > 0) {
			String idStr = "";
			String nameStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i > 0) {
					idStr += ",";
					nameStr += ",";
				}
				idStr += ids[i];
				nameStr += SysCache.interpertUserName(ids[i]);
			}
			request.setAttribute("idStr", idStr);
			request.setAttribute("nameStr", nameStr);
		}
		return SUCCESS;
	}


	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}


	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from Candidate candidate where 1=1 and candidate.vote.id='"+voteId+"' ";
		if (!StringUtil.isEmpty(qryName) && !"null".equals(qryName)) {
			hql += " and candidate.name like '%" + qryName + "%'";
		}
		hql+=" order by candidate.agreementNumber desc";
		return hql;
	}

}
