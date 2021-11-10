package controller;

import model.entity.Label;
import model.entity.Post;
import service.LabelService;

import java.util.List;

public class LabelController {

    private final LabelService labelService;

    public LabelController() {
        labelService = new LabelService();
    }

    public LabelController(Post post) {
        labelService = new LabelService(post);
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
