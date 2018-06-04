package shahi.Action.restresponse.mapper;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ResponseToBeanMapper {

	public static <T>  Object responseToBean(Map<String,String> soruce,Class<T>target) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		    Class targetClass=Class.forName(target.getName());
			Object targetObject=targetClass.newInstance();
	        Method[]methods=targetObject.getClass().getDeclaredMethods();
	        Field[]fields=targetObject.getClass().getDeclaredFields();
	        Set<String>keys=soruce.keySet();
	        for(Field field:fields){
	        	 Method method = targetObject.getClass().getMethod("set" + field.getName(), new Class[] {String.class}); 
	        	 method.invoke(targetObject,soruce.get(field.getName()));
	          
	        }
	       
	      //  System.out.println(targetObject);
	        return targetObject;
	}
}
