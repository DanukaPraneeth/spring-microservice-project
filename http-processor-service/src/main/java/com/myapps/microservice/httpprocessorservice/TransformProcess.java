package com.myapps.microservice.httpprocessorservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapps.microservice.httpprocessorservice.utils.DataModel;

@Component
public class TransformProcess implements Processor{

    private static Log log = LogFactory.getLog(TransformProcess.class);

    @Autowired
    private DataModel dataModel;
   
	@Override
    public void process(Exchange exchange) throws Exception {
	
		log.info("Transform process start.. " );
        String payload = exchange.getIn().getBody(String.class);
        String token = exchange.getIn().getHeader("user",String.class);
        //String token = (String) exchange.getIn().getHeader("correlationId");
        
        dataModel.setMsisdn("6511234512");
        dataModel.setOperator("Singtel");
        dataModel.setOperation(payload);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataModel);
        log.info("The NSB transformed request: " + json);
        exchange.getOut().setBody(json);
        
		log.info("payload " + payload );
		log.info("correlationId " + token );

	}
}
