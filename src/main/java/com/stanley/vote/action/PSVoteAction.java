package com.stanley.vote.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.stanley.common.JitongConstants;
import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.DateUtil;
import com.stanley.common.util.StringUtil;
import com.stanley.console.domain.User;
import com.stanley.vote.domain.Vote;
import com.stanley.vote.service.VoteService;

public class PSVoteAction extends JITActionBase implements Preparable {
	private Vote vote;
	private Vote primaryVote;
	private VoteService service;
	//private String primaryVoteId;
	
	private String[] hourlist = JitongConstants.HOUR_ARRAY;
	private String[] minutelist = JitongConstants.MINUTE_ARRAY;
	private String qryName;
	private String qryType;
	public void prepare() throws JTException {
		if (service == null) {
			service = (VoteService) this.getService("voteService");
		}
		
		if (primaryVote != null && !StringUtil.isEmpty(primaryVote.getId())) {
			primaryVote = (Vote) service.findBoById(Vote.class, primaryVote.getId());
		}
		
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			vote = (Vote) service.findBoById(Vote.class, vote.getId());
			if(primaryVote==null){
				primaryVote = vote.getPrimaryVote();
			}
		}

	}

	public String list() throws JTException {
	 super.list();
	 if (primaryVote != null && !StringUtil.isEmpty(primaryVote.getId())) {
		 return "success_x";
	 }else{
		 return SUCCESS;
	 }
	}

	public String input() throws JTException {
		return INPUT;
	}

	public String edit() throws JTException {
		return "edit";
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("用户连接超时");
			return INPUT;
		}

		if (StringUtil.isEmpty(vote.getId())) {
			vote.setCreateTime(DateUtil.getCurrentTime());
			vote.setType(Vote.TYPE_PS);
			vote.setStatus(Vote.STATUS_1_NEW);
			service.createBo(vote);
		} else {
			service.updateBo(vote);
		}
		return list();
	}
	
	public String ready() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		List l=service.queryByHql("from Vote where status in ('"+Vote.STATUS_2_READY+"','"+Vote.STATUS_3_INPROGRESS+"','"+Vote.STATUS_x_READY+"','"+Vote.STATUS_x_INPROGRESS+"')");
		if(l!=null&&l.size()>=1){
			this.addActionError("已经有正在进行(准备就绪、投票中、再次投票)的投票！");
			for(Object o:l){
				Vote v = (Vote)o;
				this.addActionError("投票名称:"+v.getName());
			}
			return list();
		}else{
			if (vote != null && !StringUtil.isEmpty(vote.getId())) {
				Vote pv =vote.getPrimaryVote(); 
				if(pv!=null){
					pv.setStatus(Vote.STATUS_x_READY);
				}
				vote.setStatus(Vote.STATUS_2_READY);
				service.updateBo(vote);
			}
			return list();
		}
	}
	public String start() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			Vote pv =vote.getPrimaryVote(); 
			if(pv!=null){
				pv.setStatus(Vote.STATUS_x_INPROGRESS);
			}
			vote.setStatus(Vote.STATUS_3_INPROGRESS);
			service.updateBo(vote);
		}
		return list();
	}
	public String stop() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			Vote pv =vote.getPrimaryVote(); 
			if(pv!=null){
				pv.setStatus(Vote.STATUS_4_DONE);
			}
			vote.setStatus(Vote.STATUS_4_DONE);
			service.updateBo(vote);
		}
		return list();
	}
	public String delete() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			Vote pv =vote.getPrimaryVote(); 
			if(pv!=null){
				pv.setStatus(Vote.STATUS_4_DONE);
			}
			service.deleteVote(vote.getId());
		}
		return list();
	}
	public String copy2secondaryVote() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		service.copy2secondaryVote(primaryVote);
		return list();
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from com.stanley.vote.domain.Vote me where type='"+Vote.TYPE_PS+"' ";
		if(!StringUtil.isEmpty(qryName)){
			hql+=" and me.name like '%"+qryName+"%'";
		}
		if (primaryVote != null && !StringUtil.isEmpty(primaryVote.getId())) {
			hql+=" and me.primaryVote.id = '"+primaryVote.getId()+"'";
		}else{
			hql+=" and me.primaryVote.id is null ";
		}
		
		hql += " order by me.startDate desc";
		return hql;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public String[] getHourlist() {
		return hourlist;
	}

	public void setHourlist(String[] hourlist) {
		this.hourlist = hourlist;
	}

	public String[] getMinutelist() {
		return minutelist;
	}

	public void setMinutelist(String[] minutelist) {
		this.minutelist = minutelist;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public Vote getPrimaryVote() {
		return primaryVote;
	}

	public void setPrimaryVote(Vote primaryVote) {
		this.primaryVote = primaryVote;
	}

}
