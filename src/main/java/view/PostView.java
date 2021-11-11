package view;

import model.Label;
import model.Post;

import java.util.List;

public class PostView {

    public void showAllPosts(List<Post> posts) {
        System.out.println("----------------------------------------------------");
        if(!posts.isEmpty()) {
            System.out.println("ALL POSTS FROM DATA BASE");
            for (Post post : posts) {
                System.out.println(post);
                if (!post.labels().isEmpty()) {
                    System.out.println("labels of the post:");
                    for (Label label : post.labels())
                        System.out.println(label);
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            }
            System.out.println("----------------------------------------------------");
        }else
            System.out.println("---Posts is empty---");
    }
}
