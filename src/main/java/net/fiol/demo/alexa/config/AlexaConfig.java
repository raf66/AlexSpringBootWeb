package net.fiol.demo.alexa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

import net.fiol.demo.alexa.handlers.HandlerSpeechlet;

@Configuration
public class AlexaConfig {

	@Autowired
	private HandlerSpeechlet handlerSpeechlet;
	
	@Bean
	public ServletRegistrationBean registerSpeechletServlet() {
		
		SpeechletServlet speechletServlet = new SpeechletServlet();
		speechletServlet.setSpeechlet(handlerSpeechlet);
		
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(speechletServlet, "/alexa");
		return servletRegistrationBean;
	}

}
