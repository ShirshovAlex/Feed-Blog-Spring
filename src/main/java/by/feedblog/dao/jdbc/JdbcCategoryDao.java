package by.feedblog.dao.jdbc;

import by.feedblog.dao.jdbc.mapper.CategoryMapper;
import by.feedblog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(Category category) {
        String sql = ("insert into category values (default,?)");
        jdbcTemplate.update(sql, category.getName());
    }

    public void deleteById(long id) {
        String sql = "delete from category where id =?";
        jdbcTemplate.update(sql, id);
    }

    public void updateName(long id, String name) {
        String sql = "update category set name = ? where id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public List<Category> getAll() {
        String sql = "select * from category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    public Category getByName(String name){
        String sql = "select * from category where name = ?";
        return jdbcTemplate.queryForObject(sql, new CategoryMapper(), name);
    }

    public Category getById(long id){
        String sql = "select * from category where id = ?";
        return jdbcTemplate.queryForObject(sql, new CategoryMapper(), id);
    }

    public boolean contains(long id){
        String sql = "select count(*) from category where id =?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return integer != null && integer > 0;
    }

    public boolean contains(String name){
        String sql = "select count(*) from category where name = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return integer != null && integer > 0;
    }
}
