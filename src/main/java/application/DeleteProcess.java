package application;

import controller.LabelStatement;
import controller.PostStatement;
import controller.WriterStatement;
import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;

import java.util.List;

public class DeleteProcess extends Process {

    public void delete(String action) {
        WriterStatement writerStatement;
        List<Writer> writers;
        Writer selectedWriter;
        List<Post> posts;
        Post selectedPost;
        PostStatement postStatement;
        List<Label> labels;
        Label selectedLabel;
        LabelStatement labelStatement;

        switch (action) {
            case "12":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER YOU WANT TO DELETE ****");

                writerStatement = new WriterStatement();
                writers = writerStatement.read();

                if(!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    writerStatement.delete(selectedWriter);
                    System.out.println("**** WRITER DELETED SUCCESSFUL ****\n");

                    writerStatement.updateWriterView();
                }else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            case "13":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO DELETE THE POST ****");

                writers = new WriterStatement().read();

                if(!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE CHOOSE THE POST YOU WANT TO DELETE ****");
                    posts = selectedWriter.posts();

                    if(!posts.isEmpty()) {
                        for (int j = 0; j < posts.size(); j++)
                            System.out.println(posts.get(j) + " (" + (j + 1) + ")");

                        selectedPost = posts.get(Integer.parseInt(scanner().nextLine()) - 1);

                        postStatement = new PostStatement();
                        postStatement.delete(selectedPost);
                        System.out.println("**** POST DELETED SUCCESSFUL ****\n");

                        postStatement.updatePostView();
                    }else
                        System.out.println("--- The posts off this writers is empty, please add post to this writer ---");
                }else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            case "14":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO DELETE THE LABEL ****");

                writers = new WriterStatement().read();

                if (!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE CHOOSE THE POST FROM WHOM YOU WANT TO DELETE THE LABEL ****");
                    posts = selectedWriter.posts();

                    if(!posts.isEmpty()) {
                        for (int j = 0; j < posts.size(); j++)
                            System.out.println(posts.get(j) + " (" + (j + 1) + ")");

                        selectedPost = posts.get(Integer.parseInt(scanner().nextLine()) - 1);

                        System.out.println("**** PLEASE CHOOSE THE LABEL YOU WANT TO UPDATE ****");

                        labels = selectedPost.labels();

                        if(!labels.isEmpty()) {
                            for (int j = 0; j < labels.size(); j++)
                                System.out.println(labels.get(j) + " (" + (j + 1) + ")");

                            selectedLabel = labels.get(Integer.parseInt(scanner().nextLine()) - 1);

                            labelStatement = new LabelStatement();
                            labelStatement.delete(selectedLabel);
                            System.out.println("**** POST DELETED SUCCESSFUL ****\n");

                            labelStatement.updateLabelView();
                        }else
                            System.out.println("--- The labels off this posts is empty, please add labels to this post ---");
                    }else
                        System.out.println("--- The posts off this writers is empty, please add post to this writer ---");
                }else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            default:
                throw new RuntimeException("No such action");
        }
    }
}
