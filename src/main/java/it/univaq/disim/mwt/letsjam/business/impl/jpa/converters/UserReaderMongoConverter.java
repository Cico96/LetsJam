package it.univaq.disim.mwt.letsjam.business.impl.jpa.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.domain.User;

@Component
@ReadingConverter
public class UserReaderMongoConverter implements Converter<Document, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(Document object) {
        return userService.findUserById((long) object.get("id"));
    }    
}