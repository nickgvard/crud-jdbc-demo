package controller;

import model.enums.PostStatus;
import service.PostStatusService;
import view.PostStatusView;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusStatement {

    private final PostStatusService statusService;
    private final PostStatusView statusView;

    public PostStatusStatement() {
        statusService = new PostStatusService();
        statusView = new PostStatusView();
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
