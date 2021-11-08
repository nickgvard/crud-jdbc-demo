package application;

import controller.LabelStatement;
import controller.PostStatement;
import controller.WriterStatement;
import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CreateProcess extends Process {


    public void create(String action) {
        switch (action) {
            case "1":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("**** PLEASE ENTER INFORMATION ABOUT WRITER ****");
                System.out.print("enter writer name: ");
                String writerName = scanner().nextLine();
                System.out.print("enter writer last name: ");
                String writerLastName = scanner().nextLine();

                Writer newWriter = new Writer(writerName, writerLastName);
                WriterStatement writerStatement = new WriterStatement();

                writerStatement.create(newWriter);
                System.out.println("**** WRITER ADDED SUCCESSFUL ****\n");
                writerStatement.updateWriterView();
                break;
            case "2":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER, YOU WANT TO POST ADD ****");
                List<Writer> writers = new WriterStatement().read();
                Writer selectedWriter;
                Label newLabel;
                PostStatement postStatement;

                if (!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");

                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.print("enter post content: ");
                    String postContent = scanner().nextLine();
                    System.out.print("enter label name: ");
                    String labelName = scanner().nextLine();

                    Post newPost = new Post(postContent, new Timestamp(System.currentTimeMillis()), new ArrayList<>());
                    newLabel = new Label(labelName);
                    newPost.addLabel(newLabel);
                    selectedWriter.addPost(newPost);

                    postStatement = new PostStatement(selectedWriter);
                    postStatement.create(newPost);
                    System.out.println("**** POST ADDED SUCCESSFUL ****\n");
                    postStatement.updatePostView();
                } else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            case "3":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER, YOU WANT TO LABEL ADD ****");
                writers = new WriterStatement().read();
                List<Post> posts;
                Post selectedPost;
                LabelStatement labelStatement;

                if (!writers.isEmpty()) {
                    for (int j = 0; j < writers.size(); j++)
                        System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");

                    selectedWriter = writers.get(Integer.parseInt(scanner().nextLine()) - 1);

                    System.out.println("**** PLEASE CHOOSE THE POST, YOU WANT TO LABEL ADD ****");
                    posts = selectedWriter.posts();

                    if (!posts.isEmpty()) {
                        for (int j = 0; j < posts.size(); j++)
                            System.out.println(posts.get(j) + " (" + (j + 1) + ")");
                        selectedPost = posts.get(Integer.parseInt(scanner().nextLine()) - 1);
                    } else {
                        System.out.println("--- The posts off this writers is empty, please add post to this writer ---");
                        break;
                    }

                    System.out.print("enter label name: ");

                    newLabel = new Label(scanner().nextLine());
                    selectedPost.addLabel(newLabel);
                    labelStatement = new LabelStatement(selectedPost);
                    labelStatement.create(newLabel);
                    System.out.println("**** LABEL ADDED SUCCESSFUL ****\n");

                    labelStatement.updateLabelView();
                } else
                    System.out.println("--- writers is empty, please add the writer ---");
                break;
            default:
                throw new RuntimeException("No such action");
        }
    }
}
