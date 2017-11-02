package com.example.demo.serializable;

import java.io.IOException;

import com.example.demo.controller.UserController;
import com.example.demo.model.Company;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ComapnyDeserialize extends StdSerializer<Company> {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -2012034750612008860L;

	public ComapnyDeserialize() {
	        this(null);
	    }
	   
	    public ComapnyDeserialize(Class<Company> t) {
	        super(t);
	    }

	@Override
	public void serialize(Company value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
  
        jgen.writeStartObject();
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("email", value.getEmail());
        jgen.writeEndObject();
    }

	

	
	

}
