package controller;

import model.Writer;
import service.WriterService;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

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

    public Writer save(Writer entity) {
        return writerService.save(entity);
    }

    public Writer update(Writer entity) {
        return writerService.update(entity);
    }

    public Writer deleteById(Writer entity) {
        return writerService.deleteById(entity);
    }
}