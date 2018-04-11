package com.pj.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
/**
 * gson操作工具
 * @author Fangpc
 *
 */
public class GsonUtils {
	
	protected static final Logger log = Logger.getLogger(GsonUtils.class);

	/**
	 * 将一个对象转成json字符串
	 * 转义HTML字符，如<，>，＆，=，并给您造成麻烦一个单引号：’。
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return toJsonExclude(obj);
	}
	
	/**
	 * 将一个对象转成json字符串
	 * 不转义,
	 * @param obj
	 * @return
	 */
	public static String toJsonHTML(Object obj) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.disableHtmlEscaping().create();
		return gs.toJson(obj);
	}

	/**
	 * 将一个对象转成json字符串并指定需要排除的属性名称列表 如果没有指定属性名称集合，则将会全部转换 默认时间会以时间戳的格式进行转换
	 * 
	 * @param obj
	 * @param exclusionFields
	 * @return String
	 */
	public static String toJsonExclude(Object obj, String... exclusionFields) {
		validateJsonObject(obj);
		// 创建GsonBuilder
		GsonBuilder builder = new GsonBuilder();

		// 设置时间格式
		builder.registerTypeAdapter(Date.class, new DateConverter());

		// 设置需要被排除的属性列表
		if (exclusionFields != null && exclusionFields.length > 0) {
			GsonExclusion gsonFilter = new GsonExclusion();
			gsonFilter.addExclusionField(exclusionFields);
			builder.setExclusionStrategies(gsonFilter);
		}

		// 创建Gson并进行转换
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}

	/**
	 * 将一个对象转成json字符串并指定需要需要转换的属性名称列表 如果没有指定属性名称集合，则将会全部转换 默认时间会以yyyy-MM-dd
	 * HH:mm:ss的格式进行转换
	 * 
	 * @param obj
	 * @param includeFields
	 * @return
	 */
	public static String toJsonInclude(Object obj, String... includeFields) {
		validateJsonObject(obj);
		// 创建GsonBuilder
		GsonBuilder builder = new GsonBuilder();

		// 设置时间格式
		builder.registerTypeAdapter(Date.class, new DateConverter());

		// 设置需要转换的属性名称
		if (includeFields != null && includeFields.length > 0) {

			GsonInclusion gsonFilter = new GsonInclusion();
			gsonFilter.addInclusionFields(includeFields);
			builder.setExclusionStrategies(gsonFilter);
		}

		// 创建Gson并进行转换
		Gson gson = builder.create();
		String json = gson.toJson(obj);
		System.out.println("json;;;;" + json);
		return json;
	}

	/**
	 * 通过response输出json数据
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param json
	 *            json字符串
	 */
	public static void printJson(HttpServletResponse response, String json) {
		PrintWriter writer = null;
		try {
			response.setContentType("application/json");
			writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 转map(此方法废弃)
	 * @param json
	 * @return
	 */
	public static Map<String, Object> convertJson2Map(String json) {
		if (json == null)
			return null;
		Gson gson = new GsonBuilder().registerTypeAdapter(
	            new TypeToken<TreeMap<String, Object>>(){}.getType(), 
	            new JsonDeserializer<TreeMap<String, Object>>() {
	            @Override
	            public TreeMap<String, Object> deserialize(
	            JsonElement json, Type typeOfT, 
	            JsonDeserializationContext context) throws JsonParseException {

	                TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
	                JsonObject jsonObject = json.getAsJsonObject();
	                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
	                for (Map.Entry<String, JsonElement> entry : entrySet) {
	                    treeMap.put(entry.getKey(), entry.getValue().getAsString());
	                }
	                return treeMap;
	            }
	        }).create();
		return gson.fromJson(json, new TypeToken<TreeMap<String, Object>>(){}.getType());
	}

	public static void printJson(HttpServletResponse response, String key,
			String value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		printJson(response, jsonObject.toString());
	}

	/**
	 * 将一个集合转成{data:[]}形式的对象
	 * 
	 * @param response
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public static void printCollectionJson(HttpServletResponse response,
			List data, String... includes) {
		String json = toCollectionJson(data, includes);
		printJson(response, json);
	}

	/**
	 * 将一个集合转成{data:[]}形式的字符串
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toCollectionJson(List data, String... includes) {
		Map<String, List> map = new HashMap<String, List>();
		map.put("data", data);
		return toJsonInclude(map, includes);
	}

	public static void printJson(HttpServletResponse response, String key,
			Integer value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		printJson(response, jsonObject.toString());
	}

	public static void printJson(HttpServletResponse response, String key,
			Float value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		printJson(response, jsonObject.toString());
	}

	public static void printJson(HttpServletResponse response, String key,
			Double value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		printJson(response, jsonObject.toString());
	}

	public static void printJson(HttpServletResponse response, String key,
			Boolean value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		printJson(response, jsonObject.toString());
	}

	/**
	 * 通过response输出json数据
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param obj
	 *            object
	 */
	public static void printJsonObject(HttpServletResponse response, Object obj) {
		if (obj == null)
			return;
		if (obj instanceof String) {
			printJson(response, (String) obj);
			return;
		}
		String json = toJson(obj);
		printJson(response, json);

	}

	public static void printError(HttpServletResponse response, String reason) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("error", reason);
		printJson(response, jsonObject.toString());
	}

	private static void validateJsonObject(Object obj) {
		if (obj == null) {
			throw new NullPointerException("要转成json的对象不能为空！");
		}
		if (obj instanceof String || obj instanceof Number
				|| obj instanceof Boolean) {
			throw new RuntimeException("要转成json字符串的必须是复杂(引用)类型的对象！");
		}
	}

	/**
	 * 从request body中取出数据流（json字符串），并进行utf-8转码后，使用Gson转换成指定类型的对象
	 * 如果request、clazz为空，则抛出异常
	 * 
	 * @param request
	 *            包含请求体的request对象
	 * @param clazz
	 *            要转成的对象的类型
	 * @param excludeFields
	 *            （可选，要排除的字段）
	 * @param <T>
	 * @return clazz指定的类型
	 */
	public static <T> T wrapDataToEntity(HttpServletRequest request,
			Class<T> clazz, String... excludeFields) {
		if (request == null || clazz == null) {
			throw new InvalidParameterException("参数不能为空！");
		}
		String data = null;
		try {
			data = IOUtils.toString(request.getInputStream(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new DateConverter());
		// 要排除的字段
		if (excludeFields != null && excludeFields.length > 0) {
			GsonExclusion exclusions = new GsonExclusion();
			exclusions.addExclusionField(excludeFields);
			builder.setExclusionStrategies(exclusions);
		}
		Gson gson = builder.create();

		T entity = gson.fromJson(data, clazz);
		return entity;
	}

	public static void printSuccess(HttpServletResponse response) {
		printJson(response, "success", true);
	}
	
	   /**
     * @Title: fromJson
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param <T>
     * @param json
     * @param classOfT
     * @return T 返回类型
     * @throws：
     */
    public  static <T>T fromJson(String json,Class<T> classOfT){
        Gson gson=new GsonBuilder()
                .registerTypeAdapter(java.util.Date.class, new DateConverter())
                .setDateFormat("yyyyMMddhhmmss")
                .create();
        return gson.fromJson(json, classOfT);
    }
    
    /** 
     * 获取JsonObject 
     * @param json 
     * @return 
     */  
    public static JsonObject parseJson(String json){  
        JsonParser parser = new JsonParser();  
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();  
        return jsonObj;  
    }  
      
    /** 
     * 根据json字符串返回Map对象 
     * @param json 
     * @return 
     */  
    public static Map<String,Object> toMap(String json){  
        return GsonUtils.toMap(GsonUtils.parseJson(json));  
    }  
      
    /** 
     * 将JSONObjec对象转换成Map-List集合 
     * @param json 
     * @return 
     */  
    public static Map<String, Object> toMap(JsonObject json){  
        Map<String, Object> map = new HashMap<String, Object>();  
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();  
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){  
            Entry<String, JsonElement> entry = iter.next();  
            String key = entry.getKey();  
            Object value = entry.getValue().getAsString();  
            if(value instanceof JsonArray)  
                map.put((String) key, toList((JsonArray) value));  
            else if(value instanceof JsonObject)  
                map.put((String) key, toMap((JsonObject) value));  
            else  
                map.put((String) key, entry.getValue().getAsString());  
        }  
        return map;  
    }  
      
    /** 
     * 将JSONArray对象转换成List集合 
     * @param json 
     * @return 
     */  
    public static List<Object> toList(JsonArray json){  
        List<Object> list = new ArrayList<Object>();  
        for (int i=0; i<json.size(); i++){  
            Object value = json.get(i);  
            if(value instanceof JsonArray){  
                list.add(toList((JsonArray) value));  
            }  
            else if(value instanceof JsonObject){  
                list.add(toMap((JsonObject) value));  
            }  
            else{  
                list.add(value);  
            }  
        }  
        return list;  
    }  
    public static List<Object> toList(String listStr){
    	
   	 // json转为带泛型的list  此种方法最简便
//       List<VehicleRespData> retList = gson.fromJson(RespRoot.getRespData(),  
//               new TypeToken<List<VehicleRespData>>() {  
//               }.getType());  
   	
   	return GsonUtils.toList(new JsonParser().parse(listStr).getAsJsonArray());
   }
    public static void main(String[] args) throws Exception {
//    	System.out.println(GsonUtils.toMap("{\"fans\":[{\"name\":\"小王\",\"age\":7},{\"name\":\"小尼玛\",\"age\":10}],\"name\":\"王尼玛\"}"));
//    	ArrayList<String> list = new ArrayList<>();
//    	list.add("1_2.jpg,aHR0cDovL2ltZ3Ntcy5zenNoYW55dW4uY29tOjgwODAvdXBsb2FkUGljdHVyZUZpbGUvMTQ5OTE0ODkzOTU2NS5qcGc=");
//    	System.out.println(GsonUtils.toJson(list));
//    	System.out.println(GsonUtils.toJsonHTML(list));
    }
    
}
