package by.feedblog.dao.jdbc;

import by.feedblog.dao.jdbc.mapper.PostMapper;
import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPostDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Post post) {
        String sql = "insert into post values (default,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,post.getTitle(),post.getDescription(),post.getCategory().getId(),post.getUser().getId(),post.isChecked(), 0);
    }

    public void updateDescription(long id, String string) {
        String sql = "update post set description = ? where id = ?";
        jdbcTemplate.update(sql,string, id);
    }

    public void updateCategory(long id, Category category) {
        String sql = "update post set category_id = ? where id = ?";
        jdbcTemplate.update(sql,category.getId(), id);
    }

    public void deleteById(long id) {
        String sql ="delete from post where id =?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteByTitle(String title) {
        String sql ="delete from post where title =?";
        jdbcTemplate.update(sql, title);
    }

    public List<Post> getAll() {
        String sql ="select * from post join users u on u.id = post.user_id join category c on c.id = post.category_id";
        return jdbcTemplate.query(sql, new PostMapper());
    }

    public Post getById(long id) {
        String sql = "select * from post join users u on u.id = post.user_id join category c on c.id = post.category_id where post.id = ?";
        return jdbcTemplate.queryForObject(sql, new PostMapper(), id);
    }

    public List<Post> getAllByUser(User user) {
        String sql = "select * from post join users u on u.id = post.user_id join category c on c.id = post.category_id where u.id = ?";
        return jdbcTemplate.query(sql, new PostMapper(), user.getId());
    }

    public Post getByTitle(String title) {
        String sql = "select * from post where title = ?";
        return jdbcTemplate.queryForObject(sql,new PostMapper(), title);
    }

    public boolean contains(long id) {
        String sql = "select count(*) from post where id =?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return integer != null && integer > 0;
    }

    public boolean contains(String title) {
        String sql = "select count(*) from post where title = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, title);
        return integer != null && integer > 0;
    }

    public void check(long id, boolean b){
            String sql ="update post set \"isChecked\" = ? where id = ?";
            jdbcTemplate.update(sql, b, id);
    }

    public void view(Post post){
        String sql = "update post set count_views = ? where id = ?";
        long l = post.getCountViews() + 1;
        jdbcTemplate.update(sql,l,post.getId());
    }

    public void getLikes(Post post){
        String sql = "update post set likes = ? where id =?";
        long likes = post.getLikes() + 1;
        jdbcTemplate.update(sql,likes, post.getId());
    }
}
