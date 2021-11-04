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

    public void createLabel() {
//        dataSource.createData();
    }

    public Label label() {
        return (Label)dataSource.data();
    }

    public void updateLabel() {
        dataSource.updateData();
    }

    public void deleteLabel() {
        dataSource.deleteData();
    }
}