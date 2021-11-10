package service;

import model.entity.Writer;
import repository.jdbc_impl.JDBCWriterRepositoryImpl;

import java.util.List;

public class WriterService {

    private JDBCWriterRepositoryImpl writerRepository;

    public WriterService() {
        writerRepository = new JDBCWriterRepositoryImpl();
    }

    public void create(Writer writer) {
        writerRepository.save(writer);
    }

    public List<Writer> read() {
        return writerRepository.read();
    }

    public void update(Writer writer) {
        writerRepository.update(writer);
    }

    public void delete(Writer writer) {
        writerRepository.deleteById(writer);
    }
}