package controller;

import model.entity.Post;
import model.entity.Writer;
import service.PostService;
import view.PostView;

import java.util.List;

public class PostController {

    private final PostService postService;

    public PostController(Writer writer) {
        postService = new PostService(writer);
    }

    public PostController() {
        postService = new PostService();
    }

    public void create(Post entity) {
        postService.create(entity);
    }

    public void update(Post entity) {
        postService.update(entity);
    }

    public void delete(Post entity) {
        postService.delete(entity);
    }

    public List<Post> read() {
        return postService.read();
    }

    public void updatePostView() {
        postView.showAllPosts(read());
    }
}
