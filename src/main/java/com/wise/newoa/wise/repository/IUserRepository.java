package com.wise.newoa.wise.repository;


import com.wise.newoa.wise.entity.User;

import java.util.List;

public interface IUserRepository<T> {

    List<User> findAll(Class cls);
    void insertUser(User user);
    void removeUser(String userName);
    void updateUser();
    List<User> findForRequery(String userName);



}
