package application;

import utils.database.ConnectionPool;

import java.util.Scanner;

public class Process {

    private final Scanner scanner;
    private final ProcessStatement processStatement;

    static {
        try {
            Class.forName(ConnectionPool.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Process() {
        processStatement = new ProcessStatement();
        scanner = new Scanner(System.in);
    }

    public Scanner scanner() {
        return scanner;
    }

    public static void main(String[] args) throws InterruptedException {
        Process process = new Process();
        System.out.println("-----------------------------------------------------------------------");
        while (true) {
            System.out.print("\n    **** PLEASE, SELECT ACTION ****\n");
            Thread.sleep(500);
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
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "delete Writer (12)\n" +
                    "delete Post (13)\n" +
                    "delete Label (14)\n" +
                    "+++++++++++++++++++++++++++++++++++++++++++++++\n" +
                    "exit (93) :(");
            String action = process.scanner.nextLine();
            if(action.equals("93"))
                return;
            process.processAction(action);
        }
    }

    public void processAction(String action) {
        processStatement.processResponse(action);
    }
}
