package service;

import model.Writer;
import repository.jdbc_impl.JDBCWriterRepositoryImpl;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public class WriterService {

    private JDBCWriterRepositoryImpl writerRepository;

    public WriterService() {
        writerRepository = new JDBCWriterRepositoryImpl();
    }

    public Writer getById(long id) {
        return writerRepository.getById(id);
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    public Writer deleteById(Writer writer) {
        return writerRepository.deleteById(writer);
    }
}