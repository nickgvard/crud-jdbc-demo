package repository;

import model.entity.Label;
import utils.database.DataBaseSource;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class LabelRepositoryImp implements LabelRepository{

    private final Label label;
    private final DataBaseSource dataSource;

    public LabelRepositoryImp(final Label label) {
        this.label = label;
        dataSource = new DataBaseSource();
    }

    @Override
    public void addLabel(Label label) {
        dataSource.createData();
    }

    @Override
    public void removeLabel(Label label) {
        dataSource.deleteData();
    }

    @Override
    public void updateLabel(Label label) {
        dataSource.updateData();
    }

    @Override
    public Label label(Label label) {
        return (Label) dataSource.data();
    }
}