package com.stanley.vote.action;

import java.util.ArrayList;
import java.util.List;

import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.StringUtil;
import com.stanley.vote.domain.Vote;
import com.stanley.vote.domain.Voter;
import com.stanley.vote.service.VoteService;
import com.opensymphony.xwork2.Preparable;

public class VoterAction extends JITActionBase implements Preparable {
	private Vote vote;
	private Voter voter;
	private VoteService service;
	
	public void prepare() throws JTException {
		if (service == null) {
			service =(VoteService) this.getService("voteService");
		}
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			vote = (Vote) service.findBoById(Vote.class, vote.getId());
		}else{
			List votelist =service.queryByHql("from Vote where status='"+Vote.STATUS_2_READY+"'");		
			if(votelist!=null&&votelist.size()==1){
				vote = (Vote)votelist.get(0);
			}
		}
		if (voter != null && !StringUtil.isEmpty(voter.getId())) {
			voter = (Voter) service.findBoById(Voter.class, voter.getId());
		}

	}

	public String list() throws JTException {
		return super.list();
	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(vote!=null){
		return "from com.stanley.vote.domain.Voter voter where voter.vote.id='"+vote.getId()+"' ";
		}else{
			return "from com.stanley.vote.domain.Voter voter where 1=2 ";
		}
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public Voter getCandidate() {
		return voter;
	}

	public void setCandidate(Voter voter) {
		this.voter = voter;
	}

}
