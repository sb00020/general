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
		System.out.println(name);
		
		
		try{
			return new InitialContext(getResourceAsProperties(name+".properties"));
		}catch (Exception ioe){
			throw new RuntimeException("Return", ioe);
		}
	}
	
	public static void close(final Context context){
		try{
			context.close();
		} catch (NamingException e){
			throw new RuntimeException("closing");
		}
	}
	
	
	protected static Properties getResourceAsProperties(final String name) throws Exception{
		Properties properties = new Properties();
		System.out.println("creating props");
		try {
			InputStream in = getResourceAsStream(name);
			
			properties.load(in);
			properties.size();
		} catch (IOException ioe){
			System.out.println("Stream is null");
			throw new Exception("Loading Props");
		}
		return properties;	
	}

	private static InputStream getResourceAsStream(final String name) {
		InputStream in = ContextUtil.class.getResourceAsStream(name);
		System.out.println("creating input stream");
		if (in != null) {
			return in;
		}
		in=getClassLoader().getResourceAsStream(name);
		System.out.println(in);
		if (in != null){
			return in;
		}
		System.out.println(in);
		return null;
	}

	protected static ClassLoader getClassLoader(){
		System.out.println("Class Laoders");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null){
			return classLoader;
		}
		
		return ContextUtil.class.getClassLoader();
	}
	
}
