package repository;

import model.Post;
import model.Writer;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public interface WriterRepository extends GenericRepository<Writer, Long> {
    List<Post> getPostsByWriterId(long id);
}
