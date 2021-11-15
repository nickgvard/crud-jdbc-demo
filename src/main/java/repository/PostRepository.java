package repository;

import model.Label;
import model.Post;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Label> getLabelsByPostId(long id);
}