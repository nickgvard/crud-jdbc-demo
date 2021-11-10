package controller;

import enums.PostStatus;
import service.PostStatusService;
import view.PostStatusView;

import java.util.List;

public class PostStatusController {

    private final PostStatusService statusService;

    public PostStatusController() {
        statusService = new PostStatusService();
    }

    public void update(PostStatus entity) {
        statusService.update(entity);
    }

    public void delete(PostStatus entity) {
        statusService.delete(entity);
    }

    public List<PostStatus> read() {
        return statusService.read();
    }

    public void updatePostStatusView() {
        statusView.showAllPostStatus(read());
    }
}
