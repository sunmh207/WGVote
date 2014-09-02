package com.jitong.voter.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.vote.domain.Candidate;
import com.jitong.vote.domain.Ticket;
import com.jitong.vote.domain.Vote;
import com.jitong.vote.domain.Voter;
import com.jitong.vote.service.VoteService;
import com.opensymphony.xwork2.Preparable;

public class VoterPrepareAction extends JITActionBase implements Preparable {
	private Vote vote;
	private Voter voter;
	
	//private static VoteService service;
	private String message;
	
	public void prepare() throws JTException {
		message=null;
		/*if (service == null) {
			service = new VoteService();
		}*/
		
		
	}

	public String input() throws JTException {
		return INPUT;
	}
	/*public String ready() throws JTException {
		 Voter v = new Voter();
		 String ip = request.getRemoteAddr();
		 String host = request.getRemoteHost();
		 v.setReadyTime(DateUtil.getCurrentTime());
		 v.setIp(ip);
		 v.setHost(host);
		 voter =service.saveVoter(vote, v); 
		 return "ready";
	}*/

	public String refresh() throws JTException {
		Thread curThread = Thread.currentThread();
		System.out.println(curThread);
		System.out.println(curThread.hashCode());
		
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			vote = (Vote) new VoteService().findBoById(Vote.class, vote.getId());
		}else{
			List votelist =new VoteService().queryByHql("from Vote where status='"+Vote.STATUS_2_READY+"'");		
			if(votelist!=null&&!votelist.isEmpty()){
				if(votelist.size()>1){
					message="有多个投票正在进行，请联系管理员!";
				}else{
					vote=(Vote)votelist.get(0);
				}
			}else{
				message="没有投票信息!";
			}
		}
		
		if(vote!=null&&!StringUtil.isEmpty(vote.getId())){
			if(Vote.STATUS_2_READY.equals(vote.getStatus())){	
				message="正在等待管理员启动投票程序...";
				 Voter v= new Voter();
				 String ip = request.getRemoteAddr();
				 String host = request.getRemoteHost();
				 v.setReadyTime(DateUtil.getCurrentTime());
				 v.setIp(ip);
				 v.setHost(host);
				 new VoteService().saveVoter(vote, v); 
				 return "ready";
			}else if(Vote.STATUS_3_INPROGRESS.equals(vote.getStatus())){
				 voter = new Voter();
				 String ip = request.getRemoteAddr();
				 String host = request.getRemoteHost();
				 voter.setReadyTime(DateUtil.getCurrentTime());
				 voter.setIp(ip);
				 voter.setHost(host);
				 new VoteService().saveVoter(vote, voter); 
				 return gotoVoteFormPage(vote);
			}else{
				message="投票信息已过期!";
				//vote=null;
				return "ready";
			}
		}else{
			message="没有投票信息!";
			return "ready";
		}
	}

	private String gotoVoteFormPage(Vote v)throws JTException{
		if (v==null){
			return null;
		}
		
		List list= new VoteService().queryByHql("from Candidate c where c.vote.id='"+v.getId()+"'");
		this.setObjectList(list);
		String type = v.getType();
		
		if(Vote.TYPE_PS.equals(type)){
			 return "ps_voteform";
		 }else if(Vote.TYPE_TJ.equals(type)){
			 return "tj_voteform";
		 }
		return null;
	}
	public String submit() throws JTException {
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			vote = (Vote) new VoteService().findBoById(Vote.class, vote.getId());
		}else{
			message="投票信息出现错误！";
			return gotoVoteFormPage(vote);
		}
		String status = vote.getStatus();
		if(Vote.STATUS_3_INPROGRESS.equals(status)){
			
		}else if(Vote.STATUS_1_NEW.equals(status)||Vote.STATUS_2_READY.equals(status)){
			message="本次投票尚未开始,不能提交！";
			return gotoVoteFormPage(vote);
		}else if(Vote.STATUS_4_DONE.equals(status)){
			message="本次投票已经结束，不能提交！";
			return gotoVoteFormPage(vote);
		}else{
			message="本次投票不在进行当中，不能提交！投票状态:"+vote.getStatusTXT();
			return gotoVoteFormPage(vote);
		}
		String currentTime = DateUtil.getCurrentTime();
		
		if (voter != null && !StringUtil.isEmpty(voter.getId())) {
			voter = (Voter) new VoteService().findBoById(Voter.class, voter.getId());
		}
		voter.setSubmit("1");
		voter.setSubmitTime(currentTime);
		
		Enumeration paraNames = request.getParameterNames();
		//Map paraMap =request.getParameterMap();
		List<Ticket> ticketlist = new ArrayList<Ticket>();
		while(paraNames.hasMoreElements()){
			String parameter = (String)paraNames.nextElement();
			if(parameter.startsWith("ticket_")){
				String result = (String)request.getParameter(parameter);
				String candidateId = parameter.replaceAll("ticket_", "");
				Candidate candidate = (Candidate)new VoteService().findBoById(Candidate.class, candidateId);
				Ticket ticket = new Ticket();
				ticket.setCandidate(candidate);
				ticket.setVoterIp(voter.getIp());
				ticket.setSubmitTime(currentTime);
				ticket.setAgreement(0);
				ticket.setAbstention(0);
				ticket.setAgainst(0);
				if(Ticket.RESULT_AGREEMENT.equals(result)){
					ticket.setAgreement(1);
				}else if(Ticket.RESULT_ABSTENTION.equals(result)){
					ticket.setAbstention(1);
				}else if(Ticket.RESULT_AGAINST.equals(result)){
					ticket.setAgainst(1);
				}
				ticketlist.add(ticket);
			}
		} 
		new VoteService().saveTickets(ticketlist); 
		new VoteService().getS().merge(voter);
		return "vote_success";
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

}
