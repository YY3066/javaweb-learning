package top.soft.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.util.JdbcUtil;
import top.soft.bookonline.util.Md5Util;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public int insertUser(User user) {
        //1、编写 SQL
        String sql = "INSERT INTO t_user(account,password,nickname,avatar) VALUES (?,?,?,?)";
        //2、调用query 方法
        return jdbcTemplate.update(sql, user.getAccount(), Md5Util.crypt(user.getPassword()), user.getNickname(), user.getAvatar());
    }
    @Override
    public User findUser(User userDto){
        try {
            //1、编写 sql
            String sql = "SELECT * FROM t_user WHERE account = ? AND password = ?";
            //2、调用 query 方法，比对账号和加密后的密码
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>
                    (User.class), userDto.getAccount(), Md5Util.crypt(userDto.getPassword()));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByAccount(String account) {
        String sql = "SELECT * FROM t_user WHERE account = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{account}, (rs, rowNum) ->
                    User.builder()
                            .account(rs.getString("account"))
                            .password(rs.getString("password"))
                            .build()
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // 如果没有找到用户，返回 null
        }
    }


    @Override
    public User saveUser(User user) {
        String sql = "INSERT INTO t_user(account, password, nickname, avatar) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getAccount(), Md5Util.crypt(user.getPassword()), user.getNickname(), user.getAvatar());
        return user; // 返回保存的用户
    }

    @Override
    public void changeUser(int id, String nickname, String avatar, String address) {
        String sql = "UPDATE t_user SET nickname = ?, avatar = ?, address = ? WHERE id = ?;";
        jdbcTemplate.update(sql, nickname, avatar, address,id);
    }

    @Override
    public void cancelUser(int id) {
        String sql = "DELETE FROM t_user WHERE id = ?;";
        jdbcTemplate.update(sql,id);
    }

}
