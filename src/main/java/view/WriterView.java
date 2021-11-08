package view;

import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;

import java.util.List;

public class WriterView {

    public void showAllWriters(List<Writer> writers) {
        System.out.println("----------------------------------------------------");
        if(!writers.isEmpty()) {
            System.out.println("ALL WRITERS FROM DATA BASE");
            for (Writer writer : writers) {
                System.out.println(writer);
                if (!writer.posts().isEmpty()) {
                    System.out.println("posts of the writer:");
                    for (Post post : writer.posts()) {
                        System.out.println("\t" + post);
                        if (!post.labels().isEmpty()) {
                            System.out.println("labels of the post:");
                            for (Label label : post.labels())
                                System.out.println("\t\t" + label);
                        }
                    }
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            }
            System.out.println("----------------------------------------------------");
        }else
            System.out.println("---Writer is empty---");
    }
}
