package com.jao.springboot.bean;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Service;

@Service("TransformarMensajeJAO")

public class TransformarMensajeJAO {
	
	private final MensajeBodyMapper bodyMapper;

    private final MensajeHeaderMapper headerMapper;

    public TransformarMensajeJAO(MensajeBodyMapper bodyMapper, MensajeHeaderMapper headerMapper) {
        this.bodyMapper = bodyMapper;
        this.headerMapper = headerMapper;
    }
    
    public void transform(Exchange exchange) {
        Message msg = exchange.getMessage();
        String header = "jao-header";
        msg.setBody(bodyMapper.map(msg.getBody(String.class)));
        msg.setHeader(header, headerMapper.map(msg.getHeader(header, String.class)));
    }

	
}
