package service;

import enums.PostStatus;
import repository.jdbc_impl.JDBCPostStatusRepositoryImpl;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class PostStatusService {

    private JDBCPostStatusRepositoryImpl statusRepository;

    public PostStatusService() {
        statusRepository = new JDBCPostStatusRepositoryImpl();
    }

    public PostStatus getById(long id) {
        return statusRepository.getById(id);
    }

    public List<PostStatus> getAll() {
        return statusRepository.getAll();
    }

    public PostStatus save(PostStatus postStatus) {
        return statusRepository.save(postStatus);
    }

    public PostStatus update(PostStatus postStatus) {
        return statusRepository.update(postStatus);
    }

    public PostStatus deleteById(PostStatus postStatus) {
        return statusRepository.deleteById(postStatus);
    }
}