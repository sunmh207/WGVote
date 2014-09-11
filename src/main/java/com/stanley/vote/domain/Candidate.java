package com.stanley.vote.domain;

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

import com.stanley.common.form.JTField;
import com.stanley.common.util.DateUtil;

@Entity
@Table(name = "Candidate")
public class Candidate {
	
	public Candidate() {
		super();
		this.createTime = DateUtil.getCurrentTime();
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "candidate_id")
	private String id;
	
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "gender")
	private String gender;
	
	
	@Column(name = "birth")
	private String birth;
	
	//单位
	
	@Column(name = "unit")
	private String unit;
	
	//部门
	
	@Column(name = "dept")
	private String dept;
	
	
	@Column(name = "university")
	private String university;
	
	
	@Column(name = "major")
	private String major;
	
	//学历
	
	@Column(name = "xueli")
	private String xueli;
	
	
	@Column(name = "degree")
	private String degree;
	
	
	@Column(name = "work_Years")
	private String workYears;
	
	
	@Column(name = "recommend_Unit")
	private String recommendUnit;
	
	//行政职务
	
	@Column(name = "xingzheng_Post")
	private String xingzhengPost;
	
	//行政职务级别
	
	@Column(name = "xingzheng_level")
	private String xingzhengLevel;
	
	
	@Column(name = "tech_post")
	private String techPost;
	
	
	@Column(name = "tech_post_obtain_date")
	private String techPostObtainDate;
	
	
	@Column(name = "work_major")
	private String workMajor;
	
	
	@Column(name = "graduate_year")
	private String graduateYear;

	@Column(name = "create_time")
	private String createTime;
	
	@Column(name = "agreement_number")
	private int agreementNumber;
	
	@Column(name = "abstention_number")
	private int abstentionNumber;
	
	
	@Column(name = "against_number")
	private int againstNumber;
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER) 
	@JoinColumn(name = "vote_id", nullable = false)
	private Vote vote;
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER) 
	@JoinColumn(name = "primary_vote_candidate_id", nullable = true)
	private Candidate primaryVoteCandidate;
	
	@OneToMany(mappedBy="primaryVoteCandidate",fetch=FetchType.EAGER,  cascade={CascadeType.PERSIST,CascadeType.REFRESH})  
	private Set<Candidate> secondaryCandidates;
	
	/*@OneToMany(mappedBy="candidate",fetch=FetchType.EAGER,  cascade={CascadeType.PERSIST,CascadeType.REFRESH})  
	private Set<Ticket> tickets;*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JTField(chineseName="姓名",order=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JTField(chineseName="性别",order=5)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@JTField(chineseName="出生日期",order=10)
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	@JTField(chineseName="单位",order=15)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@JTField(chineseName="部门",order=20)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	@JTField(chineseName="大学",order=25)
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
	@JTField(chineseName="专业",order=30)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	@JTField(chineseName="学历",order=35)
	public String getXueli() {
		return xueli;
	}

	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	@JTField(chineseName="学位",order=40)
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	@JTField(chineseName="工作年限",order=45)
	public String getWorkYears() {
		return workYears;
	}

	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}
	@JTField(chineseName="推荐单位",order=50)
	public String getRecommendUnit() {
		return recommendUnit;
	}

	public void setRecommendUnit(String recommendUnit) {
		this.recommendUnit = recommendUnit;
	}
	@JTField(chineseName="行政职务",order=55)
	public String getXingzhengPost() {
		return xingzhengPost;
	}

	public void setXingzhengPost(String xingzhengPost) {
		this.xingzhengPost = xingzhengPost;
	}
	@JTField(chineseName="级别",order=60)
	public String getXingzhengLevel() {
		return xingzhengLevel;
	}

	public void setXingzhengLevel(String xingzhengLevel) {
		this.xingzhengLevel = xingzhengLevel;
	}
	@JTField(chineseName="技术职务名称",order=65)
	public String getTechPost() {
		return techPost;
	}

	public void setTechPost(String techPost) {
		this.techPost = techPost;
	}
	@JTField(chineseName="首评(取得)年月",order=70)
	public String getTechPostObtainDate() {
		return techPostObtainDate;
	}

	public void setTechPostObtainDate(String techPostObtainDate) {
		this.techPostObtainDate = techPostObtainDate;
	}
	@JTField(chineseName="从事专业",order=75)
	public String getWorkMajor() {
		return workMajor;
	}

	public void setWorkMajor(String workMajor) {
		this.workMajor = workMajor;
	}
	@JTField(chineseName="毕业年限",order=80)
	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}
	@JTField(chineseName="同意",order=85)
	public int getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(int agreementNumber) {
		this.agreementNumber = agreementNumber;
	}
	@JTField(chineseName="弃权",order=90)
	public int getAbstentionNumber() {
		return abstentionNumber;
	}

	public void setAbstentionNumber(int abstentionNumber) {
		this.abstentionNumber = abstentionNumber;
	}
	@JTField(chineseName="反对",order=95)
	public int getAgainstNumber() {
		return againstNumber;
	}

	public void setAgainstNumber(int againstNumber) {
		this.againstNumber = againstNumber;
	}

	public Candidate getPrimaryVoteCandidate() {
		return primaryVoteCandidate;
	}

	public void setPrimaryVoteCandidate(Candidate primaryVoteCandidate) {
		this.primaryVoteCandidate = primaryVoteCandidate;
	}

	public Set<Candidate> getSecondaryCandidates() {
		return secondaryCandidates;
	}

	public void setSecondaryCandidates(Set<Candidate> secondaryCandidates) {
		this.secondaryCandidates = secondaryCandidates;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean getContain2ndCandidate(){
		if(secondaryCandidates==null||secondaryCandidates.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	public String getContain2ndCandidateTXT(){
		if(getContain2ndCandidate()){
			return "是";
		}else{
			return "否";
		}
	}
	
	/**
	 * 最新一次投票的Candidate信息
	 * @return
	 */
	public Candidate getLatestCandidate(){
		if(!getContain2ndCandidate()){
			return this;
		}else{
			Candidate tmpC=this;
			for(Candidate c:secondaryCandidates){
				if(tmpC.getCreateTime().compareTo(c.getCreateTime())<0){
					tmpC = c;
				}
			}
			return tmpC;
		}		
	}
	/*public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}*/
	

}
