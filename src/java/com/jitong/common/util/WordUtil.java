package com.jitong.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordUtil {

	public String replaceStr(String content, String oldcontent, String newcontent) {
		String rc = encodeToUnicode(newcontent);
		//String rc = StringUtil.trim(newcontent);
		oldcontent = "$" + oldcontent + "$";
		String target = content.replace(oldcontent, rc);
		return target;
	}

	public String encodeToUnicode(String str) {
		if (str == null)
			return "";
		StringBuilder sb = new StringBuilder(str.length() * 2);
		for (int i = 0; i < str.length(); i++) {
			sb.append(encodeToUnicode(str.charAt(i)));
		}
		return sb.toString();
	}

	public String encodeToUnicode(char character) {
		if (character > 255) {
			return "&#" + (character & 0xffff) + ";";
		} else {
			return String.valueOf(character);
		}
	}

	public void exportWordFile(String inputPath, String outPath, Map<String, String> data) {
		String sourname = inputPath;
		String sourcecontent = "";
		InputStream ins = null;
		try {
			 /*FileInputStream fis = new FileInputStream(sourname);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"gb2312"));
			 
			 String line; 
			 while ((line = reader.readLine()) != null) { 
			  	 sourcecontent += line; 
			 }   
			 fis.close(); */
			   
			ins = new FileInputStream(sourname);
			byte[] b = new byte[1638400];// 提高对文件的读取速度，特别是对于1M以上的文件
			if (!new File(sourname).isFile()) {
				System.out.println("源模板文件不存在");
				return;
			}
			int bytesRead = 0;
			while (true) {
				bytesRead = ins.read(b, 0, 1638400);
				
				if (bytesRead == -1) {
					System.out.println("读取模板文件结束");
					break;
				}
				sourcecontent += new String(b, 0, bytesRead,"GBK");
				//sourcecontent += new String(b, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(sourcecontent);
		String targetcontent = sourcecontent;
		String oldText = "";
		Object newValue;
		try {
			Iterator<String> keys = data.keySet().iterator();
			//int keysfirst = 0;
			while (keys.hasNext()) {
				oldText = (String) keys.next();
				newValue = data.get(oldText);
				String newText = StringUtil.fillNull((String) newValue);
				targetcontent = replaceStr(targetcontent, oldText, newText);
				/*if (keysfirst == 0) {
					targetcontent = replaceStr(sourcecontent, oldText, newText);
					keysfirst = 1;
				} else {
					targetcontent = replaceStr(targetcontent, oldText, newText);
					keysfirst = 1;
				}*/
			}

			/*FileWriter fw = new FileWriter(outPath, true);
			PrintWriter out = new PrintWriter(fw);
			if (targetcontent.equals("") || targetcontent == "") {
				out.println(sourcecontent);
			} else {
				out.println(targetcontent);
			}
			out.close();
			fw.close();*/
			
			FileOutputStream out = new FileOutputStream(new File(outPath));
			byte[] targetBytes= targetcontent.getBytes("GBK");
			out.write(targetBytes);
			out.close();
			
			/*System.out.println(outPath + " 生成文件成功");
			System.out.println(targetcontent);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hostDept", "科技部");
		map.put("bidItemName", "买电脑");
		
		String inUrl = "c:\\tmp\\setup_template.mht";
		String outUrl = "C:\\tmp\\setup_template.doc";
		File fileOut = new File(outUrl);
		File fileIn = new File(inUrl);
		if (fileOut.exists()) {
			fileOut.delete();
			System.out.println(inUrl + "文件已存在，已删除！");
		}
		if (!fileIn.exists()) {
			return;
		}
		new WordUtil().exportWordFile(inUrl, outUrl, map);

	}
}
