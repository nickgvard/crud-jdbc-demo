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

    public LabelService(Post post) {
        labelRepository = new JDBCLabelRepositoryImpl(post);
    }

    public void create(Label label) {
        labelRepository.save(label);
    }

    public void update(Label label) {
        labelRepository.update(label);
    }

    public void delete(Label label) {
        labelRepository.deleteById(label);
    }

    public List<Label> read() {
        return labelRepository.read();
    }
}