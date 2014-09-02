package com.jitong.vote.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Vote")
public class Vote {
	public static final String TYPE_TJ="推荐";
	public static final String TYPE_PS="评审";
	
	public static final String STATUS_1_NEW="1.new";
	public static final String STATUS_2_READY="2.ready";
	public static final String STATUS_3_INPROGRESS="3.inprogress";
	public static final String STATUS_4_DONE="4.done";
	public static final String STATUS_x_READY="x.ready"; //再次投票：准备好了
	public static final String STATUS_x_INPROGRESS="x.inprogress";//再次投票：进行中
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "vote_id")
	private String id;
	
	@Column(name="name")
	private String name;
	

	@Column(name="start_date")
	private String startDate;
	
	@Column(name="start_hour")
	private String startHour;
	
	@Column(name="start_minute")
	private String startMinute;
	
	
	@Column(name="requirement")
	private String requirement;
	
	@Column(name="note")
	private String note;
	
	@Column(name="creator")
	private String creator;
	
	
	@Column(name="create_time")
	private String createTime;
	
	@Column(name="status")
	private String status;
	
	@Column(name="type")
	private String type;

	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER) 
	@JoinColumn(name = "primary_vote_id", nullable = true)
	private Vote primaryVote;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}
	public String getStatusTXT() {
		if(Vote.STATUS_1_NEW.equals(status)){
			return "新建";
		}else if(Vote.STATUS_2_READY.equals(status)){
			return "准备就绪";
		}else if(Vote.STATUS_3_INPROGRESS.equals(status)){
			return "投票中";
		}else if(Vote.STATUS_4_DONE.equals(status)){
			return "结束";
		}else if(Vote.STATUS_x_READY.equals(status)){
			return "再次投票：准备就绪";
		}else if(Vote.STATUS_x_INPROGRESS.equals(status)){
			return "再次投票：投票中";
		}else{
			return status;
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Vote getPrimaryVote() {
		return primaryVote;
	}

	public void setPrimaryVote(Vote primaryVote) {
		this.primaryVote = primaryVote;
	}

	
}
