package database;

import database.DatabaseConnection;
import org.example.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public boolean insertTrainer(Trainer t) {
        String sql = "INSERT INTO trainer (name, phone, age, specialization, salary) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getPhone());
            ps.setInt(3, t.getAge());
            ps.setString(4, t.getSpecialization());
            ps.setDouble(5, t.getSalary());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert trainer failed: " + e.getMessage());
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> list = new ArrayList<>();
        String sql = "SELECT * FROM trainer ORDER BY trainer_id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Trainer t = new Trainer(
                        rs.getInt("trainer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("age"),
                        rs.getString("specialization"),
                        rs.getDouble("salary")
                );
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Select trainers failed: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
        return list;
    }

    public Trainer getTrainerById(int id) {
        String sql = "SELECT * FROM trainer WHERE trainer_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Trainer(
                            rs.getInt("trainer_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("specialization"),
                            rs.getDouble("salary")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Get trainer by id failed: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
        return null;
    }

    public boolean updateTrainer(Trainer t) {
        String sql = "UPDATE trainer SET name = ?, phone = ?, age = ?, specialization = ?, salary = ? WHERE trainer_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getPhone());
            ps.setInt(3, t.getAge());
            ps.setString(4, t.getSpecialization());
            ps.setDouble(5, t.getSalary());
            ps.setInt(6, t.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update trainer failed: " + e.getMessage());
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    public boolean deleteTrainer(int id) {
        String sql = "DELETE FROM trainer WHERE trainer_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete trainer failed: " + e.getMessage());
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }


    public List<Trainer> searchBySpecialization(String specPart) {
        List<Trainer> list = new ArrayList<>();
        String sql = "SELECT * FROM trainer WHERE specialization ILIKE ? ORDER BY name";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + specPart + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Trainer(
                            rs.getInt("trainer_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("specialization"),
                            rs.getDouble("salary")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Search trainer failed: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
        return list;
    }
}
