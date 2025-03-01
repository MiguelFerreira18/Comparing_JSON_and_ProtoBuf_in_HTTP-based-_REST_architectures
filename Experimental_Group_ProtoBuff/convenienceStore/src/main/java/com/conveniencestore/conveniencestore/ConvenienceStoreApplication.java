package com.conveniencestore.conveniencestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@SpringBootApplication
public class ConvenienceStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConvenienceStoreApplication.class, args);
	}



	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter(){
		return new ProtobufHttpMessageConverter();
	}
}
