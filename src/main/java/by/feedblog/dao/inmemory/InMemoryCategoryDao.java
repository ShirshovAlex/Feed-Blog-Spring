package by.feedblog.dao.inmemory;

import by.feedblog.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCategoryDao {

    private List<Category> categories = new ArrayList<>();

    public void save(Category category){
       categories.add(category);
    }

    public void deleteById(long id){
        for (Category category : categories) {
            if (category.getId()==id) {
                categories.remove(category);
                break;
            }
        }
    }

    public void updateName(long id, String name){
        for (Category category : categories) {
            if (category.getId()==id) {
                category.setName(name);
                break;
            }
        }
    }

    public List<Category> getAll(){
        return new ArrayList<>(categories);
    }

    public Category getByName(String name){
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public Category getById(long id){
        for (Category category : categories) {
            if (category.getId()==id) {
                return category;
            }
        }
        return null;
    }

    public boolean contains(long id){
        for (Category category : categories) {
            if (category.getId()==id) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String name){
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
