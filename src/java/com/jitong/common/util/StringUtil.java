package com.jitong.common.util;

import java.math.BigInteger;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.jitong.JitongConstants;

public class StringUtil {
	// private static final String symbols =
	// "ABCDEFGHJKLMNOPQRSTUVWXYZ012345d789abcdefghijkmnopqrstuvwx";
	private static final String symbols = "0123456789";
	private static Random random = new Random();

	public static String trim(String str) {
		if (str == null)
			return "";
		else
			return str.trim();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String fillNull(String str) {
		if (str == null)
			return "";
		return str;
	}

	/**
	 * 将字符串进行MD5加密
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String md5(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(input.getBytes());
			BigInteger bi = new BigInteger(digest);
			return bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String join(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return "";
		}
		StringBuffer sbuff = new StringBuffer(strArr[0]);
		for (int i = 1; i < strArr.length; i++) {
			sbuff.append(", ").append(strArr[i]);
		}
		return sbuff.toString();
	}

	/**
	 * 
	 * @param inStrList
	 * @param inStrDeli
	 * @return StringUtil.parseString2Array("abc,efg,hi", ",") ->
	 *         {"abc","efg","hi"}
	 */
	public static String[] parseString2Array(String inStrList, String inStrDeli) {
		String[] strRes = null;
		int iLength = 0;
		int i = 0;
		StringTokenizer strToken = new StringTokenizer(inStrList, inStrDeli);
		iLength = strToken.countTokens();
		strRes = new String[iLength];
		for (i = 0; i < iLength; i++) {
			strRes[i] = strToken.nextToken();
		}
		return strRes;
	}

	/**
	 * "true"->"1" "false"->"0" "1"->"1" "0"->"0" ""->"0" null->"0"
	 * 
	 * @param bool
	 * @return
	 */
	public static String boolString2IntString(String b) {
		String bool = StringUtil.trim(b);
		if ("".equals(bool)) {
			return JitongConstants.STRING_FALSE;
		}
		if (JitongConstants.STRING_FALSE.equals(bool)) {
			return JitongConstants.STRING_FALSE;
		}
		if (JitongConstants.STRING_TRUE.equals(bool)) {
			return JitongConstants.STRING_TRUE;
		}
		if (bool.toLowerCase().equals("false")) {
			return JitongConstants.STRING_FALSE;
		}
		if (bool.toLowerCase().equals("true")) {
			return JitongConstants.STRING_TRUE;
		}
		return JitongConstants.STRING_FALSE;
	}

	public static String randomString() {
		return randomString(6);
	}

	public static String randomString(int length) {
		char[] chars = new char[length];
		for (int i = 0; i < chars.length; i++) {
			int idx = random.nextInt(Integer.MAX_VALUE);
			chars[i] = symbols.charAt(idx % symbols.length());
		}
		return new String(chars);
	}

	public static String cutEndWithEllipsis(String str, int maxLength) {
		if (str == null)
			return null;

		int len = maxLength > 1 ? maxLength : 1;
		if (str.length() <= len) {
			return str;
		}

		String ret = str.substring(0, len);
		return ret + "...";
	}

	public static List<String> getMacAdds() throws SocketException {
		List<String> ips = new ArrayList<String>();
		Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
		while (el.hasMoreElements()) {
			NetworkInterface item = el.nextElement();
			byte[] mac = item.getHardwareAddress();
			if (mac != null && mac.length > 0) {
				String macip = "";
				for (int i = 0; i < mac.length; i++) {
					byte b = mac[i];
					int intValue = 0;
					if (b >= 0) {
						intValue = b;
					} else {
						intValue = 256 + b;
					}

					macip += Integer.toHexString(intValue);
					if (i != mac.length - 1) {
						macip += "-";
					}
				}
				ips.add(macip);
			}
		}
		return ips;
	}

	public static String lisence(String ip) {
		if(ip==null){return null;}
		String str = StringUtil.md5(ip.toLowerCase());
		String md5= StringUtil.md5(str+"stanley.java@gmail.com");
		return md5.substring(5, 15);//-36c79ce35c305e94078379262b894340
	}

	public static boolean matchLisence(String lisence){
		return true;
		/*try {
			List<String> ips=getMacAdds();
			for(String ip:ips){
				if(lisence(ip).equals(lisence)){
					return true;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		return false;*/
	} 
	
	public static void main(String[] args) throws Exception {
		// System.out.println(cutEndWithEllipsis("abcdeft",5));
		// System.out.println(StringUtil.md5("Sunr!se.123Pr0d"));
		// System.out.println(StringUtil.md5("Sunset.123$"));
		// System.out.println("abc".substring(0, 3));
		List<String> l = StringUtil.getMacAdds();
		/*System.out.println(Arrays.toString(l.toArray()));
		System.out.println(lisence("3c-97-e-ec-c9-74"));
		System.out.println(lisence("e4-1f-13-61-b9-78"));*/
		System.out.println(lisence("C8-0A-A9-DB-16-F7"));
		System.out.println(lisence("c8-0a-a9-db-16-f7"));
		//System.out.println(matchLisence("9ce35c305e"));
		//System.out.println(matchLisence("1234567890"));
		
	}

}
