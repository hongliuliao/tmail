/**
 * $Id: JacksonUtils.java 20636 2012-05-08 03:22:53Z zhendong $
 * (C)2011 Sohu Inc.
 */

package org.tmail.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.StdSerializerProvider;

/**
 * Jackson框架封装工具类
 * 
 * @author: taochen
 * @author adyliu (imxylz@gmail.com)
 * @since 2011-10-24
 */
public class JacksonUtils {

    final static ObjectMapper objectMapper;
    static {
        StdSerializerProvider sp = new StdSerializerProvider();
        sp.setNullValueSerializer(new NullSerializer());
        objectMapper = new ObjectMapper(null, sp, null);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static class NullSerializer extends JsonSerializer<Object> {

        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeString("");
        }
    }
    /**
	 * 将json字符串转换为map
	 * @param jsonStr 要转换的字符串
	 * @return 转换成的map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJsonStr(String jsonStr){
		try {
			getObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			getObjectMapper().configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return getObjectMapper().readValue(jsonStr, Map.class);
		} catch (Exception e) {
			throw new RuntimeException("Can not getMapFromJsonStr which jsonStr:"+jsonStr,e);
		}
	}
	/**
	 * 将map转换为json字符串
	 * @param map 要转换的map
	 * @return 生成的字符串
	 */
	public static String getJsonFromMap(Map<String, ?> map){
		try {
			return getObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			throw new RuntimeException("Can not getJsonFromMap which map:"+map,e);
		}
	}
	
	/**
	 * json格式字符串转换为java对象
	 * 
	 * @param content
	 * @param valueType
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T toBean(String content, Class<T> valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new RuntimeException("Can not convert json string toBean!",e);
		}
	}
	
	/**
	 * java对象转换为json格式字符串
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object object) throws IOException {
		StringWriter writer = new StringWriter();
		try {
			objectMapper.writeValue(writer, object);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}
}
