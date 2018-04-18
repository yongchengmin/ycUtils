package com.yc.utils.esbUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
/**yc.mim*/
public class JsonTools{

	public JsonTools(){
	}

	public static String getCreateJson(String key, Object value){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);

		return jsonObject.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String getCreateJson(Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		return jsonObject.toString();
		//{"key1":"value01","key2":"value02","key3":"value03"}
	}
	
	//{"code":0,"message":"","data":{"message":"数据格式不正确","status":101}}
	public static Boolean getJsonKey(String jsondata){
		JSONObject jsonObject = JSONObject.fromObject(jsondata);  

    	String code = jsonObject.getString("code");
        if(!code.equals("0")){
        	return false;
        }
        
        String data = jsonObject.getString("data");
        JSONObject dataObject = JSONObject.fromObject(data);
        String status = dataObject.getString("status");
//        String message = dataObject.getString("message");
        if(!status.equals("0")){
        	return false;
        }
		return true;
    
	}
	//{"errcode":0,"errmsg":"ok","msgid":108925056251363330}
	/**
	 * jsondata:是否有错误的返回json
	 * errcode:成功码
	 * errorValue:成功值
	 * */
	public static Boolean isError(String jsondata,String errcode,String errorValue){
		if(jsondata==null){
			 return true;
		}
		JSONObject jsonObject = JSONObject.fromObject(jsondata);  
    	String code = jsonObject.getString(errcode);
        if(!code.equals(errorValue)){
        	return true;
        }
        return false;
	}
	public static String getJsonKey(String jsondata,String errcode){
		if(jsondata==null){
			 return null;
		}
		JSONObject jsonObject = JSONObject.fromObject(jsondata);  
    	String code = jsonObject.getString(errcode);
        return code;
	}
	public static List<Map<String, Object>> getListMap(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 1; i <= 5; i++)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("zaction", i);
			map.put("budat", i);
			map.put("zdoc_no", i);
			map.put("zdoc_item", i);
			map.put("gernr", "hacker13A" + i);

			list.add(map);
		}
		return list;
		/***
		 * {
				"ListMap":
				[
					{"UserName":"hacker13A1","UserId":1},
					{"UserName":"hacker13A2","UserId":2},
					{"UserName":"hacker13A3","UserId":3},
					{"UserName":"hacker13A4","UserId":4},
					{"UserName":"hacker13A5","UserId":5}
				]
			}
		 */
	}
	public static void main(String[] args) {
		String json = FileUtil.readStrTxt(new File("D://json//BarChart.json"), FileUtil.encodeing);
		System.out.println(json+"\n"+"-----------------------------------------------");
		JSONObject jsonObject = JSONObject.fromObject(json);  
//    	String categories = jsonObject.getString("categories");
//    	System.out.println(categories);//["Tokyo","New York","London","Berlin"]
//    	String x = jsonObject.getString("x");
//    	System.out.println(x);//["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]
    	String y = jsonObject.getString("y");
//    	System.out.println(y);//{"Tokyo":[49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4],"New York":[83.6,78.8,98.5,93.4,106,84.5,105,104.3,91.2,83.5,106.6,92.3],"London":[48.9,38.8,39.3,41.4,47,48.3,59,59.6,52.4,65.2,59.3,51.2],"Berlin":[42.4,33.2,34.5,39.7,52.6,75.5,57.4,60.4,47.6,39.1,46.8,51.1]}
		
    	JSONObject jsonY = JSONObject.fromObject(y);
    	String Tokyo = jsonY.getString("Tokyo");
//    	System.out.println(Tokyo);//[49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4]
    	String t = Tokyo.substring(1, Tokyo.length()-1);
    	System.out.println(t);//49.9,71.5,106.4,129.2,144,176,135.6,148.5,216.4,194.1,95.6,54.4
    	
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("jacyl_orderscheduling", "调度单号");
//		map.put("jacyl_carnum", "车牌号");
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("key1", map);
//		
//		map.clear();
//		map.put("k1", "v1");
//		map.put("k2", "v2");
//		jsonObject.put("key2", map);
//		
//		String data = jsonObject.toString();
//		System.out.println(data);
//		
//		JSONObject jsonObject1 = JSONObject.fromObject(data);  
//    	String key1 = jsonObject1.getString("key1");
//    	String key2 = jsonObject1.getString("key2");
//    	System.out.println(key1+"\n"+key2);
//    	
//    	JSONObject dataObject = JSONObject.fromObject(key2);
//        String k1 = dataObject.getString("k1");
//        String k2 = dataObject.getString("k2");
//        System.out.println(k1+"\n"+k2);
//    	System.out.println("------------------2-----------------");
//    	System.out.println(getCreateJson("ListMap", getListMap()));
	}
}