package controller;

import model.entity.Writer;
import service.WriterService;
import view.WriterView;

import java.util.List;

public class WriterStatement {

    private final WriterService writerService;
    private final WriterView writerView;

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
