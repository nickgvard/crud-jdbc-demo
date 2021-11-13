package controller;

import model.Post;
import service.PostService;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class PostController {

    private final PostService postService;

    public PostController() {
        postService = new PostService();
    }

    public Post getById(long id) {
        return postService.getById(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public Post save(Post entity) {
        return postService.save(entity);
    }

    public Post update(Post entity) {
        return postService.update(entity);
    }

    public Post deleteById(Post entity) {
        return postService.deleteById(entity);
    }

}
