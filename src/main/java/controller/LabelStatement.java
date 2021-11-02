package controller;

import model.entity.Label;
import service.LabelService;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelStatement {

    private final LabelService labelService;
    private final Label label;

    public LabelStatement(final int id, final String name) {
        label = new Label(id, name);
        labelService = new LabelService(label);
    }

    public void createLabelStatement() {
        labelService.createLabelService();
    }

    public Label readLabelStatement() {
        return labelService.readLabelService();
    }

    public void updateLabelStatement() {
        labelService.updateLabelService();
    }

    public void deleteLabelStatement() {
        labelService.deleteLabelService();
    }
}
