package top.soft.bookonline.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.util.JdbcUtil;

import java.util.List;

public class BookDaoImpl implements BookDao {

    private final JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());

    public List<Book> selectAll(){
        String sql = "SELECT * FROM t_booK ORDER BY id DESC";
        return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id){
        String sql = "SELECT * FROM t_book WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class),id);
    }
}
