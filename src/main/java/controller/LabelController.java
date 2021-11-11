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

    public Label getById(long id) {
        return labelService.getById(id);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public void save(Label entity) {
        labelService.save(entity);
    }

    public void update(Label entity) {
        labelService.update(entity);
    }

    public void delete(Label entity) {
        labelService.deleteById(entity);
    }
}