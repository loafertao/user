package cn.kgc.ums.util;

import java.util.Properties;

public class ConstantUtil {

	private static Properties porps = new Properties();
	
	static {
		try {
			porps.load(ConstantUtil.class.getClassLoader().getResourceAsStream("system.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final Integer PAGE_NUM = Integer.parseInt(porps.getProperty("page.num"));
	
	public static final Integer PAGE_SIZE = Integer.parseInt(porps.getProperty("page.size"));
}
