package by.feedblog.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private long id;
    private String title;
    private String description;
    private Category category;
    private User user;
    private boolean isChecked = false;
    private long countViews = 0;
    private long likes = 0;

    public Post(String title, String description, Category category, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }
}
