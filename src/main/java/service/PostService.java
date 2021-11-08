package service;

import model.entity.Post;
import model.entity.Writer;
import repository.PostRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostService {

    private PostRepository postRepository;

    public PostService(Writer writer) {
        postRepository = new PostRepository(writer);
    }

    public PostService() {
        postRepository = new PostRepository();
    }

    public void create(Post post) {
        postRepository.add(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(Post post) {
        postRepository.remove(post);
    }

    public List<Post> read() {
        return postRepository.read();
    }
}