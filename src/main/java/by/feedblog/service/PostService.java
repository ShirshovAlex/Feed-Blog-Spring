package by.feedblog.service;

import by.feedblog.dao.jdbc.JdbcPostDao;
import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private JdbcPostDao jdbcPostDao;


    public boolean save(Post post) {
        if (jdbcPostDao.contains(post.getTitle())) {
            return false;
        }
        jdbcPostDao.save(post);
        return true;
    }

    public void updateDescription(long id, String string) {
        if (jdbcPostDao.contains(id)) {
            jdbcPostDao.updateDescription(id, string);
        }
    }

    public void deleteById(long id) {
        if (jdbcPostDao.contains(id)) {
            jdbcPostDao.deleteById(id);
        }
    }

    public void deleteByTitle(String title) {
        if (jdbcPostDao.contains(title)) {
            jdbcPostDao.deleteByTitle(title);
        }
    }

    public void count(Post post){
        jdbcPostDao.view(post);
    }

    public List<Post> getAll() {
        return jdbcPostDao.getAll();
    }

    public Post getById(long id) {
        if (jdbcPostDao.contains(id)) {
            return jdbcPostDao.getById(id);
        }
        return null;
    }

    public Post getByTitle(String title) {
        if (jdbcPostDao.contains(title)) {
            return jdbcPostDao.getByTitle(title);
        }
        return null;
    }

    public List<Post> isUnChecked(){
        List<Post> all = jdbcPostDao.getAll();
        List<Post> allUnChecked = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if(!all.get(i).isChecked()){
                allUnChecked.add(all.get(i));
            }
        }
        return allUnChecked;
    }

    public List<Post> isChecked(){
        List<Post> all = jdbcPostDao.getAll();
        List<Post> allChecked = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).isChecked()){
                allChecked.add(all.get(i));
            }
        }
        return allChecked;
    }

    public void check(long id, boolean b){
        jdbcPostDao.check(id, b);
    }


    public List<Post> getAllByUser(User user) {
       return jdbcPostDao.getAllByUser(user);
    }

    public void updateCategory(long postId, Category byId) {
        jdbcPostDao.updateCategory(postId, byId);
    }

    public void like(Post post){
        jdbcPostDao.getLikes(post);
    }
}
