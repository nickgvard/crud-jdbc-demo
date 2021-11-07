package repository;

import model.entity.Post;
import utils.database.DataBaseSource;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostRepo {

    private final Post post;
    private final DataBaseSource dataSource;

    public PostRepo(Post post) {
        this.post = post;
        dataSource = new DataBaseSource();
    }

    public void createPost() {
        dataSource.createData();
    }

    public Post post() {
        return (Post)dataSource.data();
    }

    public void updatePost() {
        dataSource.updateData();
    }

    public void deletePost() {
        dataSource.deleteData();
    }
}
