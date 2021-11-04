package controller;

import model.enums.PostStatus;
import service.PostStatusService;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusStatement {

    private final PostStatus postStatus;
    private final PostStatusService statusService;

    public PostStatusStatement(PostStatus postStatus) {
        this.postStatus = postStatus;
        statusService = new PostStatusService(postStatus);
    }

    public void createPostStatus() {
        statusService.createPostStatus();
    }

    public PostStatus postStatus() {
        return statusService.postStatus();
    }

    public void updatePostStatus() {
        statusService.updatePostStatus();
    }

    public void deletePostStatus() {
        statusService.deletePostStatus();
    }
}
