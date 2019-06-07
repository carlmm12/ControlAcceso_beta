package co.com.samtel.ControlAccesos.util;

import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.com.samtel.ControlAccesos.properties.ListenProperties;

public class BeanUtil {

	public static Object getBeanName(String name) {
		try {
			InputStream inputStream = null;
			String propFileName = "applicationContext.xml";

			inputStream = BeanUtil.class.getClassLoader().getResourceAsStream(propFileName);
			System.out.println(inputStream);

			// ApplicationContext context = new
			// FileSystemXmlApplicationContext("applicationContext.xml");
			//ApplicationContext context = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			return context.getBean(name);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
