package controller;

import model.entity.Post;
import model.entity.Writer;
import service.PostService;
import view.PostView;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatement {

    private final PostService postService;
    private final PostView postView;

    public PostStatement(Writer writer) {
        postService = new PostService(writer);
        postView = new PostView();
    }

    public PostStatement() {
        postService = new PostService();
        postView = new PostView();
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
