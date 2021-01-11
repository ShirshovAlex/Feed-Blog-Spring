package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String login = resultSet.getString(3);
        String password = resultSet.getString(4);

        user.setId(id);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

        return user;
    }
}
