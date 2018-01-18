package com.yc.utils.files;

import java.io.File;
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
    public static void main(String[] args) {
    	InputStream ips = PropertiesUtil.class.getResourceAsStream(PROPERTIES_ACCESS_TOKEN);
    	
    	String accessToken = getStringAccessTokenKey(ips);
    	Long expirationTime = getLongAccessTokenKey(ips);
    	System.out.println("修改前:"+accessToken+"=="+expirationTime);
    	
    	URL fileUrl = PropertiesUtil.class.getResource(PROPERTIES_ACCESS_TOKEN);//得到文件路径
    	saveKey(PROPERTIES_ACCESS_TOKEN, ACCESS_TOKEN, "-WkJnO-B03PC2JxzL3oz4--SyoG-1qAwSTk73PF7JQJe-dzOmtr68LRPipqtUAZMm9ML_Mm1-IzrJCiOd7YuGVd2e7qD1cPcjUmqgVJ8lp8METJScAJAXNW",fileUrl,ips);
    	saveKey(PROPERTIES_ACCESS_TOKEN, EXPIRATION_TIME, "77666",fileUrl,ips);
    	accessToken = getStringAccessTokenKey(ips);
    	expirationTime = getLongAccessTokenKey(ips);
    	System.out.println("修改后:"+accessToken+"=="+expirationTime);
    }
}
