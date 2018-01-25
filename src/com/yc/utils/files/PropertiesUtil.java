package com.yc.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties properties= new Properties();
    
    /*properties文件名*/
    public static final String PROPERTIES_ACCESS_TOKEN="accessToken.properties";
    public static final String ACCESS_TOKEN="access_token";
    public static final String EXPIRATION_TIME="expires_in";
    
    
    /**
     * 初始化properties,即载入数据
     */
    private static void initAccessTokenProperties(InputStream ips){
        try {
            properties.load(ips);
            ips.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringAccessTokenKey(InputStream ips){
    	if(properties.isEmpty()){
    		initAccessTokenProperties(ips);
    	}
    	return properties.getProperty(ACCESS_TOKEN);
    }
    public static Long getLongAccessTokenKey(InputStream ips){
    	if(properties.isEmpty()){
    		initAccessTokenProperties(ips);
    	}
    	return Long.parseLong(properties.getProperty(EXPIRATION_TIME));
    }
    
    public static void saveKey(String fileName,String key,String value,URL fileUrl,InputStream ips){
    	if(properties.isEmpty()){
    		initAccessTokenProperties(ips);
    	}
    	properties.setProperty(key, value);
      //保存文件
      try {
          FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()),false);
          properties.store(fos,"");
          fos.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    /**
     * 
     * @param pathname:文件绝对路径
     * @param keyName:要获取key
     * @return
     */
	@SuppressWarnings("unused")
	public static String getPropertiesKey(String pathname,String keyName){
		String value = "";
		File file = new File(pathname);
		if(file==null){
			return null;
		}
		try {
		 	FileInputStream in = null;
		 	Properties pp = new Properties();
	        try{
	            in = new FileInputStream(file);
	            pp.load(in);
	            value = pp.getProperty(keyName);
	        } catch (FileNotFoundException e){
	            e.printStackTrace();
	        }finally{
	        	pp = null;
	        	in = null;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
    public static void main(String[] args) {
    	System.out.println(getPropertiesKey("D:\\wechar-openid\\esb-sap.properties", "url_esb"));
    	System.out.println(getPropertiesKey("D:\\wechar-openid\\esb-sap.properties", "application"));
    	System.out.println(getPropertiesKey("D:\\wechar-openid\\esb-sap.properties", "secret_key"));
//    	InputStream ips = PropertiesUtil.class.getResourceAsStream(PROPERTIES_ACCESS_TOKEN);
    	/*FileUtil.mkdir("d:/wechar-openid/");
    	File f= new File("d:/wechar-openid/"+PROPERTIES_ACCESS_TOKEN);
    	if(!f.exists()){
    		List<String> list = new ArrayList<String>();
    		list.add(ACCESS_TOKEN+"=init");
    		list.add(EXPIRATION_TIME+"=7200");
    		try {
				FileUtil.listToFile(list, "d:/wechar-openid/"+PROPERTIES_ACCESS_TOKEN);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    	InputStream ips = null;
    	try {
			ips = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String accessToken = getStringAccessTokenKey(ips);
    	Long expirationTime = getLongAccessTokenKey(ips);
    	System.out.println("修改前:"+accessToken+"=="+expirationTime);
    	
    	URL fileUrl = PropertiesUtil.class.getResource(PROPERTIES_ACCESS_TOKEN);//得到文件路径
//    	URL fileUrl = null;
		try {//file:/d:/wechar-openid/accessToken.properties
			fileUrl = new URL("file:/d:/wechar-openid/"+PROPERTIES_ACCESS_TOKEN);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	saveKey(PROPERTIES_ACCESS_TOKEN, ACCESS_TOKEN, "-WkJnO-B03PC2JxzL3oz4--SyoG-1qAwSTk73PF7JQJe-dzOmtr68LRPipqtUAZMm9ML_Mm1-IzrJCiOd7YuGVd2e7qD1cPcjUmqgVJ8lp8METJScAJAXNW",fileUrl,ips);
    	saveKey(PROPERTIES_ACCESS_TOKEN, EXPIRATION_TIME, "77666",fileUrl,ips);
    	accessToken = getStringAccessTokenKey(ips);
    	expirationTime = getLongAccessTokenKey(ips);
    	System.out.println("修改后:"+accessToken+"=="+expirationTime);*/
    }
}
