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

    public void createLabel() {
        labelService.createLabel();
    }

    public Label label() {
        return labelService.label();
    }

    public void updateLabel() {
        labelService.updateLabel();
    }

    public void deleteLabel() {
        labelService.deleteLabel();
    }
}
