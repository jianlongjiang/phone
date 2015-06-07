package com.phone.cn.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.collections.map.ListOrderedMap;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.core.annotation.AnnotationUtils;

public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
    	
        // 设置默认日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 提供其它默认设置
        // objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES,
        // false);
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));

    }

    /**
     * 将对象转换成json字符串格式（默认将转换所有的属�?�?
     * 
     * @param o
     * @return
     */
    public static String toJsonStr(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * 对象转换成对应的bean
     * 
     * @param jsonObject
     * @param valueType
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T readValue(Object obj, Class<T> valueType) {
        // return objectMapper.readValue(objectMapper.writeValueAsString(obj),
        // valueType);
        return objectMapper.convertValue(obj, valueType);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> beanToMap(Object obj) {
        // return objectMapper.readValue(objectMapper.writeValueAsString(obj),
        // valueType);
        return objectMapper.convertValue(obj, HashMap.class);

    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> beanToList(Object apiObj) {
        // return objectMapper.readValue(objectMapper.writeValueAsString(obj),
        // valueType);
        List<Object> objList = (List<Object>) apiObj;
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for (Object obj : objList) {
            returnList.add(beanToMap(obj));
        }
        return returnList;

    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readListValue(Object apiObj, Class<T> valueType) {
        if (apiObj instanceof List) {
            List<Object> objList = (List<Object>) apiObj;
            List<T> returnList = new ArrayList<T>();
            for (Object obj : objList) {
                returnList.add(readValue(obj, valueType));
            }
            return returnList;
        }
        return null;
    }

    public static <T> T readStringValue(String jsonobj, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonobj, valueType);
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<Object, Object> readStringValueToMap(String jsonobj) {
        try {
            return objectMapper.readValue(jsonobj, HashMap.class);
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> readStringValueToList(String jsonobj) {
        try {
            return objectMapper.readValue(jsonobj, List.class);
        } catch (JsonParseException e) {

            e.printStackTrace();
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonGenerationException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将对象转换成json字符串格�?
     * 
     * @param value
     *            �?��转换的对�?注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties
     *            �?��转换的属�?
     */
    public static String toJsonStr(Object value, String[] properties) throws JsonGenerationException, JsonMappingException, IOException {

        return objectMapper.writer(
                new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class)).toString(),
                        SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValueAsString(value);

    }

    /**
     * 将对象转换成json字符串格�?
     * 
     * @param value
     *            �?��转换的对�?注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties2Exclude
     *            �?��排除的属�?
     */
    public static String toJsonStrWithExcludeProperties(Object value, String[] properties2Exclude) throws JsonGenerationException, JsonMappingException, IOException {
        return objectMapper.writer(
                new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class)).toString(),
                        SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude))).writeValueAsString(value);

    }

    /**
     * 将对象json格式直接写出到流对象中（默认将转换所有的属�?�?
     * 
     * @param o
     * @return
     */
    public static void writeJsonStr(OutputStream out, Object value) throws JsonGenerationException, JsonMappingException, IOException {
        objectMapper.writeValue(out, value);
    }

    /**
     * 将对象json格式直接写出到流对象�?
     * 
     * @param value
     *            �?��转换的对�?注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties
     *            �?��转换的属�?
     */
    public static void writeJsonStr(OutputStream out, Object value, String[] properties) throws JsonGenerationException, JsonMappingException, IOException {

        objectMapper.writer(
                new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class)).toString(),
                        SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValue(out, value);

    }

    /**
     * 将对象转换成json字符串格�?
     * 
     * @param value
     *            �?��转换的对�?
     * @param properties2Exclude
     *            �?��排除的属�?注意，需要在要转换的对象中定义JsonFilter注解)
     */
    public static void writeJsonStrWithExcludeProperties(OutputStream out, Object value, String[] properties2Exclude) throws JsonGenerationException, JsonMappingException, IOException {
        objectMapper.writer(
                new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class)).toString(),
                        SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude))).writeValue(out, value);

    }

    /**
     * 得到jackson原始ObjectMapper进行本类未开放api的调�?
     * 
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
	/** 
	 * @Title: parse 
	 * @Description: map转成json字符串 
	 * @param:  @param map
	 * @param:  @return    
	 * @return:  String    
	 * @throws 
	 * @author xuhj
	 * @Date 2015-3-6 上午10:53:48 
	 */
	public static String parse(Map<String,Object> map) {
		JsonConfig jc = new JsonConfig();
		return JSONObject.fromObject(map, jc).toString();
		}
	
	/** 
	 * @Title: parseJSON2List 
	 * @Description: 字符串转成list 
	 * @param:  @param jsonStr
	 * @param:  @return    
	 * @return:  List<Map<String,Object>>    
	 * @throws 
	 * @author xuhj
	 * @Date 2015-3-6 上午10:54:10 
	 */
	public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        @SuppressWarnings("unchecked")
		Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }
    
    /** 
     * @Title: parseJSON2Map 
     * @Description: 字符串转成map
     *  
     * @param:  @param jsonStr
     * @param:  @return    
     * @return:  Map<String,Object>    
     * @throws 
     * @author xuhj
     * @Date 2015-3-6 上午10:54:43 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr){
        ListOrderedMap map = new ListOrderedMap();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static void main(String[] ddd) throws JsonParseException, JsonMappingException, IOException {
        // String jsonStr =
        // "{big:\"300X300\",middle:\"200X200\",small:\"100X100\"}";
        // String jsonStr = "{big:\"300X300\"}";
        // Map test = readStringValue(jsonStr, Map.class);
        // System.out.println(JsonMapper.toJsonStr(test));

        // APIDesUtils api = new APIDesUtils();
        // try {
        // System.out.println(api.encrypt("ddddddddd",
        // "product1111111111111111111111"));
        // } catch (Exception e) {
        //
        // e.printStackTrace();
        // }
    }
}
