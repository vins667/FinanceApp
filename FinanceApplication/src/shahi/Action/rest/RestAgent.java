package shahi.Action.rest;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import shahi.Action.MI.Beans.MMS200MIGetBean;
import shahi.Action.restresponse.MIRecord;
import shahi.Action.restresponse.NameValue;
import shahi.Action.restresponse.RootObject;
import shahi.Action.restresponse.mapper.ResponseToBeanMapper;

public class RestAgent {
private RestTemplate restTemplate=null;
private List<HttpMessageConverter<?>>messageConverters;
private MappingJacksonHttpMessageConverter jsonConverter;
public RestAgent(){
	restTemplate= new RestTemplate();
}
public void setHttpMessageConverter(){
	messageConverters=restTemplate.getMessageConverters();
	jsonConverter=new MappingJacksonHttpMessageConverter();
	messageConverters.add(jsonConverter);
	restTemplate.setMessageConverters(messageConverters);
}
public 	HttpHeaders createHeaders(final String username, final String password){
	       setHttpMessageConverter();
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		         set("Accept","application/json");
		      }};
		}
	
	public static void main(String args[]){
		RestAgent agent=new RestAgent();
		System.out.println("Start Time:"+new Date());
		RootObject status=agent.getRestTemplate()
				.exchange("http://172.17.3.115:25007/m3api-rest/execute/MMS200MI/LstItmByInAc?CONO=111&ITNO=0067", HttpMethod.GET, new HttpEntity(agent.createHeaders("m3user", "Lawson123")),RootObject.class)
				.getBody();
		System.out.println(initilizeMap(status.getMIRecord()));
		System.out.println("End  Time:"+new Date());
	}
	public static Object initilizeMap(List<MIRecord>list){
		Map<String,String>map=new LinkedHashMap<String,String>();
		MMS200MIGetBean bean=null;
		for(MIRecord mirecord:list){
			for(NameValue value:mirecord.getNameValue())
			map.put(value.getName(), value.getValue());
		}
	
			try {
				 bean=(MMS200MIGetBean)ResponseToBeanMapper.responseToBean(map, MMS200MIGetBean.class);
			} catch (InstantiationException | IllegalAccessException  |ClassNotFoundException | NoSuchMethodException|SecurityException |InvocationTargetException e) {
				e.printStackTrace();
			}
			
		 
		return bean;
	}
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
