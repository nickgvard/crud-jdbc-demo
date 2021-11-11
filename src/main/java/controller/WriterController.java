package controller;

import model.entity.Writer;
import service.WriterService;
import view.WriterView;

import java.util.List;

public class WriterController {

    private final WriterService writerService;

    public WriterController() {
        writerService = new WriterService();
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
}
