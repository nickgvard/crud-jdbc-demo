package controller;

import model.enums.PostStatus;
import service.PostStatusService;

import java.util.List;

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

    public void create() {
        statusService.create();
    }

    public void update() {
        statusService.update();
    }

    public void delete() {
        statusService.delete();
    }

    public List<PostStatus> read() {
        return statusService.read();
    }
}
