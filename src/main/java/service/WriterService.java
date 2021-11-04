package service;

import model.entity.Writer;
import repository.WriterRepository;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class WriterService {

    private final Writer writer;
    private WriterRepository writerRepository;

    public WriterService(Writer writer) {
        this.writer = writer;
        writerRepository = new WriterRepository(writer);
    }

    public void createWriter() {
        writerRepository.createWriter();
    }

    public Writer writer() {
        return writerRepository.writer();
    }

    public void updateWriter() {
        writerRepository.updateWriter();
    }

    public void deleteWriter() {
        writerRepository.deleteWriter();
    }
}
