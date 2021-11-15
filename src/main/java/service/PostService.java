package service;

import model.Post;
import repository.jdbc.JDBCPostRepositoryImpl;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class PostService {

    private JDBCPostRepositoryImpl postRepository;

    public PostService() {
        postRepository = new JDBCPostRepositoryImpl();
    }

    public Post getById(long id) {
        return postRepository.getById(id);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public Post deleteById(Post post) {
        return postRepository.deleteById(post);
    }
}