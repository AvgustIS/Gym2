package menu;

import database.StaffDAO;
import org.example.Member;
import org.example.Trainer;

import java.util.Scanner;

public class MenuGym implements Menu {

    private Scanner scanner;
    private StaffDAO staffDAO;

    public MenuGym() {
        this.scanner = new Scanner(System.in);
        this.staffDAO = new StaffDAO();

        System.out.println("\n Gym 2");

        int a = 0;
        while(a<4) {
            System.out.println("Loading.");
            System.out.println("loading..");
            System.out.println("loading...");
            System.out.println("loading....");
            a++;
        }
    }
    @Override
    public void displayMenu(){
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ STAFF MANAGEMENT ─────────────────────┐");
        System.out.println("│ 1. Add Member                          │");
        System.out.println("│ 2. Add Trainer                         │");
        System.out.println("│ 3. View All Staff                      │");
        System.out.println("│ 4. View Member Only                    │");
        System.out.println("│ 5. View Trainer Only                   │");
        System.out.println("│ 6. Update Staff                        │");
        System.out.println("│ 7. Delete Staff                        │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 8. Search by Name                      │");
        System.out.println("│ 9. Search by  Specialization           │");
        System.out.println("│10. High-Paid Staff (Salary >= X)       │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│11. Polymorphism Demo                   │");
        System.out.println("│ 0. Exit                                │");
        System.out.println("└────────────────────────────────────────┘");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int cho = scanner.nextInt();
                scanner.nextLine();

                switch (cho) {
                    case 1:
                        addMember();
                        break;
                    case 2:
                        addTrainer();
                        break;
                    case 3:
                        viewAllStaff();
                        break;
                    case 4:
                        viewOnlyMember();
                        break;
                    case 5:
                        viewOnlyTrainer();
                        break;
                    case 6:
                        updateStaff();
                        break;
                    case 7:
                        deleteStaff();
                        break;
                    case 8:
                        searchByName();
                        break;
                    case 9:
                        searchBySpecialization();
                        break;
                    case 10:
                        searchHighPaidStaff();
                        break;
                    case 11:
                        demonstratePolymorphism();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system!      ║");
                        System.out.println("║  Goodbye! 👋                          ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please select 0-11.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }
        scanner.close();
    }

    private void  addMember() {
        try {
            System.out.println("\n┌─ ADD Member ─────────────────────────────┐");

            System.out.print("│ Enter Member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter membershipType: ");
            String membershipType = scanner.nextLine();

            System.out.println("└────────────────────────────────────────┘");

            Member member = new Member(id, name, membershipType);
            staffDAO.insertMember(member);

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void addTrainer() {
        try {
            System.out.println("\n┌─ ADD CHEF ─────────────────────────────┐");

            System.out.print("│ Enter Trainer ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Salary (KZT): ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("│ Enter Specialization: ");
            String specialization = scanner.nextLine();

            System.out.println("└────────────────────────────────────────┘");

            Trainer trainer = new Trainer(id, name, specialization, salary);
            staffDAO.insertTrainer(trainer);

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void viewAllStaff() {
        staffDAO.displayAllStaff();
    }

    private void viewChefs() {
        List<Member> mem = staffDAO.getAllMember();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         Member ONLY                    ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (Member.isEmpty()) {
            System.out.println("📭 No member in database.");
        } else {
            for (int i = 0; i < mem.size(); i++) {
                Member member = Member.get(i);
                System.out.println((i + 1) + ". " + member.toString());
                System.out.println("   membership: " + member.getMembershipType());
                if (member.isMember()) {
                    System.out.println("   ⭐ MASTER CHEF (10+ years)");
                }
                System.out.println();
            }
            System.out.println("Total Chefs: " + mem.size());
        }
    }

    private void viewWaiters() {
        List<Trainer> train = staffDAO.getAllTrainer();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        TRAINER ONLY                   ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (train.isEmpty()) {
            System.out.println("📭 No waiters in database.");
        } else {
            for (int i = 0; i < train.size(); i++) {
                Trainer trainer = Trainer.get(i);
                System.out.println((i + 1) + ". " + trainer.toString());
                System.out.println("   Specialization: " + trainer.getSpecialization());
                if (trainer.isTopTrainer()) {
                    System.out.println("   ⭐ TOP WAITER (100+ tables & 3+ years)");
                }
                System.out.println();
            }
            System.out.println("Total Waiters: " + train.size());
        }
    }

    // ========================================
    // UPDATE OPERATION (Week 8)
    // ========================================

    private void updateStaff() {
        System.out.println("\n┌─ UPDATE STAFF ─────────────────────────┐");
        System.out.print("│ Enter Staff ID to update: ");

        try {
            int staffId = scanner.nextInt();
            scanner.nextLine();

            // First, get existing staff from database
            Staff existingStaff = staffDAO.getStaffById(staffId);

            if (existingStaff == null) {
                System.out.println("❌ No staff found with ID: " + staffId);
                return;
            }

            // Display current info
            System.out.println("│ Current Info:");
            System.out.println("│ " + existingStaff.toString());
            System.out.println("└────────────────────────────────────────┘");

            // Get new values
            System.out.println("\n┌─ ENTER NEW VALUES ─────────────────────┐");
            System.out.println("│ (Press Enter to keep current value)   │");

            System.out.print("│ New Name [" + existingStaff.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingStaff.getName();
            }

            System.out.print("│ New Salary [" + existingStaff.getSalary() + "]: ");
            String salaryInput = scanner.nextLine();
            double newSalary = salaryInput.trim().isEmpty() ?
                    existingStaff.getSalary() : Double.parseDouble(salaryInput);

            System.out.print("│ New Experience [" + existingStaff.getExperienceYears() + "]: ");
            String expInput = scanner.nextLine();
            int newExperience = expInput.trim().isEmpty() ?
                    existingStaff.getExperienceYears() : Integer.parseInt(expInput);

            // Update based on type
            if (existingStaff instanceof Chef) {
                Chef chef = (Chef) existingStaff;
                System.out.print("│ New Specialization [" + chef.getSpecialization() + "]: ");
                String newSpec = scanner.nextLine();
                if (newSpec.trim().isEmpty()) {
                    newSpec = chef.getSpecialization();
                }

                Chef updatedChef = new Chef(staffId, newName, newSalary, newExperience, newSpec);
                staffDAO.updateChef(updatedChef);

            } else if (existingStaff instanceof Waiter) {
                Waiter waiter = (Waiter) existingStaff;
                System.out.print("│ New Tables Served [" + waiter.getTablesServed() + "]: ");
                String tablesInput = scanner.nextLine();
                int newTables = tablesInput.trim().isEmpty() ?
                        waiter.getTablesServed() : Integer.parseInt(tablesInput);

                Waiter updatedWaiter = new Waiter(staffId, newName, newSalary, newExperience, newTables);
                staffDAO.updateWaiter(updatedWaiter);
            }

            System.out.println("└────────────────────────────────────────┘");

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Invalid number format!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    // ========================================
    // DELETE OPERATION (Week 8)
    // ========================================

    private void deleteStaff() {
        System.out.println("\n┌─ DELETE STAFF ─────────────────────────┐");
        System.out.print("│ Enter Staff ID to delete: ");

        try {
            int staffId = scanner.nextInt();
            scanner.nextLine();

            // First, show who will be deleted
            Staff staff = staffDAO.getStaffById(staffId);

            if (staff == null) {
                System.out.println("❌ No staff found with ID: " + staffId);
                return;
            }

            System.out.println("│ Staff to delete:");
            System.out.println("│ " + staff.toString());
            System.out.println("└────────────────────────────────────────┘");

            System.out.print("⚠️  Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                staffDAO.deleteStaff(staffId);
            } else {
                System.out.println("❌ Deletion cancelled.");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input!");
            scanner.nextLine();
        }
    }

    // ========================================
    // SEARCH OPERATIONS (Week 8)
    // ========================================

    private void searchByName() {
        System.out.println("\n┌─ SEARCH BY NAME ───────────────────────┐");
        System.out.print("│ Enter name to search: ");
        String name = scanner.nextLine();
        System.out.println("└────────────────────────────────────────┘");

        List<Staff> results = staffDAO.searchByName(name);

        displaySearchResults(results, "Search: '" + name + "'");
    }

    private void searchBySalaryRange() {
        try {
            System.out.println("\n┌─ SEARCH BY SALARY RANGE ───────────────┐");
            System.out.print("│ Enter minimum salary: ");
            double minSalary = scanner.nextDouble();

            System.out.print("│ Enter maximum salary: ");
            double maxSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Staff> results = staffDAO.searchBySalaryRange(minSalary, maxSalary);

            displaySearchResults(results, "Salary: " + minSalary + " - " + maxSalary + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void searchHighPaidStaff() {
        try {
            System.out.println("\n┌─ HIGH-PAID STAFF ──────────────────────┐");
            System.out.print("│ Enter minimum salary: ");
            double minSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Staff> results = staffDAO.searchByMinSalary(minSalary);

            displaySearchResults(results, "Salary >= " + minSalary + " KZT");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void displaySearchResults(List<Staff> results, String criteria) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SEARCH RESULTS                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Criteria: " + criteria);
        System.out.println("─────────────────────────────────────────");

        if (results.isEmpty()) {
            System.out.println("📭 No staff found matching criteria.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                Staff s = results.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + s.getRole() + "] ");
                System.out.println(s.toString());
            }
            System.out.println("─────────────────────────────────────────");
            System.out.println("Total Results: " + results.size());
        }
    }

    // ========================================
    // POLYMORPHISM DEMO
    // ========================================

    private void demonstratePolymorphism() {
        staffDAO.demonstratePolymorphism();
    }

    // ========================================
    // HELPER METHOD
    // ========================================

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}