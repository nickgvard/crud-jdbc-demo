package repository;

import model.entity.Label;
import utils.database.DataBaseSource;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelRepository {

    private final Label label;
    private final DataBaseSource dataSource;

    public LabelRepository(final Label label) {
        this.label = label;
        dataSource = new DataBaseSource();
    }

    public void createLabelRepository() {
        System.out.println("Label " + label.id() + ":" +  label.name() + " inserted in database");
    }

    public Label readLabelRepository() {
        return (Label)dataSource.readData();
    }

    public void updateLabelRepository() {

    }

    public void deleteLabelRepository() {

    }
}