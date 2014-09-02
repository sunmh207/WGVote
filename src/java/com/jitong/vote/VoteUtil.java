package com.jitong.vote;

import java.io.InputStream;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.vote.domain.Candidate;

public class VoteUtil {
	public static String readTJCandidateExcelFile(InputStream in, int startRow, int expectedCols,List<Candidate> returnCandidates)throws JTException {
		StringBuffer errBuffer=new StringBuffer();
		try {
			Workbook wbook = null;
			wbook = Workbook.getWorkbook(in);
			//Get the first Sheet
			Sheet sheet = wbook.getSheet(0);
			int rows = sheet.getRows(); // sheet rows
			if (rows == 0 || rows < startRow) {
				errBuffer.append("导入的文件内容为空<br>");

			}
			int columns = sheet.getColumns(); // sheet columns
			if (columns != expectedCols && (columns - 1) != expectedCols) {
				errBuffer.append("导入的excel文件的列数不等于规定的导入列数,必须为" + expectedCols + "列数据<br>");
			}

			// Start to read excel
			boolean invalidRow;
			for (int i = startRow; i < rows; i++) {// rows
				invalidRow=false;
				Candidate candidate = new Candidate();		
				
				for (int j = 0; j < expectedCols; j++) { // 列
					String value = StringUtil.fillNull(sheet.getCell(j, i).getContents().trim());
					if (j == 0) {// name	
						if(value==null||value.trim().length()==0){
							invalidRow=true;
							break;//姓名为空，则跳过
						}
						candidate.setName(value);
					} else if(j==1){
						candidate.setGender(value);
					}else if(j==2){
						candidate.setBirth(value);
					}else if(j==3){
						candidate.setRecommendUnit(value);
					}else if(j==4){
						candidate.setUnit(value);
					}else if(j==5){
						candidate.setXingzhengPost(value);
					}else if(j==6){
						candidate.setXingzhengLevel(value);
					}else if(j==7){
						candidate.setTechPost(value);
					}else if(j==8){
						candidate.setTechPostObtainDate(value);
					}else if(j==9){
						candidate.setWorkMajor(value);
					}else if(j==10){
						candidate.setXueli(value);
					}else if(j==11){
						candidate.setGraduateYear(value);
					}else if(j==12){
						candidate.setMajor(value);
					}else if(j==13){
						candidate.setGraduateYear(value);
					}else if(j==14){
						candidate.setDegree(value);
					}
				}
				if(!invalidRow){
					returnCandidates.add(candidate);
				}
			}

		} catch (Exception ex) {
			throw new JTException("读取Excel文件错误", VoteUtil.class);
		}
		return errBuffer.toString();
	}
	public static String readPSCandidateExcelFile(InputStream in, int startRow, int expectedCols,List<Candidate> returnCandidates)throws JTException {
		StringBuffer errBuffer=new StringBuffer();
		try {
			Workbook wbook = null;
			wbook = Workbook.getWorkbook(in);
			//Get the first Sheet
			Sheet sheet = wbook.getSheet(0);
			int rows = sheet.getRows(); // sheet rows
			if (rows == 0 || rows < startRow) {
				errBuffer.append("导入的文件内容为空<br>");

			}
			int columns = sheet.getColumns(); // sheet columns
			if (columns != expectedCols && (columns - 1) != expectedCols) {
				errBuffer.append("导入的excel文件的列数不等于规定的导入列数,必须为" + expectedCols + "列数据<br>");
			}

			// Start to read excel
			boolean invalidRow;
			for (int i = startRow; i < rows; i++) {// rows
				invalidRow=false;
				Candidate candidate = new Candidate();		
				
				for (int j = 0; j < expectedCols; j++) { // 列
					String value = StringUtil.fillNull(sheet.getCell(j, i).getContents().trim());
					if (j == 0) {// name	
						if(value==null||value.trim().length()==0){
							invalidRow=true;
							break;//姓名为空，则跳过
						}
						candidate.setName(value);
					} else if(j==1){
						candidate.setRecommendUnit(value);
					}else if(j==2){
						candidate.setDept(value);
					}else if(j==3){
						candidate.setUniversity(value);
					}else if(j==4){
						candidate.setMajor(value);
					}else if(j==5){
						candidate.setXueli(value);
					}else if(j==6){
						candidate.setWorkYears(value);
					}
				}
				if(!invalidRow){
					returnCandidates.add(candidate);
				}
			}

		} catch (Exception ex) {
			throw new JTException("读取Excel文件错误", VoteUtil.class);
		}
		return errBuffer.toString();
	}
	public static void main(String[] args){
		for(int i=0;i<=5;i++){
			for(int j=0;j<=3;j++){
				System.out.println(i+","+j);
				if(j==2){
					break;
				}
			}
		}
	}
}
