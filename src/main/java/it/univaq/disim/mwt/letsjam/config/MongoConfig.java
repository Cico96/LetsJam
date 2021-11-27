package it.univaq.disim.mwt.letsjam.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.stereotype.Component;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.converters.UserReaderMongoConverter;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.converters.UserWriterMongoConverter;

@Configuration
public class MongoConfig {
    
  @Autowired
  private UserReaderMongoConverter userReaderMongoConverter;

  @Autowired 
  private UserWriterMongoConverter userWriterMongoConverter;

  @Bean
  public MongoCustomConversions mongoCustomConversions() {
    return new MongoCustomConversions(List.of(
        userWriterMongoConverter, 
        userReaderMongoConverter
        ));
  }
}
