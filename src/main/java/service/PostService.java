package service;

import model.entity.Post;
import model.entity.Writer;
import repository.jdbc_impl.JDBCPostRepositoryImpl;

import java.util.List;

public class PostService {

    private JDBCPostRepositoryImpl postRepository;

    public PostService(Writer writer) {
        postRepository = new JDBCPostRepositoryImpl(writer);
    }

    public PostService() {
        postRepository = new JDBCPostRepositoryImpl();
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(Post post) {
        postRepository.deleteById(post);
    }

    public List<Post> read() {
        return postRepository.read();
    }
}