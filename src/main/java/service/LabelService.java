package service;

import model.entity.Label;
import model.entity.Post;
import repository.jdbc_impl.JDBCLabelRepositoryImpl;

import java.util.List;

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

    public void save(Label label) {
        labelRepository.save(label);
    }

    public void update(Label label) {
        labelRepository.update(label);
    }

    public void deleteById(Label label) {
        labelRepository.deleteById(label);
    }
}