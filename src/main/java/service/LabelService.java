package service;

import model.entity.Label;
import repository.LabelRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelService {

    private final LabelRepository labelRepository;
    private final Label label;

    public LabelService(final Label label) {
        this.label = label;
        labelRepository = new LabelRepository(label);
    }

    public void createLabelService() {
        labelRepository.createLabelRepository();
    }

    public Label readLabelService() {
        return labelRepository.readLabelRepository();
    }

    public void updateLabelService() {
        labelRepository.updateLabelRepository();
    }

    public void deleteLabelService() {
        labelRepository.deleteLabelRepository();
    }

}
