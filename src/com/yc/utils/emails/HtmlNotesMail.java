package com.yc.utils.emails;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class HtmlNotesMail {
	private static String encodeing = "UTF-8";
	private static String host = "smtp.jac.com.cn";
	private static String userName = "ccgs.wlgs@jac.com.cn";
	private static String password = "Hello123";
	private static String from = "ccgs.wlgs@jac.com.cn";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test002();
	}
	@SuppressWarnings("unused")
	private static void test001(){
		// TODO Auto-generated method stub
		String address = "yongcheng_min@163.com,309831731@qq.com";//sunxiaojing0628@126.com
		String subject = "节点消息";
		// set the html message
		String head = "每日计划汇总(2017-12-12)";
		String[] cloumns = new String[]{
			"产线","备料完成率","发运完成率"
		};
		int size = cloumns.length;
		List<String[]> values = new ArrayList<String[]>(size);
		values.add(new String[]{
			"帅铃北线","98.08%","66.13%"	
		});
		values.add(new String[]{
			"骏铃南线","97.90%","95.50%"	
		});
		values.add(new String[]{
			"帅铃北线","98.08%","66.13%"	
		});
		values.add(new String[]{
			"帅铃北线","98.08%","66.13%"	
		});
		try {
			from = "wechar.email@jqwl.com";
			HtmlNotesMail.sendHtmlNotes(subject, host, from,address,head,cloumns,values,size);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	private static void test002(){
		// TODO Auto-generated method stub
		String address = "yongcheng_min@163.com,309831731@qq.com";//sunxiaojing0628@126.com
		String subject = "蔚来汽车节点消息";
		// set the html message
		String head = "节点消息(20171212)";
		String[] cloumns = new String[]{
			"VIN","状态"
		};
		int size = cloumns.length;
		List<String[]> values = new ArrayList<String[]>(size);
		values.add(new String[]{
			"ES8000024H7700030","入库"	
		});
		try {
			from = "wechar.email@jqwl.com";
			HtmlNotesMail.sendHtmlNotes(subject, host, from,address,head,cloumns,values,size);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param subject:主题
	 * @param host
	 * @param from
	 * @param address:邮箱字符串英文逗号分割
	 * @param head:头
	 * @param cloumns:明细列头名称
	 * @param values:明细列内容
	 * @param size:几列
	 * @throws EmailException
	 * @throws MalformedURLException
	 */
	public static String sendHtmlNotes(String subject,String host,String from,String address//邮件主题
			,String head,String[] cloumns,List<String[]> values,int size) //邮件内容
					throws EmailException, MalformedURLException{
		String emails[] = address.split(",");
		// embed the image and get the content id
		//http://www.jac.com.cn/r/cms/www/red/images/logo.png
		//http://www.apache.org/images/asf_logo_wide.gif
		for(int i=0;i<emails.length;i++){
			if(emails[i].contains("@")){
				HtmlEmail email = new HtmlEmail();
				email.setHostName(host);
				email.addTo(emails[i]);
				email.setFrom(from);
				email.setCharset(encodeing);
				email.setAuthentication(userName, password);
				email.setSubject(subject);
				// set the html message
				email.setHtmlMsg(getHtmlNotesMsg(head, cloumns, values,size));
				// set the alternative message
				email.setTextMsg("Your email client does not support HTML messages");
				// send the email
				email.send();
			}
		}
		return null;
	}
	
	//,
	/**
	 * 
	 * @param head:头
	 * @param cloumns:明细列头名称
	 * @param values:明细列内容
	 * @param size:几列
	 * @return
	 */
	private static String getHtmlNotesMsg(String head,String[] cloumns,List<String[]> values
			,int size){
		StringBuffer str = new StringBuffer();
		str.append("<TABLE border=1 cellspacing=0 style='border:1px solid blue'>");
		
		str.append("<TR>");
		str.append("<TD colspan=3>"+head+"");
		str.append("</TD>");
		str.append("</TR>");
		
		str.append("<TR>");
		for(int i = 0 ; i<size ; i++){
			str.append("<TD style='border:1px solid black'>"+cloumns[i]);
			str.append("</TD>");
		}
//		str.append("<TD style='border:1px solid black'>"+cloumns[0]);
//		str.append("</TD>");
//		str.append("<TD style='border:1px solid black'>"+cloumns[1]);
//		str.append("</TD>");
//		str.append("<TD style='border:1px solid black'>"+cloumns[2]);
//		str.append("</TD>");
		
		str.append("</TR>");
		for(int i = 0 ; i<values.size() ; i++){
			String[] v = values.get(i);
			for(int j = 0 ; j<size ; j++){
				str.append("<TD style='border:1px solid black'>"+v[j]+"");
				str.append("</TD>");
			}
//			str.append("<TD style='border:1px solid black'>"+v[0]+"");
//			str.append("</TD>");
//			str.append("<TD style='border:1px solid black'>"+v[1]+"");
//			str.append("</TD>");
//			str.append("<TD style='border:1px solid black'>"+v[2]+"");
//			str.append("</TD>");
			
			str.append("</TR>");
		}
		str.append("</TABLE>");
		return str.toString();
	}
}
