package model.entity;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class Writer extends BaseEntity {

    private final String firstName;
    private final String lastName;
    private final List<Post> posts;

    public Writer(long id, String firstName, String lastName, List<Post> posts) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public List<Post> posts() {
        return posts;
    }
}