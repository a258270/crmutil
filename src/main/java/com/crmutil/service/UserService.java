package com.crmutil.service;

import com.crmutil.dao.DaoSupport;
import com.crmutil.entity.User;
import com.crmutil.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/7
 */
@CacheConfig(cacheNames = "usersget")
@Service
public class UserService {
    @Autowired
    private DaoSupport dao;

    @Cacheable
    public List<User> getUsers(Page page) throws Exception {
        return (List<User>) dao.findForList("UserMapper.getUsers", page);
    }

    @Transactional
    @Cacheable(cacheNames = "usersget", key = "#u.username")
    public User getUser(User u) throws Exception {
        return (User) dao.findForObject("UserMapper.getUser", u);
    }

    @Transactional
    public void addUser(User u) throws Exception {
        dao.save("UserMapper.addUser", u);
    }

    @Transactional
    public void deleteUser(User u) throws Exception {
        dao.delete("UserMapper.deleteUser", u);
    }

    @Transactional
    public void updateUser(User u) throws Exception {
        dao.update("UserMapper.updateUser", u);
    }
}
