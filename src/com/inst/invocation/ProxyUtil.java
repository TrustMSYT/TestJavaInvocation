package com.inst.invocation;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 
 * @description 代理方法执行工具类
 * @author xxl
 * @version 1.0
 * @createDate 2018年12月26日 上午10:18:50
 *
 */
public class ProxyUtil {
	Reflect reflect;

	public ProxyUtil() throws ClassNotFoundException {
		reflect = new Reflect();
	}

	/**
	 * 
	 * @description 负责代理方法的执行
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 下午1:40:05
	 *
	 * @param proxyEntity
	 * @return
	 * @throws Throwable
	 */
	public Object generateEntity(ProxyEntity proxyEntity) throws Throwable {
		String methodStr = proxyEntity.getMethod().toString();
		String proxyMethodValue = methodStr.substring(methodStr.lastIndexOf(" ") + 1, methodStr.indexOf("("));
		// 获取自定义注解中的前置及后置方法
		Map<String, String> methodMap = reflect.getMap();
		for (Map.Entry<String, String> map : methodMap.entrySet()) {
			String proxyMethodName = proxyMethodValue.substring(proxyMethodValue.lastIndexOf(".")+1);
			String mapMethodName = map.getValue().substring(map.getValue().lastIndexOf(".")+1);
			if (proxyMethodName.equals(mapMethodName)) {
				String[] str = mapKeyDivision(map.getKey());
				// 若为前置方法
				if (str[2].equals("before")) {
					// 加载该类
					Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader());
					Method method = clazz.getDeclaredMethod(str[0]);
					// 反射调用前置方法
					method.invoke(clazz.newInstance(), null);
				}
			}
		}
		// 处理后置通知
		return doAfter(proxyEntity, methodMap); 
	}

	/**
	 * 
	 * @description 处理后置通知
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 上午10:21:30
	 *
	 * @param proxyEntity
	 * @param map
	 * @return
	 * @throws Throwable
	 */
	private Object doAfter(ProxyEntity proxyEntity, Map<String, String> map) throws Throwable {
		// 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象的关联hanler对象的invoke方法来进行调用
		proxyEntity.getMethod().invoke(proxyEntity.getTargetObject(), proxyEntity.getArgs());
		String methodStr = proxyEntity.getMethod().toString();
		String proxyMethodValue = methodStr.substring(methodStr.lastIndexOf(" ") + 1, methodStr.indexOf("("));
		for (Map.Entry<String, String> aMap : map.entrySet()) {
			String proxyMethodName = proxyMethodValue.substring(proxyMethodValue.lastIndexOf(".")+1);
			String mapMethodName = aMap.getValue().substring(aMap.getValue().lastIndexOf(".")+1);
			if (proxyMethodName.equals(mapMethodName)) {
				String[] str = mapKeyDivision(aMap.getKey());
				if (str[2].equals("after")) {
					Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // 加载该类
					Method method = clazz.getDeclaredMethod(str[0]);
					method.invoke(clazz.newInstance(), null); // 这一步需要原始的类
				}
			}
		}
		return proxyEntity.getObject();
	}

	/**
	 * 
	 * @description 分解map里面的键，因为里面存入了方法和类名以及执行顺序
	 * @author xxl
	 * @version 1.0
	 * @createDate 2018年12月26日 上午10:19:35
	 *
	 * @param value
	 * @return
	 */
	private String[] mapKeyDivision(String value) {
		String[] str = new String[10];
		str[0] = value.substring(0, value.indexOf("-")); // 注解下面的方法
		str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-")); // 注解所在的类
		str[2] = value.substring(value.lastIndexOf("-") + 1, value.length()); // 是before还是after
		return str;
	}
}
