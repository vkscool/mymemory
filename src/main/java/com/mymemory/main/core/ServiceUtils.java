package com.mymemory.main.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mymemory.exceptions.DependencyException;

public class ServiceUtils {

	private static final Logger logger = LogManager.getLogger(ServiceUtils.class);
	private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private ServiceUtils(){
		//This Class Cannot be Instantiated
	}
	
	public static boolean isNullLength(String str) {
		if ((str == null) || (str.trim().length() < 1))
			return true;
		return false;
	}
	
	public static String getEncodedString(String str) throws DependencyException {
		if (str == null)
			return "";
		else {
			String encStr = null;
			try {
				encStr = java.net.URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("exception occurred while encoding " + str, e);
				throw new DependencyException(e.getMessage());
			}
			return encStr;
		}
	}

	public static String getDecodedString(String str) throws DependencyException {
		if (str == null)
			return "";
		else {
			String decStr = null;
			try {
				decStr = java.net.URLDecoder.decode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("exception occurred while decoding " + str, e);
				throw new DependencyException(e.getMessage());
			}
			return decStr;
		}
	}

	public static ArrayList getArrayListFromString(String sInput, String sDelimiter) {
		if ((isNullLength(sInput)) || (isNullLength(sDelimiter))) {
			logger.debug("getArrayListFromString is called with sInput-" + sInput + ",sDelimiter-" + sDelimiter);
			return null;
		}
		String sVals[] = sInput.split(",");
		String sTmp = null;
		ArrayList opList = null;
		if ((sVals != null) && (sVals.length > 0)) {
			opList = new ArrayList();
			for (int i = 0; i < sVals.length; i++) {
				sTmp = sVals[i];
				if (!isNullLength(sTmp)) {
					if (!opList.contains(sTmp.trim())) {
						opList.add(sTmp.trim());
					}
				}
			}
		}
		return opList;
	}

	public static String getHexID(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(str.getBytes());
				MessageDigest tc1 = (MessageDigest) md.clone();
				byte[] sDigest = tc1.digest();
				return toHexString(sDigest);
			} catch (CloneNotSupportedException cnse) {
				logger.error("Exception:couldnt make digest of partial content for :" + str + " Reason:"
						+ cnse.getMessage());
			}
		} catch (NoSuchAlgorithmException nsae) {
			logger.error("Exception:couldnt make digest of partial content for :" + str + " Reason:"
					+ nsae.getMessage());
		}

		return null;
	}

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			// look up high nibble char
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String checkAndReplace(String str, String placeholder, String value) {
		String retStr = str;
		if (str != null) {
			if (str.indexOf(placeholder) > -1) {
				logger.debug("replacing " + placeholder + " with " + value);
				String encodedValue = value;
				try {
					encodedValue = URLEncoder.encode(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error(e.getMessage(), e);
				}
				retStr = str.replaceAll(Pattern.quote(placeholder), encodedValue);
				logger.debug("url after replacing " + placeholder + " = " + retStr);
			}
		}
		return retStr;
	}

	public static String checkAndReplaceWith$(String str, String placeholder, String value) {
		String retStr = str;
		if (str != null) {
			placeholder = "$" + placeholder + "$";
			if (str.indexOf(placeholder) > -1) {
				logger.debug("replacing " + placeholder + " with " + value);
				retStr = str.replace(placeholder, value);
				logger.debug("url after replacing " + placeholder + " = " + retStr);
			}
		}
		return retStr;
	}
	
	public static boolean checkBooleanValue(String value) {
		boolean flag = false;
		try {
			flag = Boolean.valueOf(value);
		} catch (Exception e) {
			flag = false;
			logger.error("Error for high/low resolution content :" + e.getMessage());
		}
		logger.debug("Is High content resolution required : " + flag);
		return flag;

	}
}
