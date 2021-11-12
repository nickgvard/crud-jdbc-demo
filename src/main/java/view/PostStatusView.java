package view;

import controller.PostStatusController;
import enums.PostStatus;

import java.util.List;

public class PostStatusView {

    private final PostStatusController postStatusController;

    public PostStatusView() {
        postStatusController = new PostStatusController();
    }

    public void showPostStatusById(long id) {
        PostStatus postStatus = postStatusController.getById(id);
        if(postStatus != null) {
            System.out.println(postStatus.name());
        }else
            System.out.println("Not found post status by this id");
    }

    public void showAllPostStatus() {
        List<PostStatus> postStatuses = postStatusController.getAll();
        if(!postStatuses.isEmpty()) {
            for (PostStatus postStatus : postStatuses) {
                System.out.println(postStatus.name().toLowerCase());
            }
        }else
            System.out.println("Post statuses is empty");
    }

    public void savePostStatus(PostStatus postStatus) {
        postStatusController.save(postStatus);
        System.out.println("Post status save successful");
    }

    public void updatePostStatus(PostStatus postStatus) {
        postStatusController.update(postStatus);
        System.out.println("Post status updated successful");
    }

    public void deletePostStatus(PostStatus postStatus) {
        postStatusController.deleteById(postStatus);
        System.out.println("Post status deleted successful");
    }
}