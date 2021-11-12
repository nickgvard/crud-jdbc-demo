package controller;

import enums.PostStatus;
import service.PostStatusService;

import java.util.List;

public class PostStatusController {

    private final PostStatusService statusService;

    public PostStatusController() {
        statusService = new PostStatusService();
    }

    public PostStatus getById(long id) {
        return statusService.getById(id);
    }

    public List<PostStatus> getAll() {
        return statusService.getAll();
    }

    public PostStatus save(PostStatus postStatus) {
        return statusService.save(postStatus);
    }

    public PostStatus update(PostStatus postStatus) {
        return statusService.update(postStatus);
    }

    public PostStatus deleteById(PostStatus postStatus) {
        return statusService.deleteById(postStatus);
    }


}
