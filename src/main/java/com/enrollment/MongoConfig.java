package com.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfig {

	@Autowired
	@Lazy
	private MappingMongoConverter mappinMongoConverter;
	
	public void afterPropertiesSet() throws Exception {
		mappinMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
	}
}
