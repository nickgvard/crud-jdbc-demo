package controller;

import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
import service.LabelService;
import view.LabelView;

import java.util.List;

public class LabelStatement {

    private final LabelService labelService;
    private final LabelView labelView;

    public LabelStatement() {
        labelService = new LabelService();
        labelView = new LabelView();
    }

    public LabelStatement(Post post) {
        labelService = new LabelService(post);
        labelView = new LabelView();
    }

    public void create(Label entity) {
        labelService.create(entity);
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
