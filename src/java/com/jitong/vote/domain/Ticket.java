package com.jitong.vote.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Ticket")
public class Ticket {
	public static final String RESULT_AGREEMENT="agreement";
	public static final String RESULT_ABSTENTION="abstention";
	public static final String RESULT_AGAINST="against";
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ticket_id")
	private String id;
	@Column(name = "voter_ip")
	private String voterIp;
	
	@Column(name = "agreement")
	private int agreement;
	
	@Column(name = "abstention")
	private int abstention;
	
	@Column(name = "against")
	private int against;
	@Column(name="submit_time")
	private String submitTime;
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER) 
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoterIp() {
		return voterIp;
	}

	public void setVoterIp(String voterIp) {
		this.voterIp = voterIp;
	}

	public int getAgreement() {
		return agreement;
	}

	public void setAgreement(int agreement) {
		this.agreement = agreement;
	}

	public int getAbstention() {
		return abstention;
	}

	public void setAbstention(int abstention) {
		this.abstention = abstention;
	}

	public int getAgainst() {
		return against;
	}

	public void setAgainst(int against) {
		this.against = against;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
}
