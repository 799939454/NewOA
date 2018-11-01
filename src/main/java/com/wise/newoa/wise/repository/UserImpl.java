package com.wise.newoa.wise.repository;



import com.wise.newoa.wise.entity.User;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserImpl implements IUserRepository {
    @Resource
    private MongoTemplate mongoTemplate1;


    @Resource
    private MongoTemplate mongoTemplate2;

    @Override
    public List<User> findAll(Class cls) {
        return mongoTemplate1.findAll(cls);
    }

    public List<User> findAll2(Class cls) {
        return mongoTemplate2.findAll(cls);
    }

    @Override
    public void insertUser(User user) {
        mongoTemplate1.insert(user);
    }

    @Override
    public void removeUser(String userName) {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public List<User> findForRequery(String userName) {
        return null;
    }


    public synchronized void saveUser(Object object){
        User wr=(User)object;
        wr.setId(getMaxIdDetailAccount());
        mongoTemplate1.insert(wr);
    }

    public Integer getMaxIdDetailAccount(){
        Document dbObject=new Document();
        Document fieldObject =new Document();
        fieldObject.put("_id", true);
        Query query= new BasicQuery(dbObject,fieldObject);
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        User findOne=mongoTemplate1.findOne(query,User.class);
        if(findOne==null){
            return 0;
        }
        return findOne.getId()+1;

       /* //DBObject dbObject = new BasicDBObject();
       // DBObject fieldObject = new BasicDBObject();
       // fieldObject.put("_id", true);
        Document dbObject =new Document();
        Document fieldObject =new Document();
        fieldObject.put("_id", true);

        Query query = new BasicQuery(dbObject,fieldObject);
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        User findOne= mongoTemplate1.findOne(query, User.class);
        if(findOne==null){
            return 0;
        }
        return findOne.getId()+1;*/

    }



}
