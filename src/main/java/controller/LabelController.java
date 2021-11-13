package controller;

import model.Label;
import service.LabelService;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

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

    public Label save(Label label) {
        return labelService.save(label);
    }

    public Label update(Label label) {
        return labelService.update(label);
    }

    public Label delete(Label label) {
        return labelService.deleteById(label);
    }
}