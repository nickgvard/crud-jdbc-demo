package controller;

import model.entity.Writer;
import service.WriterService;
import view.WriterView;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class WriterStatement {

    private WriterService writerService;
    private WriterView writerView;

    public WriterStatement() {
        writerService = new WriterService();
        writerView = new WriterView();
    }

    public void create(Writer entity) {
        writerService.create(entity);
    }

    public void update(Writer entity) {
        writerService.update(entity);
    }

    public void delete(Writer entity) {
        writerService.delete(entity);
    }

    public List<Writer> read() {
        return writerService.read();
    }

    public void updateWriterView() {
        writerView.showAllWriters(read());
    }
}
