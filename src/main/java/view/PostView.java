package view;

import controller.PostController;
import model.Post;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class PostView {

    private final PostController postController;

    public PostView() {
        postController = new PostController();
    }

    public void showPostById(long id) {
        Post post = postController.getById(id);
        if(post != null) {
            System.out.println(post);
        }else
            System.out.println("Not found post by this id");
    }

    public void showAllPosts() {
        List<Post> posts = postController.getAll();
        if(!posts.isEmpty()) {
            for (Post post : posts) {
                System.out.println(post);
            }
        }else
            System.out.println("Posts is empty");
    }

    public void savePost(Post post) {
        postController.save(post);
        System.out.println("Post save successful");
    }

    public void updatePost(Post post) {
        postController.update(post);
        System.out.println("Post updated successful");
    }

    public void deletePost(Post post) {
        postController.deleteById(post);
        System.out.println("Post deleted successful");
    }
}
