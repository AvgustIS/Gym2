package menu;

import database.MemberDAO;
import database.TrainerDAO;
import org.example.Member;
import org.example.Trainer;

import java.util.List;
import java.util.Scanner;

public class GymMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private MemberDAO memberDAO = new MemberDAO();
    private TrainerDAO trainerDAO = new TrainerDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("           GYM MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Member");
        System.out.println("2. View All Members");
        System.out.println("3. Update Member");
        System.out.println("4. Delete Member");
        System.out.println("5. Search Member by Name");
        System.out.println("6. Search Members by Fee Range");
        System.out.println("----- Trainers -----");
        System.out.println("7. Add Trainer");
        System.out.println("8. View All Trainers");
        System.out.println("9. Update Trainer");
        System.out.println("10. Delete Trainer");
        System.out.println("11. Search Trainer by Specialization");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> addMember();
                    case 2 -> viewAllMembers();
                    case 3 -> updateMember();
                    case 4 -> deleteMember();
                    case 5 -> searchByName();
                    case 6 -> searchByFeeRange();
                    case 7 -> addTrainer();
                    case 8 -> viewAllTrainers();
                    case 9 -> updateTrainer();
                    case 10 -> deleteTrainer();
                    case 11 -> searchTrainerBySpec();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }


    private void addMember() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Membership Type: ");
            String type = scanner.nextLine();
            System.out.print("Monthly Fee: ");
            double fee = Double.parseDouble(scanner.nextLine());

            Member m = new Member(0, name, phone, age, type, fee);
            boolean ok = memberDAO.insertMember(m);
            System.out.println(ok ? "Member added!" : "Failed to add member.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    private void viewAllMembers() {
        List<Member> list = memberDAO.getAllMembers();
        if (list.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (Member m : list) {
            System.out.println(m);
        }
    }

    private void updateMember() {
        try {
            System.out.print("Enter member ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Member existing = memberDAO.getMemberById(id);
            if (existing == null) {
                System.out.println("No member with ID " + id);
                return;
            }
            System.out.println("Current: " + existing);
            System.out.print("New name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) name = existing.getName();
            System.out.print("New phone [" + existing.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) phone = existing.getPhone();
            System.out.print("New age [" + existing.getAge() + "]: ");
            String ageStr = scanner.nextLine();
            int age = ageStr.trim().isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);
            System.out.print("New membership type [" + existing.getMembershipType() + "]: ");
            String type = scanner.nextLine();
            if (type.trim().isEmpty()) type = existing.getMembershipType();
            System.out.print("New monthly fee [" + existing.getMonthlyFee() + "]: ");
            String feeStr = scanner.nextLine();
            double fee = feeStr.trim().isEmpty() ? existing.getMonthlyFee() : Double.parseDouble(feeStr);

            Member updated = new Member(id, name, phone, age, type, fee);
            boolean ok = memberDAO.updateMember(updated);
            System.out.println(ok ? "Member updated!" : "Update failed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }
    }

    private void deleteMember() {
        System.out.print("Enter member ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Member m = memberDAO.getMemberById(id);
            if (m == null) {
                System.out.println("No member with ID " + id);
                return;
            }
            System.out.println("To delete: " + m);
            System.out.print("Are you sure? (yes/no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("yes")) {
                boolean ok = memberDAO.deleteMember(id);
                System.out.println(ok ? "Deleted." : "Delete failed.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID");
        }
    }

    private void searchByName() {
        System.out.print("Enter name (partial allowed): ");
        String q = scanner.nextLine();
        List<Member> res = memberDAO.searchByName(q);
        if (res.isEmpty()) System.out.println("No results.");
        else res.forEach(System.out::println);
    }

    private void searchByFeeRange() {
        try {
            System.out.print("Min fee: ");
            double min = Double.parseDouble(scanner.nextLine());
            System.out.print("Max fee: ");
            double max = Double.parseDouble(scanner.nextLine());
            List<Member> res = memberDAO.searchByFeeRange(min, max);
            if (res.isEmpty()) System.out.println("No results.");
            else res.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Enter valid numbers");
        }
    }


    private void addTrainer() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Specialization: ");
            String spec = scanner.nextLine();
            System.out.print("Salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            Trainer t = new Trainer(0, name, phone, age, spec, salary);
            boolean ok = trainerDAO.insertTrainer(t);
            System.out.println(ok ? "Trainer added!" : "Failed to add trainer.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }

    private void viewAllTrainers() {
        List<Trainer> list = trainerDAO.getAllTrainers();
        if (list.isEmpty()) {
            System.out.println("No trainers found.");
            return;
        }
        for (Trainer t : list) {
            System.out.println(t);
        }
    }

    private void updateTrainer() {
        try {
            System.out.print("Enter trainer ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Trainer existing = trainerDAO.getTrainerById(id);
            if (existing == null) {
                System.out.println("No trainer with ID " + id);
                return;
            }
            System.out.println("Current: " + existing);
            System.out.print("New name [" + existing.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) name = existing.getName();
            System.out.print("New phone [" + existing.getPhone() + "]: ");
            String phone = scanner.nextLine();
            if (phone.trim().isEmpty()) phone = existing.getPhone();
            System.out.print("New age [" + existing.getAge() + "]: ");
            String ageStr = scanner.nextLine();
            int age = ageStr.trim().isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);
            System.out.print("New specialization [" + existing.getSpecialization() + "]: ");
            String spec = scanner.nextLine();
            if (spec.trim().isEmpty()) spec = existing.getSpecialization();
            System.out.print("New salary [" + existing.getSalary() + "]: ");
            String salStr = scanner.nextLine();
            double salary = salStr.trim().isEmpty() ? existing.getSalary() : Double.parseDouble(salStr);

            Trainer updated = new Trainer(id, name, phone, age, spec, salary);
            boolean ok = trainerDAO.updateTrainer(updated);
            System.out.println(ok ? "Trainer updated!" : "Update failed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }
    }

    private void deleteTrainer() {
        System.out.print("Enter trainer ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Trainer t = trainerDAO.getTrainerById(id);
            if (t == null) {
                System.out.println("No trainer with ID " + id);
                return;
            }
            System.out.println("To delete: " + t);
            System.out.print("Are you sure? (yes/no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("yes")) {
                boolean ok = trainerDAO.deleteTrainer(id);
                System.out.println(ok ? "Deleted." : "Delete failed.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID");
        }
    }

    private void searchTrainerBySpec() {
        System.out.print("Enter specialization (partial allowed): ");
        String q = scanner.nextLine();
        List<Trainer> res = trainerDAO.searchBySpecialization(q);
        if (res.isEmpty()) System.out.println("No results.");
        else res.forEach(System.out::println);
    }
}
