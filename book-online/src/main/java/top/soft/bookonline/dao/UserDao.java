package top.soft.bookonline.dao;

import top.soft.bookonline.entity.User;

public interface UserDao {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据条件查找用户
     *
     * @param userDto
     * @return
     */
    User findUser(User userDto);

    User findByAccount(String account);

    User saveUser(User user);

    /**
     * 修改用户信息
     * @param id
     * @param nickname
     * @param avatar
     * @param address
     * @return
     */
    void changeUser(int id,String nickname, String avatar, String address);

    /**
     * 注销账号
     * @param id
     * @return
     */
    void cancelUser(int id);
}

