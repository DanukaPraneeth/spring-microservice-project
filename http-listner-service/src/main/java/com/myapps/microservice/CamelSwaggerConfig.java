package com.myapps.microservice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class CamelSwaggerConfig extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .apiContextPath("/swagger")					//Enable swagger end point  : swagger end point path
                .apiContextRouteId("swagger") 				//id of route providing the swagger end point
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .apiProperty("cors", "true");
        
		         //Swagger properties
//		        .contextPath("/api") //base.path swagger property; use the mapping path set for CamelServlet
//		        .apiProperty("api.title", "Example REST api")
//		        .apiProperty("api.version", "1.0");
        
    }

    @Controller
    public class SwaggerConfig {
        @RequestMapping("/swagger-ui")
        public String redirectToUi() {
            return "redirect:/webjars/swagger-ui/index.html?url=/nsb/api/swagger&validatorUrl=";
        }
    }
}
