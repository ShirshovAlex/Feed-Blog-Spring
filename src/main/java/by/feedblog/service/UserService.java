package by.feedblog.service;

import by.feedblog.dao.jdbc.JdbcUserDao;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    public boolean save(User user) {
        if (jdbcUserDao.contains(user.getLogin())) {
            return false;
        }
        jdbcUserDao.save(user);
        return true;
    }

    public boolean updatePassword(long id, String password){
        if (jdbcUserDao.contains(id)) {
            jdbcUserDao.updatePassword(id,password);
            return true;
        }
        return false;
    }

    public boolean updateName(String name, long id){
        if (jdbcUserDao.contains(id)) {
            jdbcUserDao.updateName(id, name);
            return true;
        }
        return false;
    }

    public boolean deleteById(long id){
        if(jdbcUserDao.contains(id)){
            jdbcUserDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByLogin(String login){
        if(jdbcUserDao.contains(login)){
            jdbcUserDao.deleteByLogin(login);
            return true;
        }
        return false;
    }

    public List<User> getAll(){
        return jdbcUserDao.getAll();
    }

    public User getByLogin(String login){
        if (jdbcUserDao.contains(login)) {
            return jdbcUserDao.getByLogin(login);
        }
        return null;
    }

    public List<User> getAllByName(String name){
        return jdbcUserDao.getAllByName(name);
    }



}
