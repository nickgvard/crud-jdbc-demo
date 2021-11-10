package view;

import model.entity.Label;

import java.util.List;

public class LabelView {

    private

    public void showAllLabels(List<Label> labels) {
        System.out.println("----------------------------------------------------");
        if(!labels.isEmpty()) {
            System.out.println("ALL LABELS FROM DATA BASE");
            for (Label label : labels) {
                System.out.println(label);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            }
            System.out.println("----------------------------------------------------");
        }else
            System.out.println("Labels is empty");
    }
}
