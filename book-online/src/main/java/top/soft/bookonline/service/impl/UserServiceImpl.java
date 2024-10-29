package top.soft.bookonline.service.impl;

import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    public User signIn(String account, String password){
        User user = User.builder().account(account).password(password).build();
        return userDao.findUser(user);
    }

    @Override
    public User signUp(String account, String password) {
        // 检查账号是否已存在
        if (userDao.findByAccount(account) != null) {
            throw new IllegalArgumentException("账号已存在");
        }

        // 创建新用户
        User newUser = User.builder().account(account).password(password).build();
        return userDao.saveUser(newUser);
    }

    @Override
    public void changeInfo(int id,String nickname, String avatar, String address) {
        userDao.changeUser(id,nickname,avatar,address);
    }

    @Override
    public void cancelUser(int id) {
        userDao.cancelUser(id);
    }
}
