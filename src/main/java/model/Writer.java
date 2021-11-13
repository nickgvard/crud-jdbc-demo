package model;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class Writer extends BaseEntity {

    private final String firstName;
    private final String lastName;
    private List<Post> posts;

    public Writer(long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public String toString() {
        return "Writer {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "}";
    }
}