package com.stanley.vote.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.stanley.common.JitongConstants;
import com.stanley.common.util.StringUtil;

@Entity
@Table(name = "Voter")
public class Voter {	
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "voter_id")	
	private String id;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="host")
	private String host;
	
	@Column(name="ready_time")
	private String readyTime;
	
	@Column(name="submit")
	private String submit;
	
	@Column(name="submit_time")
	private String submitTime;

	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER) 
	@JoinColumn(name = "vote_id", nullable = false)
	private Vote vote;
	
	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSubmit() {
		return submit;
	}
	public String getSubmitTXT() {
		if("1".equals(submit)){
			return "是";
		}else{
			return "否";
		}
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public boolean drop(){
		if(!StringUtil.isEmpty(readyTime)){
			SimpleDateFormat df = new SimpleDateFormat(JitongConstants.JT_DATETIME_FORMAT);
			Date lastTime ;
			try{
				lastTime=df.parse(readyTime);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			Date currTime = Calendar.getInstance().getTime();
			long diff= (currTime.getTime()-lastTime.getTime())/1000;
			if(diff>20){
				return true;
			}			
		}
		return false;
	}
	public String getDropTXT(){
		if(drop()){
			return "可能已掉线";
		}else{
			return "在线";
		}
	}
}
