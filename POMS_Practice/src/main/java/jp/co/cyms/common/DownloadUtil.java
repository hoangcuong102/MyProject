package jp.co.cyms.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * This is DownloadUtil Class Service process download file when received
 * request
 * 
 * @since 2015/07/03
 * @author hungnt
 * */
@SuppressWarnings("javadoc")
public class DownloadUtil {
	/**
	 * Processing return response file when received request
	 * 
	 * @since 2015/24/06
	 * @param String
	 *            :fullPathFile Full Path file in server
	 * @param String
	 *            :filename name file when download
	 * @param HttpServletResponse
	 *            :response
	 * @return boolean (true: stream file success, false: stream file fail)
	 * @throws FileNotFoundException
	 *             ,UnsupportedEncodingException,IOException
	 * */
	public static Map<String, Object> responseFile(String fullPathFile, String filename,
	                HttpServletResponse response) {
		File downloadFile = new File(fullPathFile);
		FileInputStream inputStream;
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		try {
			inputStream = new FileInputStream(downloadFile);
			// responseのヘッダをセット
			String headerKey = CommonDefineIF.HEADERKEY_DOWNLOAD_FILE;
			String finalName = filename + "_" + DateUtil.doGetStringCurrentDateWithFormat("yyyyMMddhhmmss")
			                + Constant.XLSX_EXTENSION;
			// （１０）シート名の変更 & Encode ファイル名
			String fileNameEncode = URLEncoder.encode(
			                finalName, CommonDefineIF.ENCODE_UTF8);
			String headerValue = String.format(
			                CommonDefineIF.HEADER_VALUE_DOWNLOAD_FILE, fileNameEncode);
			response.setHeader(headerKey, headerValue);
			response.setContentType(CommonDefineIF.CONTENT_TYPE);
			response.setHeader(CommonDefineIF.CONTENT_LENGTH, String.valueOf(downloadFile.length()));
			response.setBufferSize(CommonDefineIF.BUFFER_STREAM_FILE);
			// responseのoutput stream を取得
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[CommonDefineIF.BUFFER_STREAM_FILE];
			int bytesRead = CommonDefineIF.BYTE_END_FILE;
			// ファイルデータをoutput streamに書きこむ
			while ((bytesRead = inputStream.read(buffer)) != CommonDefineIF.BYTE_END_FILE) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			rtnMap.put("success", true);
			rtnMap.put("errorCode", "");
			return rtnMap;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			rtnMap.put("success", false);
			//rtnMap.put("errorCode", ErrorDefineIF.ERR_010);
			return rtnMap;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			rtnMap.put("success", false);
			//rtnMap.put("errorCode", ErrorDefineIF.ERR_010);
			return rtnMap;
		}
		catch (IOException e) {
			e.printStackTrace();
			rtnMap.put("success", false);
			//rtnMap.put("errorCode", ErrorDefineIF.ERR_010);
			return rtnMap;
		}
	}

	/**
	 * Processing return response file when received request
	 * 
	 * @param fullPathFile
	 * @param filename
	 * @param response
	 * @param rtnMap
	 * 
	 * @since 2015/24/06
	 * @param String
	 *            :fullPathFile Full Path file in server
	 * @param String
	 *            :filename name file when download
	 * @param HttpServletResponse
	 *            :response
	 * @return boolean (true: stream file success, false: stream file fail)
	 * @throws FileNotFoundException
	 *             ,UnsupportedEncodingException,IOException
	 */
	public static Map<String, Object> responseFile(String fullPathFile, String filename,
	                HttpServletResponse response, Map<String, Object> rtnMap) {
		rtnMap.put("success", true);
		rtnMap.put("errorCode", "");
		return rtnMap;
	}
}

