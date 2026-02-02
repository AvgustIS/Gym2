package database;
import org.example.Member;
import org.example.Person;
import org.example.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StaffDAO{
    public boolean insertMember(Member mem) {
        String sql =
                "INSERT INTO member (name, id,membershipType) " +
                "VALUES (?, ? ,?)";

        Connection connection = DataBaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mem.getName());
            statement.setInt(2, mem.getId());
            statement.setString(3, mem.getMembershipType());


            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Member inserted: " + mem.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Member failed!");
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean insertTrainer(Trainer trainer) {
        String sql =
                "INSERT INTO trainer (name, id,specialization,staff_type,salary) " +
                        "VALUES (?, ? ,?,'Trainer',0.0)";

        Connection connection = DataBaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trainer.getName());
            statement.setInt(2, trainer.getId());
            statement.setString(3, trainer.getSpecialization());
            statement.setDouble(4, trainer.getSallary());


            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Member inserted: " + trainer.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Trainer failed!");
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Person> getAllStaff() {
        List<Person> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff ORDER BY staff_id";

        Connection connection = DataBaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person staff = extractStaffFromResultSet(resultSet);
                if (staff != null) {
                    staffList.add(staff);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + staffList.size() + " Person from database");

        } catch (SQLException e) {
            System.out.println("❌ Select all staff failed!");
            e.printStackTrace();
        } finally {
            DataBaseConnection.closeConnection(connection);
        }

        return staffList;
    }

    private Person extractStaffFromResultSet(ResultSet resultSet) throws SQLException {
        int staffId = resultSet.getInt("staff_id");
        String name = resultSet.getString("name");
        double salary = resultSet.getDouble("salary");
        String staffType = resultSet.getString("staff_type");

        Person staff = null;

        if ("Member".equals(staffType)) {
            String membership = resultSet.getString("membership");
            staff = new Member(staffId, name, membership) {
            };

        } else if ("Person".equals(staffType)) {
            int specialization = resultSet.getString("specialization");
            staff = new Trainer(staffId, name, specialization, salary);
        }

        return staff;
    }

    public void displayAllStaff() {
        List<Person> staffList = getAllStaff();

        System.out.println("\n========================================");
        System.out.println("   ALL STAFF FROM DATABASE");
        System.out.println("========================================");

        if (staffList.isEmpty()) {
            System.out.println("No staff members in database.");
        } else {
            for (int i = 0; i < staffList.size(); i++) {
                Person s = staffList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + s.getRole() + "] ");
                System.out.println(s.toString());
            }
        }

        System.out.println("========================================\n");
    }

}