package service;

import model.entity.Label;
import repository.LabelRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelService {

    private LabelRepository labelRepository;
    private final Label label;

    public LabelService(final Label label) {
        this.label = label;
        labelRepository = new LabelRepository(label);
    }

    public void createLabel() {
        labelRepository.createLabel();
    }

    public Label label() {
        return labelRepository.label();
    }

    public void updateLabel() {
        labelRepository.updateLabel();
    }

    public void deleteLabel() {
        labelRepository.deleteLabel();
    }
}