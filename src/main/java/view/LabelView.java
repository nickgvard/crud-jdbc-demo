package view;

import controller.LabelController;
import model.Label;

import java.util.List;

public class LabelView {

    private final LabelController labelController;

    public LabelView() {
        labelController = new LabelController();
    }

    public void showLabelById(long id) {
        Label label = labelController.getById(id);
        if(label != null) {
            System.out.println(label);
        }else
            System.out.println("Not found label by this id");
    }

    public void showAllLabels() {
        List<Label> labels = labelController.getAll();
        if(!labels.isEmpty()) {
            for (Label label : labels) {
                System.out.println(label);
            }
        }else
            System.out.println("Labels is empty");
    }

    public void save(Label label) {
        labelController.save(label);
        System.out.println("Label save successful");
    }

    public void updateLabel(Label label) {
        labelController.update(label);
        System.out.println("Label updated successful");
    }
    public void deleteLabel(Label label) {
        labelController.delete(label);
        System.out.println("Label deleted successful");
    }
}
