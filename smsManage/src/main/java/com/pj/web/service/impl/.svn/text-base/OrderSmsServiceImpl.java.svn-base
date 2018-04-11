package com.pj.web.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.core.util.App.JsonRootBean;
import com.pj.core.util.GsonUtils;
import com.pj.core.util.HttpUtils;
import com.pj.core.util.MD5Util;
import com.pj.core.util.PropertyUtils;
import com.pj.web.constant.AppConstants;
import com.pj.web.dao.OrderSmsMapper;
import com.pj.web.dao.SmsSignatureMapper;
import com.pj.web.dao.UserMapper;
import com.pj.web.model.OrderSms;
import com.pj.web.model.Property;
import com.pj.web.model.SmsSignature;
import com.pj.web.model.User;
import com.pj.web.service.OrderSmsService;

@Service
public class OrderSmsServiceImpl extends GenericServiceImpl<OrderSms, Long> implements OrderSmsService {

    @Resource
    private OrderSmsMapper orderSmsMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private SmsSignatureMapper smsSignatureMapper;
    
    public GenericDao<OrderSms, Long> getDao() {
    	return orderSmsMapper;
    }

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Result sendSmsContent(HttpServletRequest request, HttpSession session) {
		Result ret = new Result();
		ret.setStatus(HttpStatusEnums.ERROR.toString());
		ret.setMessage("发送失败");
		try {
			String smsSignatureId = request.getParameter("smsSignatureId");
			String templateContent = request.getParameter("templateContent");
			String phones = request.getParameter("phones");
			phones.replaceAll("，", ",");//中文逗号替换成英文逗号（兼容误输入）
			if(phones.indexOf("，") > 0){
				//分割符必须以因为逗号为准
				return ret;
			}
			SmsSignature signa = smsSignatureMapper.selectByPrimaryKey(Long.valueOf(smsSignatureId));
			User user = (User) session.getAttribute(AppConstants.SESSION_USER);
			JsonRootBean rootBean = new JsonRootBean();
			rootBean.setAccessKey(user.getUsername());
			if(phones.indexOf(",") > 0){
				rootBean.setPhoneNumbers(phones);
			}else{
				rootBean.setPhoneNumber(phones);
			}
			rootBean.setContent("【" + signa.getSignContent() + "】" + templateContent);
			rootBean.setOutOrderId(String.valueOf(new Date().getTime()));
			rootBean.setTimestamp(String.valueOf(new Date().getTime()));
			rootBean.setSign(MD5Util.MD5Encode(rootBean.getOutOrderId()+user.getAppsecret()+rootBean.getTimestamp(), "utf-8"));
			HttpUtils httpUtils = new HttpUtils();
			//请求地址从数据字典中获取
			//http://101.132.78.201:8089/receive
			Property property = PropertyUtils.selectByParentPathAndPropertyName("system", "sendSmsUrl");
			String result = httpUtils.postStringEntity(property.getPropertyKey(), new GsonUtils().toJson(rootBean), "utf-8");
			//{"message":"操作成功","statusCode":1}
			log.info("sendSms:" + result);
			if(StringUtils.isNotEmpty(result)) {
				Map<String,Object> map = GsonUtils.fromJson(result, Map.class);
				if("1.0".equals(map.get("statusCode").toString()) || "1".equals(map.get("statusCode").toString())) {
					ret.setStatus(HttpStatusEnums.SUCCESS.toString());
					ret.setMessage("发送成功");
					return ret;
				}else {
					ret.setMessage(map.get("message").toString());
				}
			}
		} catch (Exception e) {
			log.error("sendSmsContent error:", e);
		}
		return ret;
	}

