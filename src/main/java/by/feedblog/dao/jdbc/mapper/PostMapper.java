package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();
//        User user = new User();
//        Category category = new Category();

        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        String description = resultSet.getString(3);
        int countViews = resultSet.getInt(7);
        int likes = resultSet.getInt(8);
        int categoryId = resultSet.getInt(4);
        int userId = resultSet.getInt(5);
        boolean isChecked = resultSet.getBoolean(6);
        String userName = resultSet.getString(10);
        String login = resultSet.getString(11);
        String password = resultSet.getString(12);
        String categoryName = resultSet.getString(14);

        post.setId(id);
        post.setTitle(title);
        post.setDescription(description);
        post.setCountViews(countViews);
        post.setCategory(new Category(categoryId,categoryName));
        post.setUser(new User(userId,userName,login,password));
        post.setChecked(isChecked);
        post.setLikes(likes);

        return post;
    }
}
