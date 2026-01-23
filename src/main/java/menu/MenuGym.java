package menu;

import java.util.ArrayList;
import java.util.Scanner;
import org.example.Member;
import org.example.Person;
import org.example.Trainer;

public class MenuGym implements Menu { // Имя класса с большой буквы

    private static ArrayList<Person> gymPeople = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        gymPeople.add(new Trainer(123,"Isaali","Run"));
    }

    @Override
    public void displayMenu() {
        boolean run = true;
        while (run) {
            System.out.println("\n--- GYM MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Trainer");
            System.out.println("2. Add Member");
            System.out.println("3. View All People");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    addTrainer();
                } else if (choice == 2) {
                    addMember();
                } else if (choice == 3) {
                    viewPeople();
                } else if (choice == 0) {
                    run = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void addTrainer() {
        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Specialization: ");
            String spec = scanner.nextLine();

            gymPeople.add(new Trainer(id, name, spec));
            System.out.println("Trainer added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void addMember() {
        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Membership Type: ");
            String type = scanner.nextLine();

            gymPeople.add(new Member(id, name, type));
            System.out.println("Member added!");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void viewPeople() {
        System.out.println("\n--- LIST OF ALL PEOPLE ---");
        for (Person p : gymPeople) {
            p.aboniment();
        }
    }
}