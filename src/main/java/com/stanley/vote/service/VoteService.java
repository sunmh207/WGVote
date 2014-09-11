package com.stanley.vote.service;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanley.common.exception.JTException;
import com.stanley.common.service.BaseService;
import com.stanley.common.util.DateUtil;
import com.stanley.vote.domain.Candidate;
import com.stanley.vote.domain.Ticket;
import com.stanley.vote.domain.Vote;
import com.stanley.vote.domain.Voter;

@Service("voteService")
public class VoteService extends BaseService {

	@Transactional
	public void deleteVote(String voteId) throws JTException {
		if (voteId == null) {
			return;
		}
		this.deleteAll("from Voter voter where voter.vote.id='" + voteId + "'");
		this.deleteAll("from Ticket ticket where ticket.candidate.vote.id='" + voteId + "'");
		this.deleteAll("from Candidate candidate where candidate.vote.id='" + voteId + "'");
		this.deleteAll("from Vote vote where vote.id='" + voteId + "'");
	}

	@Transactional
	public Voter saveVoter(Vote vote, Voter voter) throws JTException {
		if (vote == null || voter == null) {
			return null;
		}

		this.deleteAll("from Voter voter where voter.vote.id='" + vote.getId() + "' and voter.ip='" + voter.getIp() + "'");
		voter.setVote(vote);
		this.createBo(voter);
		return voter;
	}

	/**
	 * 复制主投票信息
	 * 
	 * @param primaryVote
	 * @return
	 * @throws JTException
	 */
	@Transactional
	public Vote copy2secondaryVote(Vote primaryVote) throws JTException {
		if (primaryVote == null || primaryVote == null) {
			throw new JTException("主投票信息不存在，无法复制", this.getClass());
		}

		Vote v = new Vote();
		//BeanUtils.copyProperties(v, primaryVote);
		BeanUtils.copyProperties(primaryVote,v);
		v.setName(primaryVote.getName() + "(再次投票)");
		v.setStatus(Vote.STATUS_1_NEW);
		v.setPrimaryVote(primaryVote);
		v.setCreateTime(DateUtil.getCurrentTime());
		this.createBo(v);
		return v;
	}

	@Transactional
	public Candidate saveCandidate(Candidate candidate) throws JTException {
		if (candidate == null) {
			return null;
		}
		Candidate c = (Candidate) this.mergeBo(candidate);
		return c;
	}

	@Transactional
	public void saveTickets(List<Ticket> ticketlist) throws JTException {
		if (ticketlist == null && ticketlist.isEmpty()) {
			return;
		}

		for (Ticket ticket : ticketlist) {
			Candidate candidate = ticket.getCandidate();
			this.deleteAll("from Ticket ticket where ticket.voterIp='" + ticket.getVoterIp() + "' and ticket.candidate.id = '" + candidate.getId() + "'");
			getCurrentSession().flush();
			this.createBo(ticket);
			getCurrentSession().flush();
			String updateSql = "update candidate c set "
					+ " c.agreement_number = (select sum(t.agreement) from ticket t where t.candidate_id = c.candidate_id), "
					+ " c.abstention_number = (select sum(t.abstention) from ticket t where t.candidate_id = c.candidate_id),"
					+ " c.against_number = (select sum(t.against) from ticket t where t.candidate_id = c.candidate_id)" + " where c.candidate_id='"
					+ candidate.getId() + "'";
			this.executeSQLUpdate(updateSql);
			getCurrentSession().flush();
		}

	}

}
