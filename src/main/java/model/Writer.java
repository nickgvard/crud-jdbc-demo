package model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

@ToString
@SuperBuilder
public class Writer extends BaseEntity {

    @Getter private final String firstName;
    @Getter private final String lastName;
    @Getter private List<Post> posts;

    public void addPost(Post post) {
        posts.add(post);
    }
}