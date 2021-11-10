package service;

import enums.PostStatus;
import repository.jdbc_impl.JDBCPostStatusRepositoryImpl;

import java.util.List;

public class PostStatusService {

    private JDBCPostStatusRepositoryImpl statusRepository;

    public PostStatusService() {
        statusRepository = new JDBCPostStatusRepositoryImpl();
    }

    public List<PostStatus> read() {
        return statusRepository.read();
    }

    public void update(PostStatus entity) {
        statusRepository.update(entity);
    }

    public void delete(PostStatus entity) {
        statusRepository.deleteById(entity);
    }
}