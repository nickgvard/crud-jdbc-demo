package service;

import model.Label;
import repository.jdbc_impl.JDBCLabelRepositoryImpl;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class LabelService {

    private JDBCLabelRepositoryImpl labelRepository;

    public LabelService() {
        labelRepository = new JDBCLabelRepositoryImpl();
    }

    public Label getById(long id) {
        return labelRepository.getById(id);
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public Label save(Label label) {
        return labelRepository.save(label);
    }

    public Label update(Label label) {
        return labelRepository.update(label);
    }

    public Label deleteById(Label label) {
        return labelRepository.deleteById(label);
    }
}