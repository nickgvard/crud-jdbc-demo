package repository;

import model.enums.PostStatus;
import repository.interfaces.Repository;
import utils.database.DataBaseAccess;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusRepository implements Repository<PostStatus> {

    private final PostStatus postStatus;
    private final DataBaseAccess dataSource;

    public PostStatusRepository(PostStatus postStatus) {
        this.postStatus = postStatus;
        dataSource = new DataBaseAccess();
    }

    @Override
    public void add(PostStatus entity) {

    }

    @Override
    public void remove(PostStatus entity) {

    }

    @Override
    public void update(PostStatus entity) {

    }

    @Override
    public List<PostStatus> read() {
        return null;
    }
}
