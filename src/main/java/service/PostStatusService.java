package service;

import model.enums.PostStatus;
import repository.PostStatusRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class PostStatusService {

    private PostStatusRepository statusRepository;

    public PostStatusService() {
        statusRepository = new PostStatusRepository();
    }

    public List<PostStatus> read() {
        return statusRepository.read();
    }

    public void update(PostStatus entity) {
        statusRepository.update(entity);
    }

    public void delete(PostStatus entity) {
        statusRepository.remove(entity);
    }
}