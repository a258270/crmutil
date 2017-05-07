package com.crmutil.mapper;

import com.crmutil.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/7
 */
public interface UserMapper {
    @Select("select * from user")
    public List<User> getUsers();
}
