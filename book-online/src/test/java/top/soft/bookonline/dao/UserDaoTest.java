package top.soft.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;

class UserDaoTest {

    @Test
    void insertUser() {
        UserDao userDao = new UserDaoImpl();
        User user = User.builder().nickname("GJH").password("1234567").address("江苏南京").
                avatar("https://img0.baidu.com/it/u=3514278911,439770913&fm=253&fmt=auto&app=138&f=JPEG?w=707&h=500").
                account("GJH").build();
        userDao.insertUser(user);
    }
}