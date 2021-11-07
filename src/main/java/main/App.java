package main;

import controller.LabelStatement;
import controller.PostStatement;
import controller.WriterStatement;
import model.entity.Label;
import model.entity.Post;
import model.entity.Writer;
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
            System.out.println("    ****PLEASE, SELECT ACTION****\n");
            Thread.sleep(1000);
            System.out.println(".................................\n" +
                    "add Writer (1)\n" +
                    "add Post (2)\n" +
                    "add PostStatus (3)\n" +
                    ".................................\n" +
                    "read all Writers (4)\n" +
                    "read all Posts (5)\n" +
                    "read all Label (6)\n" +
                    ".................................\n" +
                    "update Writer (8)\n" +
                    "update Post (9)\n" +
                    "update Label (10)\n" +
                    ".................................\n" +
                    "delete Writer (11)\n" +
                    "delete Post (12)\n" +
                    ".................................\n" +
                    "exit (7) :(");
            String action = scanner.nextLine();
            if(action.equals("7"))
                return;
            app.processResponse(action);
        }
    }

    public void processResponse(String action) {
        switch (action) {
            case "1":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("****PLEASE ENTER INFORMATION ABOUT WRITER****");
                System.out.print("enter writer name: ");
                String writerName = scanner.nextLine();
                System.out.print("enter writer last name: ");
                String writerLastName = scanner.nextLine();

                Writer writer = new Writer(writerName, writerLastName);
                WriterStatement writerStatement = new WriterStatement();

                writerStatement.create(writer);
                System.out.println("****WRITER ADDED SUCCESSFUL****\n");
                writerStatement.updateWriterView();
                break;
            case "2":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("****PLEASE CHOOSE THE WRITER YOU WANT TO POST TO****");
                List<Writer> writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                int w = Integer.parseInt(scanner.nextLine()) - 1;

                System.out.print("enter post content: ");
                String postContent = scanner.nextLine();
                System.out.print("enter label name: ");
                String labelName = scanner.nextLine();

                Post post = new Post(postContent, new Timestamp(System.currentTimeMillis()), new ArrayList<>());
                Label label = new Label(labelName);
                post.addLabel(label);
                writers.get(w).addPost(post);

                PostStatement postStatement = new PostStatement(writers.get(w));
                postStatement.create(post);
                System.out.println("****POST ADDED SUCCESSFUL****\n");
                postStatement.updatePostView();
                break;
            case "4":
                new WriterStatement().updateWriterView();
                break;
            case "5":
                new PostStatement().updatePostView();
                break;
            case "6":
                new LabelStatement().updateLabelView();
                break;
            case "8":
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("****PLEASE CHOOSE THE WRITER YOU WANT TO UPDATE****");
                writers = new WriterStatement().read();
                for (int j = 0; j < writers.size(); j++)
                    System.out.println(writers.get(j).firstName() + " " + writers.get(j).lastName() + " (" + (j + 1) + ")");
                w = Integer.parseInt(scanner.nextLine()) - 1;

                System.out.println("****PLEASE ENTER THE FIELD YOU WANT TO UPDATE****");
                Field[] fields = Writer.class.getFields();

                for (int j = 0; j < writers.size(); j++) {
                    if(!fields[j].getName().toLowerCase().contains("posts"))
                        System.out.println(fields[j] + " (" + (j + 1) + ")");
                }
                int f = Integer.parseInt(scanner.nextLine()) - 1;



                break;
            default:
                throw new RuntimeException("Sorry no such action");
        }
    }
}