	@Override
	public String analyzeImportPhones(MultipartFile uploadExcelFile, String suffix) {
		String phones = "";
		try {
			if("xls".equals(suffix)){
				InputStream is = uploadExcelFile.getInputStream();
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				for (int numSheet = 0; numSheet < 1; numSheet++) {
		            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
		            if (hssfSheet == null) {
		                continue;
		            }
		            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		                if (hssfRow != null) {
		                	if(handleNull(hssfRow.getCell(0))==null){
		 	                	continue;
		 	                }
		                	phones += hssfRow.getCell(0).toString() + ",";
		                }
		            }
		        }
				if(StringUtils.isNotEmpty(phones)){
					phones = phones.substring(0, phones.length() - 1);
				}
			}else if("xlsx".equals(suffix)){
				InputStream is = uploadExcelFile.getInputStream();
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);  
		        for (int numSheet = 0; numSheet < 1; numSheet++) {
		        	XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
		            if (xssfSheet == null) {
		                continue;
		            }
		            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
		            	XSSFRow hssfRow = xssfSheet.getRow(rowNum);
		                if (hssfRow != null) {
		                	if(hssfRow.getCell(0)==null){
		 	                	continue;
		 	                }
		                	DecimalFormat format = new DecimalFormat("#");  
		                	String phone = format.format(hssfRow.getCell(0).getNumericCellValue()); 
		                	phones += phone + ",";
		                }
		            }
		        }
		        if(StringUtils.isNotEmpty(phones)){
					phones = phones.substring(0, phones.length() - 1);
				}
			}else if("txt".equals(suffix)){
				BufferedReader br = new BufferedReader(new InputStreamReader(uploadExcelFile.getInputStream()));
                String line = "";  
                line = br.readLine();  
               /* while (line != null) {  
                    line = br.readLine(); // 一次读入一行数据  
                }*/
                phones = line;
			}
		} catch (Exception e) {
			log.error("analyzeImportPhones error:", e);
		}
		return phones;
	}
	
	private String handleNull(HSSFCell handle) {
		String a =  handle == null ? "" : handle.getStringCellValue();
		return a;
	}

	@Override
	public Integer selectSumByYesterday(Map<String, Object> params) {
		return orderSmsMapper.selectSumByYesterday(params);
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public boolean systemSendSms(String phone, String smsContent) {
		boolean bool = false;
		try {
			Property proerty = PropertyUtils.selectByParentPathAndPropertyName("system", "systemUserId");
			User user = userMapper.selectByPrimaryKey(Long.valueOf(proerty.getPropertyKey()));
			JsonRootBean rootBean = new JsonRootBean();
			rootBean.setAccessKey(user.getUsername());
			rootBean.setPhoneNumber(phone);
			rootBean.setContent(smsContent);
			rootBean.setOutOrderId(String.valueOf(new Date().getTime()));
			rootBean.setTimestamp(String.valueOf(new Date().getTime()));
			rootBean.setSign(MD5Util.MD5Encode(rootBean.getOutOrderId()+user.getAppsecret()+rootBean.getTimestamp(), "utf-8"));
			HttpUtils httpUtils = new HttpUtils();
			//请求地址从数据字典中获取
			//http://101.132.78.201:8089/receive
			Property property = PropertyUtils.selectByParentPathAndPropertyName("system", "sendSmsUrl");
			String result = httpUtils.postStringEntity(property.getPropertyKey(), new GsonUtils().toJson(rootBean), "utf-8");
			//{"message":"操作成功","statusCode":1}
			if(StringUtils.isNotEmpty(result)) {
				Map<String,Object> map = GsonUtils.fromJson(result, Map.class);
				if("1.0".equals(map.get("statusCode").toString()) || "1".equals(map.get("statusCode").toString())) {
					bool = true;
				}
			}
		} catch (Exception e) {
			log.error("systemSendSms error:", e);
		}
		return bool;
	}

	@Override
	public List<Map<String, Object>> selectByPageList(Map<String, Object> param) {
		return orderSmsMapper.selectByPageList(param);
	}

	@Override
	public boolean manualCallback(Map<String, Object> param) {
		boolean bool = false;
		try {
			List<Map<String, Object>> orders = orderSmsMapper.selectByPageList(param);
			for (Map<String, Object> map : orders) {
				if(map.get("status") == null || "-1".equals(map.get("status").toString())
						|| "100".equals(map.get("status").toString())){
					continue;
				}
				//TODO 回调操作
			}
			bool = true;
		} catch (Exception e) {
			log.error("manualCallback error:", e);
		}
		return bool;
	}
}
