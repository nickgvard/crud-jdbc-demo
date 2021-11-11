package controller;

import model.Writer;
import service.WriterService;

import java.util.List;

public class WriterController {

    private final WriterService writerService;

    public WriterController() {
        writerService = new WriterService();
    }

    public Writer getById(long id) {
        return writerService.getById(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public void save(Writer entity) {
        writerService.save(entity);
    }

    public void update(Writer entity) {
        writerService.update(entity);
    }

    public void deleteById(Writer entity) {
        writerService.deleteById(entity);
    }
}