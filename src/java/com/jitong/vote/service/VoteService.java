package com.jitong.vote.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.vote.domain.Candidate;
import com.jitong.vote.domain.Ticket;
import com.jitong.vote.domain.Vote;
import com.jitong.vote.domain.Voter;

public class VoteService extends BaseService {
	public VoteService() throws JTException {
		super();
	}

	public Vote findVote(String voteId)throws JTException{
		return (Vote) this.findBoById(Vote.class, voteId);
	}
	
	public void deleteVote(String voteId) throws JTException {
		if (voteId == null ) {
			return ;
		}
		
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			this.deleteByHQL("from Voter voter where voter.vote.id='"+voteId+"'");
			this.deleteByHQL("from Ticket ticket where ticket.candidate.vote.id='"+voteId+"'");
			this.deleteByHQL("from Candidate candidate where candidate.vote.id='"+voteId+"'");
			this.deleteByHQL("from Vote vote where vote.id='"+voteId+"'");
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	
	public Voter saveVoter(Vote vote, Voter voter) throws JTException {
		if (vote == null || voter == null) {
			return null;
		}
		
		Transaction tx = null;
		try {
			tx = this.beginTransaction(); 
			this.deleteByHQL("from Voter voter where voter.vote.id='"+vote.getId()+"' and voter.ip='"+voter.getIp()+"'");
			voter.setVote(vote);
			this.createBo(voter);
			this.commitTransaction(tx);
			return voter;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	/**
	 * 复制主投票信息
	 * @param primaryVote
	 * @return
	 * @throws JTException
	 */
	public Vote copy2secondaryVote(Vote primaryVote) throws JTException {
		if (primaryVote == null || primaryVote == null) {
			throw new JTException("主投票信息不存在，无法复制",this.getClass());
		}
		
		Transaction tx = null;
		try {
			tx = this.beginTransaction(); 
			Vote v = new Vote();
			BeanUtils.copyProperties(v, primaryVote);
			v.setName(primaryVote.getName()+"(再次投票)");
			v.setStatus(Vote.STATUS_1_NEW);
			v.setPrimaryVote(primaryVote);
			v.setCreateTime(DateUtil.getCurrentTime());
			this.createBo(v);
			this.commitTransaction(tx);
			return v;
		} catch (JTException jte) {
			this.rollbackTransaction(tx);
			throw jte;
		}catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("无法复制主投票信息",e,this.getClass());
		}
	}
	public Candidate saveCandidate(Candidate candidate) throws JTException {
		if (candidate == null ) {
			return null;
		}
		Transaction tx = null;
		try {
			tx = this.beginTransaction(); 
			Candidate c = (Candidate)this.mergeBO(candidate);
			this.commitTransaction(tx);
			return c;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}

	public void saveTickets(List<Ticket> ticketlist) throws JTException {
		if(ticketlist==null&&ticketlist.isEmpty()){
			return;
		}
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			
			for(Ticket ticket:ticketlist){
				Candidate candidate = ticket.getCandidate();
				this.deleteByHQL("from Ticket ticket where ticket.voterIp='"+ticket.getVoterIp()+"' and ticket.candidate.id = '"+candidate.getId()+"'");
				this.createBo(ticket);
				String updateSql="update candidate c set "
						+ " c.agreement_number = (select sum(t.agreement) from ticket t where t.candidate_id = c.candidate_id), "
						+ " c.abstention_number = (select sum(t.abstention) from ticket t where t.candidate_id = c.candidate_id),"
						+ " c.against_number = (select sum(t.against) from ticket t where t.candidate_id = c.candidate_id)"
						+ " where c.candidate_id='"+candidate.getId()+"'";
				this.getS().createSQLQuery(updateSql).executeUpdate();
				
				//update primary vote's tickets and candidate
				/*Candidate primaryVoteCandidate = ticket.getCandidate().getPrimaryVoteCandidate();
				if(primaryVoteCandidate!=null){
					
					this.deleteByHQL("from Ticket ticket where ticket.voterIp='"+ticket.getVoterIp()+"' and ticket.candidate.id = '"+primaryVoteCandidate.getId()+"'");
					Ticket t = new Ticket();
					BeanUtils.copyProperties(t, ticket);
					t.setCandidate(primaryVoteCandidate); 
					t.setSubmitTime(DateUtil.getCurrentTime());
					t.setId(null);
					this.createBo(t);
					
					String updatePrimaryCandidateSql="update candidate c set "
							+ " c.agreement_number = (select sum(t.agreement) from ticket t where t.candidate_id = c.candidate_id), "
							+ " c.abstention_number = (select sum(t.abstention) from ticket t where t.candidate_id = c.candidate_id),"
							+ " c.against_number = (select sum(t.against) from ticket t where t.candidate_id = c.candidate_id)"
							+ " where c.candidate_id='"+primaryVoteCandidate.getId()+"' ";
					this.getS().createSQLQuery(updatePrimaryCandidateSql).executeUpdate();
				}*/
			}
			/*this.getS().merge(voter); */
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("保存投票信息失败",e,this.getClass());
		}
	}
	
}
