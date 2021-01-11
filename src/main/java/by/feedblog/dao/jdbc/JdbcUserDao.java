package by.feedblog.dao.jdbc;

import by.feedblog.dao.jdbc.mapper.UserMapper;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user){
        String sql = "insert into users values(default, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getLogin(), user.getPassword());
    }

    public void updateName(long id, String name){
       String sql = "update users set name = ? where id = ?";
       jdbcTemplate.update(sql, name , id);
    }

    public void updatePassword(long id, String password){
        String sql = "update users set password = ? where id = ?";
        jdbcTemplate.update(sql, password, id);
    }

    public void deleteById(long id){
        String sql = "delete from users where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteByLogin(String login){
        String sql = "delete from users where login = ?";
        jdbcTemplate.update(sql, login);
    }

    public List<User> getAll(){
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public User getByLogin(String login){
        String sql = "select * from users where login = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(),login);
    }

    public List<User> getAllByName(String name){
        String sql = "select * from users where name =?";
        return jdbcTemplate.query(sql, new UserMapper(), name);
    }

    public boolean contains(long id){
        String sql = "select count(*) from users where id =?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return integer != null && integer > 0;
    }

    public boolean contains(String login){
        String sql = "select count(*) from users where login = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, login);
        return integer != null && integer > 0;
    }
}
