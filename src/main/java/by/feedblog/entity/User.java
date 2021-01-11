package by.feedblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    private String name;
    private String login;
    private String password;


    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
