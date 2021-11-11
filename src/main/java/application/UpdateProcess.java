package application;

import controller.LabelController;
import controller.PostController;
import controller.WriterController;
import model.Label;
import model.Post;
import model.Writer;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

public class UpdateProcess extends Process {

    public void update(String action) {
        List<Writer> writers;
        Writer selectedWriter;
        Writer newWriter;
        WriterController writerController;
        Post selectedPost;
        List<Post> posts;
        PostController postController;
        LabelController labelController;

        switch (action) {
            case "9":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER YOU WANT TO UPDATE ****");
                writers = new WriterController().getAll();

                if(!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE ENTER THE FIELD YOU WANT TO UPDATE ****");
                    Field[] fields = Writer.class.getDeclaredFields();

                    for (int j = 0; j < fields.length; j++) {
                        if (!fields[j].getName().toLowerCase().contains("posts"))
                            System.out.println(fields[j].getName() + " (" + (j + 1) + ")");
                    }
                    System.out.println("all (" + (fields.length + 1) + ")");
                    int f = Integer.parseInt(scanner().nextLine()) - 1;

                    if (f == fields.length) {
                        String firstName = "";
                        String lastName = "";
                        for (Field field : fields) {
                            if (!field.getName().toLowerCase().contains("posts")) {
                                System.out.print("enter updated " + field.getName().toLowerCase() + ": ");
                                if (field.getName().toLowerCase().contains("first"))
                                    firstName = scanner().nextLine();
                                else
                                    lastName = scanner().nextLine();
                            }
                        }
                        newWriter = new Writer(selectedWriter.id(), firstName, lastName, selectedWriter.posts());
                    } else {

                        System.out.print("enter updated " + fields[f].getName().toLowerCase() + ": ");
                        String update = scanner().nextLine();

                        if (fields[f].getName().contains("first"))
                            newWriter = new Writer(selectedWriter.id(), update, selectedWriter.lastName(), selectedWriter.posts());
                        else
                            newWriter = new Writer(selectedWriter.id(), selectedWriter.firstName(), update, selectedWriter.posts());
                    }
                    writerController = new WriterController();
                    writerController.update(newWriter);
                    System.out.println("****WRITER UPDATED SUCCESSFUL****\n");

                    writerController.updateWriterView();
                }else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            case "10":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO UPDATE THE POST ****");
                String update;
                writers = new WriterController().getAll();

                if(!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE CHOOSE THE POST YOU WANT TO UPDATE ****");
                    posts = selectedWriter.posts();

                    if(!posts.isEmpty()) {
                        for (int j = 0; j < posts.size(); j++)
                            System.out.println(posts.get(j) + " (" + (j + 1) + ")");

                        selectedPost = posts.get(Integer.parseInt(scanner().nextLine()) - 1);

                        System.out.print("enter updated content: ");
                        update = scanner().nextLine();

                        Post updatePost = new Post(selectedPost.id(), update, selectedPost.created(), new Timestamp(System.currentTimeMillis()), selectedPost.labels());

                        postController = new PostController(selectedWriter);
                        postController.update(updatePost);
                        System.out.println("****POST UPDATED SUCCESSFUL****\n");

                        postController.updatePostView();
                    }else
                        System.out.println("--- The posts off this writers is empty, please add post to this writer ---");
                    break;
                }else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            case "11":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO UPDATE THE POST ****");

                List<Label> labels;
                Label selectedLabel;
                writers = new WriterController().getAll();

                if (!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE CHOOSE THE POST FROM WHOM YOU WANT TO UPDATE THE LABEL ****");
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
                            System.out.print("enter updated label name: ");
                            update = scanner().nextLine();

                            Label updateLabel = new Label(selectedLabel.id(), update);
                            labelController = new LabelController();
                            labelController.update(updateLabel);
                            System.out.println("**** POST UPDATED SUCCESSFUL ****\n");

                            labelController.updateLabelView();
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
