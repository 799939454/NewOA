package com.wise.newoa.wise.service;


import com.wise.newoa.wise.entity.User;
import com.wise.newoa.wise.repository.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserImpl impl;

    public List<User> findAll(Class cls){
       return impl.findAll(cls);

    }

    public List<User> findAll2(Class cls){
        return impl.findAll2(cls);

    }


    public void save(User user){
        impl.saveUser(user);

    }

}
