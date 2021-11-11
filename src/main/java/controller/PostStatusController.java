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

    public PostStatus update(PostStatus entity) {
        return statusService.update(entity);
    }

    public PostStatus deleteById(PostStatus entity) {
        return statusService.deleteById(entity);
    }


}
