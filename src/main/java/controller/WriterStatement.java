package controller;

import model.entity.Post;
import model.entity.Writer;
import service.WriterService;

import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class WriterStatement {

    private final Writer writer;
    private final WriterService writerService;

    public WriterStatement(int id, String firstName, String lastName) {
        writer = new Writer(id, firstName, lastName);
        writerService = new WriterService(writer);
    }

    public WriterStatement(int id, String firstName, String lastName, List<Post> posts) {
        writer = new Writer(id, firstName, lastName, posts);
        writerService = new WriterService(writer);
    }

    public void createWriter() {
        writerService.createWriter();
    }

    public Writer writer() {
        return writerService.writer();
    }

    public void updateWriter() {
        writerService.updateWriter();
    }

    public void deleteWrite() {
        writerService.deleteWriter();
    }

}
