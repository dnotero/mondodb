package ar.com.dno.mongodb.helloworld;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldFreeMarkerStyle {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");
		
		Template template = configuration.getTemplate("hello.ftl");
		StringWriter writer = new StringWriter();
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("name", "Freemarker");
		
		template.process(params, writer);
		
		System.out.println(writer);
	}

}
