package service;

import model.enums.PostStatus;
import repository.PostStatusRepository;

import java.util.List;

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

    public void create() {
        statusRepository.add(postStatus);
    }

    public List<PostStatus> read() {
        return statusRepository.read();
    }

    public void update() {
        statusRepository.update(postStatus);
    }

    public void delete() {
        statusRepository.remove(postStatus);
    }
}