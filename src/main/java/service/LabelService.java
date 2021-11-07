package service;

import model.entity.Label;
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