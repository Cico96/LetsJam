package it.univaq.disim.mwt.letsjam.business.impl.jpa.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import it.univaq.disim.mwt.letsjam.domain.User;

@Component
@WritingConverter
public class UserWriterMongoConverter implements Converter<User, DBObject> {

    @Override
    public DBObject convert(User user) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("id", user.getId());
        dbObject.put("firstname", user.getFirstname());
        dbObject.put("lastname", user.getLastname());
        dbObject.put("username", user.getUsername());
        dbObject.put("email", user.getEmail());
        dbObject.put("avatar", user.getAvatar());
        dbObject.put("role", user.getRole().toString());
        dbObject.removeField("_class");
        System.out.println("convert");
        return dbObject;
    }
}
