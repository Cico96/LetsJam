package it.univaq.disim.mwt.letsjam.business.impl.jpa.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import it.univaq.disim.mwt.letsjam.domain.User;

@Component
@WritingConverter
public class UserWriterMongoConverter implements Converter<User, Document> {

    @Override
    public Document convert(User user) {
        Document obj = new Document();
        obj.put("id", user.getId());
        obj.put("firstname", user.getFirstname());
        obj.put("lastname", user.getLastname());
        obj.put("username", user.getUsername());
        obj.put("email", user.getEmail());
        obj.put("avatar", user.getAvatar());
        obj.put("role", user.getRole().toString());
        return obj;
    }
}
