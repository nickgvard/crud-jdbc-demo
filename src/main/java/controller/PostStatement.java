package controller;

import model.entity.Label;
import model.entity.Post;
import service.PostService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatement {

    private final Post post;
    private final PostService postService;

    public PostStatement(long id, String content, Timestamp created, Timestamp updated, List<Label> labels) {
        post = new Post(id, content, created, updated, labels);
        postService = new PostService(post);
    }

    public void createPost() {
        postService.createPost();
    }

    public Post post() {
        return postService.post();
    }

    public void updatePost() {
        postService.updatePost();
    }

    public void deletePost() {
        postService.deletePost();
    }
}
