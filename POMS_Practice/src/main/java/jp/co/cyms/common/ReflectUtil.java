/*
 * CLASS   : ReflectUtil
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2015-01-20　LSC　LI CREATE
 */
package jp.co.cyms.common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * NumberUtil
 * 
 * @author LSC LI
 * @since 2014/12/02
 */
public class ReflectUtil {

	/**
	 * ReflectUtil's constructor
	 */
	public ReflectUtil() {
	}

	/**
	 * getter
	 * 
	 * @param obj Object
	 * @param att String
	 * @return Object
	 * @throws Exception Exception
	 */
	public static Object getter(Object obj, String att) throws Exception {
		Method method = obj.getClass().getMethod("get" + toUpperCaseFirstOne(att));
		return method.invoke(obj);
	}

	/**
	 * setter
	 * 
	 * @param obj Object
	 * @param att String
	 * @param value Object
	 * @param type Type
	 * @throws Exception Exception
	 */
	public static void setter(Object obj, String att, Object value, Class< ? > type) throws Exception {
		Method method = obj.getClass().getMethod("set" + toUpperCaseFirstOne(att), type);
		method.invoke(obj, value);
	}

	/**
	 * deep copy
	 * 
	 * @param src List<T>
	 * @param <T> t
	 * @return dest List
	 * @throws IOException IOException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

	/**
	 * first letter to upper case
	 * 
	 * @param name String
	 * @return result
	 */
	private static String toUpperCaseFirstOne(String name) {
		if (StringUtil.isTrimEmpty(name)) {
			return name;
		}

		char[] ch = name.toCharArray();
		ch[0] = Character.toUpperCase(ch[0]);
		StringBuffer a = new StringBuffer();
		a.append(ch);

		return a.toString();
	}
}
