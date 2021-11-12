package view;

import controller.WriterController;
import model.Label;
import model.Post;
import model.Writer;

import java.util.List;

public class WriterView {

    private final WriterController writerController;

    public WriterView() {
        writerController = new WriterController();
    }

    public void showWriterById(long id) {
        Writer writer = writerController.getById(id);
        if(writer != null) {
            System.out.println("----------------------------------------------------");
            System.out.println(writer);
        }else
            System.out.println("Not found writer by this id");
    }

    public void showAllWriters() {
        List<Writer> writers = writerController.getAll();
        if(!writers.isEmpty()) {
            System.out.println("----------------------------------------------------");
            System.out.println("ALL WRITERS FROM DATA BASE");
            for (Writer writer : writers) {
                System.out.println(writer);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            }
            System.out.println("----------------------------------------------------");
        }else
            System.out.println("---Writer is empty---");
    }

    public void saveWriter(Writer writer) {
        writerController.save(writer);
        System.out.println("Writer save successful");
    }

    public void updateWriter(Writer writer) {
        writerController.update(writer);
        System.out.println("Writer updated successful");
    }

    public void deleteWriter(Writer writer) {
        writerController.deleteById(writer);
        System.out.println("Writer deleted successful");
    }
}
