package com.jitong.vote.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.vote.VoteUtil;
import com.jitong.vote.domain.Candidate;
import com.jitong.vote.domain.Vote;
import com.jitong.vote.service.VoteService;
import com.opensymphony.xwork2.Preparable;

public class TJCandidateAction extends JITActionBase implements Preparable {
	private Vote vote;
	private Candidate candidate;
	private VoteService service;
	
	private File uploadfile;
	private String uploadfileFileName;
	private String uploadfileContentType;
	private List<Candidate> candidateList;
	
	private String qryUnit;
	private String qryXingzhengPost;
	private String qryXueli;
	
	private String newCandidateIds;
	
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new VoteService();
		}
		if (vote != null && !StringUtil.isEmpty(vote.getId())) {
			vote = (Vote) service.findBoById(Vote.class, vote.getId());
		}
		if (candidate != null && !StringUtil.isEmpty(candidate.getId())) {
			candidate = (Candidate) service.findBoById(Candidate.class, candidate.getId());
		}

	}

	public String list() throws JTException {
		return super.list();
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

		if (StringUtil.isEmpty(candidate.getId())) {
			candidate.setVote(vote);
			service.saveCandidate(candidate);
		} else {
			service.saveCandidate(candidate);
		}
		return list();
	}
	
	public String delete() throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("用户连接超时");
			return list();
		}
		
		if (candidate != null && !StringUtil.isEmpty(candidate.getId())) {
			service.deleteBo(Candidate.class,candidate.getId());
		}
		return list();
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from com.jitong.vote.domain.Candidate candidate where candidate.vote.id='"+vote.getId()+"' ";
		if(!StringUtil.isEmpty(qryUnit)){
			hql += " and candidate.unit like '%"+qryUnit+"%'";
		}
		if(!StringUtil.isEmpty(qryXingzhengPost)){
			hql += " and candidate.xingzhengPost like '%"+qryXingzhengPost+"%'";
		}
		if(!StringUtil.isEmpty(qryXueli)){
			hql += " and candidate.xueli like '%"+qryXueli+"%'";
		}
		hql +=" order by candidate.agreementNumber desc";
		return hql;
	}

	public String importForm() throws Exception {
		return "import";
	}
	
	public String uploadCandidates() throws Exception {			
			if (uploadfile != null) {
				String ext = FileTypeUtil.getExtensionByName(uploadfileFileName);
				if(!"xls".equals(ext)){
					this.addActionError("请上传扩展名为.xls的Excel文件");
					return list();
				}

				FileInputStream in = new FileInputStream(uploadfile);
				candidateList = new ArrayList<Candidate>();
				 try{
					 VoteUtil.readTJCandidateExcelFile(in, 2, 15, candidateList);
				 }catch(Exception e){
					 this.addActionError("不支持您上传的文件!");
				 }
				session.put("candidateList", candidateList);
				request.setAttribute("candidateList", candidateList);
			}
			return "import";
	}
	
	public String importCandidates() throws Exception {
		candidateList=(ArrayList<Candidate>)session.get("candidateList");
		if(candidateList!=null){
			for(Candidate c:candidateList){
				c.setVote(vote);
			}
			service.saveBoList(candidateList);
			session.put("candidateList", null);
			candidateList=null;
			this.addActionError("导入成功！");
		}
		return "import";
	}
	public String createSecondaryCandidates() throws Exception {
		if(!StringUtil.isEmpty(newCandidateIds)){
			String[] ids = newCandidateIds.split(",");
			for(String id:ids){
				Candidate pc = (Candidate)service.findBoById(Candidate.class, id);
				Candidate newCandidate = new Candidate();
				BeanUtils.copyProperties(newCandidate, pc);
				newCandidate.setPrimaryVoteCandidate(pc);
				newCandidate.setId(null);
				newCandidate.setVote(vote);
				newCandidate.setCreateTime(DateUtil.getCurrentTime());
				service.createBo(newCandidate);
			}
		}
			
		return list();
	}
	
	public String show2ndaryCandidates(){
		candidateList=new ArrayList<Candidate>();
		candidateList.add(candidate);
		if(candidate.getContain2ndCandidate()){
			candidateList.addAll(candidate.getSecondaryCandidates());
		}
		Collections.sort(candidateList, new ComparatorCandidate());
		return "candidatelist_2ndary";
	}
	
	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public String getUploadfileContentType() {
		return uploadfileContentType;
	}

	public void setUploadfileContentType(String uploadfileContentType) {
		this.uploadfileContentType = uploadfileContentType;
	}

	public String getQryUnit() {
		return qryUnit;
	}

	public void setQryUnit(String qryUnit) {
		this.qryUnit = qryUnit;
	}

	public String getQryXingzhengPost() {
		return qryXingzhengPost;
	}

	public void setQryXingzhengPost(String qryXingzhengPost) {
		this.qryXingzhengPost = qryXingzhengPost;
	}

	public String getQryXueli() {
		return qryXueli;
	}

	public void setQryXueli(String qryXueli) {
		this.qryXueli = qryXueli;
	}

	public String getNewCandidateIds() {
		return newCandidateIds;
	}

	public void setNewCandidateIds(String newCandidateIds) {
		this.newCandidateIds = newCandidateIds;
	}

	 class ComparatorCandidate implements Comparator{
		 public int compare(Object arg0, Object arg1) {
			 Candidate c0=(Candidate)arg0;
			 Candidate c1=(Candidate)arg1;
			 return c0.getCreateTime().compareTo(c1.getCreateTime());
		 }
	 }
}
