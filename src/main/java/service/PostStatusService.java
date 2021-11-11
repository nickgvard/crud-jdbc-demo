package service;

import enums.PostStatus;
import repository.jdbc_impl.JDBCPostStatusRepositoryImpl;

import java.util.List;

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

    public PostStatus update(PostStatus entity) {
        return statusRepository.update(entity);
    }

    public PostStatus deleteById(PostStatus entity) {
        return statusRepository.deleteById(entity);
    }
}