package com.yc.utils.esbUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
	
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
	
	public static void main(String []args)
	{
		try {
			fileToList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
