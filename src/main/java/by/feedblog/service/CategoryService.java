package by.feedblog.service;

import by.feedblog.dao.jdbc.JdbcCategoryDao;
import by.feedblog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private JdbcCategoryDao jdbcCategoryDao;

    public boolean save(Category category) {
        if (jdbcCategoryDao.contains(category.getName())) {
            return false;
        }
        jdbcCategoryDao.save(category);
        return true;
    }

    public void deleteById(long id) {
        if (jdbcCategoryDao.contains(id)) {
            jdbcCategoryDao.deleteById(id);
        }
    }

    public void updateName(long id, String name) {
        if (jdbcCategoryDao.contains(id)) {
            jdbcCategoryDao.updateName(id, name);
        }
    }

    public List<Category> getAll(){
        return jdbcCategoryDao.getAll();
    }

    public Category getByName(String name){
        if (jdbcCategoryDao.contains(name)) {
            return jdbcCategoryDao.getByName(name);
        }
        return null;
    }

    public Category getById(long id){
        if (jdbcCategoryDao.contains(id)) {
            return jdbcCategoryDao.getById(id);
        }
        return null;
    }


}
