package net.yes.JsonUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.yes.exception.SysException;

/**
 * 本类是json工具类
 * 
 * @author ChenYingshuang
 * @version Ver 1.0 2014年12月31日 创建
 * @since CodingExample Ver 1.0
 *
 * @see YourClass, HisClass
 * @deprecated（这里写deprecated的信息）
 */
public class JsonUtil {

	/**
	 * 日志记录器
	 */
	private static final Logger LOG = Logger.getLogger(JsonUtil.class);

	/**
	 * java对象和JSON结构的转换类
	 */
	static final ObjectMapper OBJECT_MAPPER;

	/**
	 * 初始化json和object映射关系对象，并指定序列化和反序列化的日期的格式
	 */
	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 构造方法
	 */
	private JsonUtil() {
	}

	/**
	 * 获取java对象和JSON结构的转换类
	 * 
	 * @return
	 */
	public static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}

	/**
	 * JSON串转换为Java泛型对象，可以是各种类型。
	 * 
	 * @param <T>
	 *            java对象的类型
	 * @param jsonString
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<FamousUser> >(){}
	 * @return List对象列表
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToGenericObject(String jsonString,
			TypeReference<T> tr) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		} else {
			try {
				return (T) OBJECT_MAPPER.readValue(jsonString, tr);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				throw new SysException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Java对象转JSON字符串
	 * 
	 * @param object
	 *            Java对象，可以是对象，数组，List,Map等
	 * @return JSON 字符串
	 */
	public static String toJson(Object object, boolean isPretty) {
		String jsonString;
		try {
			if (isPretty) {
				jsonString = OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
						.writeValueAsString(object);
			} else {
				jsonString = OBJECT_MAPPER.writeValueAsString(object);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new SysException(e.getMessage(), e);
		}
		return jsonString;

	}

	/**
	 * JSON字符串转Java对象
	 * 
	 * @param jsonString
	 *            要转换的JSON字符串
	 * @param c
	 *            java类型
	 * @return
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> c) {
		try {
			if (StringUtils.isBlank(jsonString)) {
				return c.newInstance();
			} else {
				return OBJECT_MAPPER.readValue(jsonString, c);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new SysException(e.getMessage(), e);
		}
	}

	/**
	 * null值的转换方式定义
	 * 
	 */
	public static class NullSerializer extends JsonSerializer<Object> {
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException {
			jgen.writeString((String) null);
		}
	}

	public static void main(String[] args) {
		Object file1 = new SipFile();
		((SipFile) file1).setName("test");
		((SipFile) file1).setPath("D:\\test\\abc.jpg");
		((SipFile) file1).setMemo(new String[] { "abc", "bbb" });
		System.out.println(JsonUtil.toJson(file1, false));

		String json = JsonUtil.toJson(file1, false);
		SipFile file = JsonUtil.jsonToObject(json, SipFile.class);
		System.out.println(file.getName());
		System.out.println(file.getPath());
	}

	public static class SipFile {
		private String name;
		private String path;
		private String[] memo;

		/**
		 * 获取（变量名称）
		 * 
		 * @return name
		 * @since CodingExamle Ver 1.0
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置（变量名称）
		 * 
		 * @param name
		 * @since CodingExample Ver 1.0
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 获取（变量名称）
		 * 
		 * @return path
		 * @since CodingExamle Ver 1.0
		 */
		public String getPath() {
			return path;
		}

		/**
		 * 设置（变量名称）
		 * 
		 * @param path
		 * @since CodingExample Ver 1.0
		 */
		public void setPath(String path) {
			this.path = path;
		}

		public String[] getMemo() {
			return memo;
		}

		public void setMemo(String[] memo) {
			this.memo = memo;
		}

	}

}