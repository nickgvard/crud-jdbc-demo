package repository;

import model.enums.PostStatus;
import utils.database.DataBaseSource;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusRepo {

    private final PostStatus postStatus;
    private final DataBaseSource dataSource;

    public PostStatusRepo(PostStatus postStatus) {
        this.postStatus = postStatus;
        dataSource = new DataBaseSource();
    }

    public void createPostStatus() {
        dataSource.createData();
    }

    public PostStatus postStatus() {
        return (PostStatus)dataSource.data();
    }

    public void updatePostStatus() {
        dataSource.updateData();
    }

    public void deletePostStatus() {
        dataSource.deleteData();
    }
}
