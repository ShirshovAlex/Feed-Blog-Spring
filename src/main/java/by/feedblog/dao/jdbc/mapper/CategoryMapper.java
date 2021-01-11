package by.feedblog.dao.jdbc.mapper;

import by.feedblog.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {

        Category category = new Category();

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);

        category.setId(id);
        category.setName(name);

        return category;
    }
}
