package com.crmutil.controller;

import com.crmutil.controller.base.BaseController;
import com.crmutil.entity.User;
import com.crmutil.service.UserService;
import com.crmutil.util.LoggerUtil;
import com.crmutil.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/7
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add/{username}/{password}")
    public String add(@PathVariable String username, @PathVariable String password){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        try {
            userService.addUser(u);
        } catch (Exception e) {
            logger.error("add error", e);
        }

        return "add ok";
    }

    @RequestMapping(value = "/delete")
    public String delete(@PathVariable String username){
        User u = new User();
        u.setUsername(username);
        try{
            userService.deleteUser(u);
        }
        catch (Exception e){
            logger.error("delete error", e);
        }

        return "delete ok";
    }

    @RequestMapping(value = "/update")
    public String update(@PathVariable String username, @PathVariable String password){
        User u = new User();
        u.setPassword(password);
        u.setUsername(username);

        try{
            userService.updateUser(u);
        } catch (Exception e){
            logger.error("update error", e);
        }

        return "update ok";
    }

    @RequestMapping(value = "/gets")
    public String gets() {
        try{
            Page<User> page = new Page<User>();
            List<User> users = userService.getUsers(page);
            page.setResults(users);
            logger.info(users.toString());
        } catch (Exception e){
            logger.error("gets error", e);
        }

        return "gets ok";
    }

    @RequestMapping(value = "/get/{username}")
    public Object get(@PathVariable String username){
        try{
            User user = new User();
            user.setUsername(username);
            return userService.getUser(user);
        }
        catch(Exception e){
            logger.error("get error", e);
        }

        return "get ok";
    }
}
