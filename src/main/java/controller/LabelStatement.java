package controller;

import service.LabelService;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelStatement {

    private final LabelService labelService;

    public LabelStatement() {
        labelService = new LabelService();
    }

    public void createLabelStatement() {
        labelService.createLabelService();
    }

    public void readLabelStatement() {
        labelService.readLabelService();
    }

    public void updateLabelStatement() {
        labelService.updateLabelService();
    }

    public void deleteLabelStatement() {
        labelService.deleteLabelService();
    }
}
