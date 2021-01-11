package by.feedblog.dao.inmemory;


//save
//update description
//delete by id
//delete by title
//get all
//get by id
//get by title
//contains by id
//contains title

import by.feedblog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserDao {

    private List<User> users = new ArrayList<>();

    public void save(User user){
        users.add(user);
    }

    public void updateName(long id, String name){
        for (User user : users) {
            if (user.getId()==id) {
                user.setName(name);
                break;
            }
        }
    }

    public void updatePassword(long id, String password){
        for (User user : users) {
            if (user.getId()==id) {
                user.setPassword(password);
                break;
            }
        }
    }

    public void deleteById(long id){
        for (User user : users) {
            if (user.getId()==id) {
                users.remove(user);
                break;
            }
        }
    }

    public void deleteByLogin(String login){
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                users.remove(user);
                break;
            }
        }
    }

    public List<User> getAll(){
        return new ArrayList<>(users);
    }

    public User getByLogin(String login){
        for (User user : users) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    public List<User> getAllByName(String name){
        List<User> usersByName = new ArrayList<>();
        for (User user : users) {
            if (user.getName().equals(name)) {
                usersByName.add(user);
            }
        }
        return usersByName;
    }

    public boolean contains(long id){
        for (User user : users) {
            if (user.getId()==id) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String login){
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }


}
