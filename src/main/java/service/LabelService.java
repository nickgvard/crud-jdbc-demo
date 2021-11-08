package service;

import model.entity.Label;
import model.entity.Post;
import repository.LabelRepository;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelService {

    private LabelRepository labelRepository;

    public LabelService() {
        labelRepository = new LabelRepository();
    }

    public LabelService(Post post) {
        labelRepository = new LabelRepository(post);
    }

    public void create(Label label) {
        labelRepository.add(label);
    }

    public void update(Label label) {
        labelRepository.update(label);
    }

    public void delete(Label label) {
        labelRepository.remove(label);
    }

    public List<Label> read() {
        return labelRepository.read();
    }
}