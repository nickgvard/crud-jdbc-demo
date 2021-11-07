package controller;

import model.entity.Label;
import service.LabelService;
import view.LabelView;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelStatement {

    private final LabelService labelService;
    private final LabelView labelView;

    public LabelStatement() {
        labelService = new LabelService();
        labelView = new LabelView();
    }

    public void update(Label entity) {
        labelService.update(entity);
    }

    public void delete(Label entity) {
        labelService.delete(entity);
    }

    public List<Label> read() {
        return labelService.read();
    }

    public void updateLabelView() {
        labelView.showAllLabels(read());
    }
}
