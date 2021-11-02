package service;

import repository.LabelRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelService() {
        labelRepository = new LabelRepository();
    }

    public void createLabelService() {
        labelRepository.createLabelRepository();
    }

    public void readLabelService() {
        labelRepository.readLabelRepository();
    }

    public void updateLabelService() {
        labelRepository.updateLabelRepository();
    }

    public void deleteLabelService() {
        labelRepository.deleteLabelRepository();
    }

}
