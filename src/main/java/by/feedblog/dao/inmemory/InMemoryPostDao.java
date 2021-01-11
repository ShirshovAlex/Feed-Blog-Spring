package by.feedblog.dao.inmemory;

import by.feedblog.entity.Category;
import by.feedblog.entity.Post;
import by.feedblog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


//save
//update description
//delete by id
//delete by title
//get all
//get by id
//get by title
//contains by id
//contains title

@Repository
public class InMemoryPostDao {
    private List<Post> posts = new ArrayList<>();

    public void save(Post post) {
        posts.add(post);
    }

    public void updateDescription(long id, String string) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setDescription(string);
                break;
            }
        }
    }

    public void updateCategory(long id, Category category) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setCategory(category);
                break;
            }
        }
    }

    public void deleteById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                break;
            }
        }
    }

    public void deleteByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                posts.remove(post);
                break;
            }
        }
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts);
    }

    public Post getById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List<Post> getAllByUser(User user) {
        ArrayList<Post> newPostList = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUser().getId() == user.getId()) {
                newPostList.add(post);
            }
        }
        return newPostList;
    }

    public Post getByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }

    public boolean contains(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }


}
