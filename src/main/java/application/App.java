package application;

import controller.LabelStatement;
import controller.PostStatement;
import controller.PostStatusStatement;
import controller.WriterStatement;
import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
import model.enums.PostStatus;
import utils.database.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner scanner;

    static {
        try {
            Class.forName(ConnectionPool.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        App app = new App();
        scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------------");
        while (true) {
            System.out.print("    **** PLEASE, SELECT ACTION ****\n");
            Thread.sleep(1000);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "add Writer (1)\n" +
                    "add Post (2)\n" +
                    "add Label (3)\n" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "read all Writers (5)\n" +
                    "read all Posts (6)\n" +
                    "read all Label (7)\n" +
                    "read all PostStatus (8)\n" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "update Writer (9)\n" +
                    "update Post (10)\n" +
                    "update Label (11)\n" +
                    "update PostStatus (12)\n" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "delete Writer (13)\n" +
                    "delete Post (14)\n" +
                    "delete Label (15)\n" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "exit (93) :(");
            String action = scanner.nextLine();
            if(action.equals("99"))
                return;
            app.processResponse(action);
        }
    }

    public void processResponse(String action) {
        switch (action) {
            case "1":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("**** PLEASE ENTER INFORMATION ABOUT WRITER ****");
                System.out.print("enter writer name: ");
                String writerName = scanner.nextLine();
                System.out.print("enter writer last name: ");
                String writerLastName = scanner.nextLine();

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
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");

                Writer selectedWriter = writers.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.print("enter post content: ");
                String postContent = scanner.nextLine();
                System.out.print("enter label name: ");
                String labelName = scanner.nextLine();

                Post newPost = new Post(postContent, new Timestamp(System.currentTimeMillis()), new ArrayList<>());
                Label newLabel = new Label(labelName);
                newPost.addLabel(newLabel);
                selectedWriter.addPost(newPost);

                PostStatement postStatement = new PostStatement(selectedWriter);
                postStatement.create(newPost);
                System.out.println("**** POST ADDED SUCCESSFUL ****\n");
                postStatement.updatePostView();
                break;
            case "3":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER, YOU WANT TO LABEL ADD ****");
                writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");

                selectedWriter = writers.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.println("**** PLEASE CHOOSE THE POST, YOU WANT TO LABEL ADD ****");
                List<Post> posts = selectedWriter.posts();

                Post currentPost;
                if(!posts.isEmpty()) {
                    for (int j = 0; j < posts.size(); j++)
                        System.out.println(posts.get(j) + " (" + (j + 1) + ")");
                    currentPost = posts.get(Integer.parseInt(scanner.nextLine()) - 1);
                }else {
                    System.out.println("--- The posts off this writers is empty, please add post to this writer ---");
                    break;
                }

                System.out.print("enter label name: ");

                newLabel = new Label(scanner.nextLine());
                currentPost.addLabel(newLabel);
                LabelStatement labelStatement = new LabelStatement(currentPost);
                labelStatement.create(newLabel);
                System.out.println("**** LABEL ADDED SUCCESSFUL ****\n");

                labelStatement.updateLabelView();
                break;
            case "5":
                new WriterStatement().updateWriterView();
                break;
            case "6":
                new PostStatement().updatePostView();
                break;
            case "7":
                new LabelStatement().updateLabelView();
                break;
            case "8":
                new PostStatusStatement().updatePostStatusView();
                break;
            case "9":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER YOU WANT TO UPDATE ****");
                writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                selectedWriter = writers.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.println("**** PLEASE ENTER THE FIELD YOU WANT TO UPDATE ****");
                Field[] fields = Writer.class.getDeclaredFields();

                for (int j = 0; j < fields.length; j++) {
                    if(!fields[j].getName().toLowerCase().contains("posts"))
                        System.out.println(fields[j].getName() + " (" + (j + 1) + ")");
                }
                System.out.println("all (" + (fields.length + 1) + ")");
                int f = Integer.parseInt(scanner.nextLine()) - 1;

                if(f == fields.length) {
                    String firstName = "";
                    String lastName = "";
                    for (Field field : fields) {
                        if (!field.getName().toLowerCase().contains("posts")) {
                            System.out.print("enter updated " + field.getName().toLowerCase() + ": ");
                            if (field.getName().toLowerCase().contains("first"))
                                firstName = scanner.nextLine();
                            else
                                lastName = scanner.nextLine();
                        }
                    }
                    newWriter = new Writer(selectedWriter.id(), firstName, lastName, selectedWriter.posts());
                }else {

                    System.out.print("enter updated " + fields[f].getName().toLowerCase() + ": ");
                    String update = scanner.nextLine();

                    if (fields[f].getName().contains("first"))
                        newWriter = new Writer(selectedWriter.id(), update, selectedWriter.lastName(), selectedWriter.posts());
                    else
                        newWriter = new Writer(selectedWriter.id(), selectedWriter.firstName(), update, selectedWriter.posts());
                }
                writerStatement = new WriterStatement();
                writerStatement.update(newWriter);
                System.out.println("****WRITER UPDATED SUCCESSFUL****\n");

                writerStatement.updateWriterView();
                break;
            case "10":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO UPDATE THE POST ****");

                writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                selectedWriter = writers.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.println("**** PLEASE CHOOSE THE POST YOU WANT TO UPDATE ****");
                posts = selectedWriter.posts();
                for(int j = 0; j < posts.size(); j++) {
                    System.out.println(posts.get(j) + " (" + (j + 1) + ")");
                }
                currentPost = posts.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.print("enter updated content: ");
                String update = scanner.nextLine();

                Post updatePost = new Post(currentPost.id(), update, currentPost.created(), new Timestamp(System.currentTimeMillis()), currentPost.labels());

                postStatement = new PostStatement(selectedWriter);
                postStatement.update(updatePost);
                System.out.println("****POST UPDATED SUCCESSFUL****\n");

                postStatement.updatePostView();
                break;
            case "11":
                System.out.println("----------------------------------------------------");
                System.out.println("**** PLEASE CHOOSE THE WRITER FROM WHOM YOU WANT TO UPDATE THE POST ****");

                writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                selectedWriter = writers.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.println("**** PLEASE CHOOSE THE POST FROM WHOM YOU WANT TO UPDATE THE LABEL ****");
                posts = selectedWriter.posts();
                for(int j = 0; j < posts.size(); j++)
                    System.out.println(posts.get(j) + " (" + (j + 1) + ")");

                currentPost = posts.get(Integer.parseInt(scanner.nextLine()) - 1);

                System.out.println("**** PLEASE CHOOSE THE LABEL YOU WANT TO UPDATE ****");

                List<Label> labels = currentPost.labels();
                for(int j = 0; j < labels.size(); j++)
                    System.out.println(labels.get(j) + " (" + (j + 1) + ")");

                Label selectedLabel = labels.get(Integer.parseInt(scanner.nextLine()) - 1);
                System.out.print("enter updated label name: ");
                update = scanner.nextLine();

                Label updateLabel = new Label(selectedLabel.id(), update);
                labelStatement = new LabelStatement();
                labelStatement.update(updateLabel);
                System.out.println("****POST UPDATED SUCCESSFUL****\n");

                labelStatement.updateLabelView();
                break;
            default:
                throw new RuntimeException("Sorry no such action");
        }
    }
}
