package uk.co.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {
	
	private ContextUtil(){
		
	}

	public static Context open(String name) throws Exception {
		try{
			return new InitialContext(getResourceAsProperties(name+".properties"));
		}catch (Exception ioe){
			throw new RuntimeException("ex");
		}
	}
	
	public static void close(final Context context){
		try{
			context.close();
		} catch (NamingException e){
			throw new RuntimeException("");
		}
	}
	
	
	protected static Properties getResourceAsProperties(final String name) throws Exception{
		Properties properties = new Properties();
		
		try (InputStream in = getResourceAsStream(name)){
			properties.load(in);
		} catch (IOException ioe){
			throw new Exception("ex");
		}
		return properties;	
	}

	private static InputStream getResourceAsStream(final String name) {
		InputStream in = ContextUtil.class.getResourceAsStream(name);
		if (in != null) {
			return in;
		}
		in =getClassLoader().getResourceAsStream(name);
		if (in != null){
			return in;
		}
		return null;
	}

	protected static ClassLoader getClassLoader(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null){
			return classLoader;
		}
		
		return ContextUtil.class.getClassLoader();
	}
	
}
