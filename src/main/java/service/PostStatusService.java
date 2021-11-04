package service;

import model.enums.PostStatus;
import repository.PostStatusRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusService {

    private final PostStatus postStatus;
    private PostStatusRepository statusRepository;

    public PostStatusService(PostStatus postStatus) {
        this.postStatus = postStatus;
        statusRepository = new PostStatusRepository(postStatus);
    }

    public void createPostStatus() {
        statusRepository.createPostStatus();
    }

    public PostStatus postStatus() {
        return statusRepository.postStatus();
    }

    public void updatePostStatus() {
        statusRepository.updatePostStatus();
    }

    public void deletePostStatus() {
        statusRepository.deletePostStatus();
    }
}