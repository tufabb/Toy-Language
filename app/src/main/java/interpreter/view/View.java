package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.repository.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class View {
    Examples check = new Examples();
    String currentCheck = "NONE";

    private void printMenu() {
        IO.println("//--------------------------------//");
        IO.println("SELECTED PROGRAM: " + currentCheck);
        IO.println("1. Select a program");
        IO.println("2. Run the selected program");
        IO.println("0. EXIT");
        IO.println("//--------------------------------//");
    }

    private void printExemples() {
        IO.println("//--------------------------------//");
        IO.println("WHICH PROGRAM (1/2/3): ");
        IO.println("//--------------------------------//");
    }


    public void startUp() {
        Scanner s = new Scanner(System.in);
        while (true) {
            printMenu();
            String s1 = s.nextLine();

            switch (s1) {
                case "0":
                    return;
                case "1": {
                    printExemples();
                    String s2 = s.nextLine();
                    if (s2.equals("1") || s2.equals("2") || s2.equals("3"))
                        currentCheck = s2;
                    else {
                        IO.println("invalid input");
                        break;
                    }
                    break;
                }
                case "2": {
                    if (currentCheck.equals("NONE")) {
                        IO.println("please select a program to execute first");
                        break;
                    }
                    PrgState state;

                    if (currentCheck.equals("1")) {
                         state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), check.exemple1());

                    } else if (currentCheck.equals("2")) {
                         state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), check.exemple2());

                    } else {
                         state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), check.exemple3());
                    }
                    try {
                        var c = new Controller(new Repository(state));
                        c.allStep();
                    } catch (MyException e) {
                        IO.println();
                    }
                    break;
                }
                default:
                    IO.println("invalid input");

            }
        }

//        try {
//            var ex = new Examples();
//            PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(),
//                    new MyList<>(), ex.exemple1());
//            var c = new Controller(new Repository(state));
//            c.allStep();
//        } catch (MyException e) {
//            IO.println(e);
//        }
    }

}
