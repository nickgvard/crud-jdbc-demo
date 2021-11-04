package service;

import model.entity.Post;
import repository.PostRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostService {

    private final Post post;
    private PostRepository postRepository;

    public PostService(Post post) {
        this.post = post;
        postRepository = new PostRepository(post);
    }

    public void createPost() {
        postRepository.createPost();
    }

    public Post post() {
        return postRepository.post();
    }

    public void updatePost() {
        postRepository.updatePost();
    }

    public void deletePost() {
        postRepository.deletePost();
    }
}
