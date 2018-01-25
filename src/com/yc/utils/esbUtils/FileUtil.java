package com.yc.utils.esbUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FileUtil {
	public static String enter = "\r\n";
	public static void listToFile(List<Long> list) throws IOException
	{
		 File f=new File("d:/1.txt");
		 BufferedWriter bw=new BufferedWriter(new FileWriter(f));
	     for(int i=0;i<list.size();i++){
	         bw.append((Long.toString(list.get(i))));
	         bw.newLine();
	     }
	     bw.close();
	}
	
	
	
	public static void fileToList() throws IOException
	{
		Map<String,String> map = new HashMap<String,String>();
		 String lineinfo="";
		 File f=new File("d:/1.txt");
		 if(!f.exists()){
			 System.out.println("file not exits");
		 }
		 BufferedReader br=new BufferedReader(new FileReader(f));
		 while((lineinfo = br.readLine()) != null)
		 {
			 map.put(lineinfo, lineinfo);
		 }
	     br.close();
	     System.out.println("map Size = "+map.size());
	}
	//"d:/openid.txt"
	public static List<String> fileToList(String pathname) throws IOException
	{
		List<String> list = new ArrayList<String>();
		String lineinfo="";
		File f=new File(pathname);
		BufferedReader br=new BufferedReader(new FileReader(f));
		while((lineinfo = br.readLine()) != null)
		{
			list.add(lineinfo.trim());
		}
	    br.close();
		return list;
	}
	//"d:/openid_nickname.txt"
	public static void listToFile(List<String> list,String pathname) throws IOException
	{
		 File f=new File(pathname);
		 BufferedWriter bw=new BufferedWriter(new FileWriter(f));
	     for(int i=0;i<list.size();i++){
	         bw.append(list.get(i));
	         bw.newLine();
	     }
	     bw.close();
	}
	public static File mkdir(String path){
    	File file =new File(path);    
    	//如果文件夹不存在则创建    
    	if  (!file .exists()  || !file .isDirectory()){       
    	    file .mkdir();    
    	}
    	return file;
    }
	
	public static String readStrTxt(File file,String encoding){
    	//jt.readTxt(new File("C:/Users/Administrator/Desktop/aaaaa.txt"));
    	BufferedReader br = null;
    	String sql = "";
    	StringBuffer sb = new StringBuffer();
    	try {
    		InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
    		br = new BufferedReader(read);
    		String temp = null;
    		while ((temp = br.readLine()) != null) {
    			if("".equals(temp)){
    				continue;
    			}
    			StringTokenizer st = new StringTokenizer(temp);
    			StringBuffer sbTemp = new StringBuffer();
    			while(st.hasMoreElements()){
    				String num = st.nextToken("\t").trim();
    				sbTemp.append(num);
    			}
    			if(sbTemp.length()>0){
    				sb.append(sbTemp+enter);
    			}
    		}
    		sql = StringUtils.substringBeforeLast(sb.toString(), enter);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != br){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sql;
    }
	public static void createTxt(String file,String row,String character){
    	OutputStreamWriter osw = null;
    	try {
    		osw = new OutputStreamWriter(new FileOutputStream(file,false),character);//设置成true就是追加,false就是覆盖
    		osw.write(row);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	       	 if(osw!=null){ 
	       		try {
	             	osw.close();
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	       	 }
	    }
    }
	public static void main(String []args)
	{
		try {
			List<Long> list = new ArrayList<Long>();
			list.add(1L);list.add(2L);list.add(1L);list.add(2L);
			listToFile(list);
			System.out.println(list.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	
	

}
