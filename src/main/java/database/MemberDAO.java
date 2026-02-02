package database;

import database.DatabaseConnection;
import org.example.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public boolean insertMember(Member m) {
        String sql = "INSERT INTO member (name, phone, age, membership_type, monthly_fee) VALUES (?, ?, ?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getPhone());
            ps.setInt(3, m.getAge());
            ps.setString(4, m.getMembershipType());
            ps.setDouble(5, m.getMonthlyFee());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(conn);
        }
    }

    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member ORDER BY member_id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Member m = new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("age"),
                        rs.getString("membership_type"),
                        rs.getDouble("monthly_fee")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Select failed: " + e.getMessage());
        } finally {
            DatabaseConnection.close(conn);
        }
        return list;
    }

    public Member getMemberById(int id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                            rs.getInt("member_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("membership_type"),
                            rs.getDouble("monthly_fee")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Get by id failed: " + e.getMessage());
        } finally {
            DatabaseConnection.close(conn);
        }
        return null;
    }

    public boolean updateMember(Member m) {
        String sql = "UPDATE member SET name = ?, phone = ?, age = ?, membership_type = ?, monthly_fee = ? WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getPhone());
            ps.setInt(3, m.getAge());
            ps.setString(4, m.getMembershipType());
            ps.setDouble(5, m.getMonthlyFee());
            ps.setInt(6, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(conn);
        }
    }

    public boolean deleteMember(int id) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(conn);
        }
    }

    // Search by name (ILIKE, partial)
    public List<Member> searchByName(String namePart) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE name ILIKE ? ORDER BY name";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + namePart + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Member(
                            rs.getInt("member_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("membership_type"),
                            rs.getDouble("monthly_fee")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.close(conn);
        }
        return list;
    }

    // Search by fee range
    public List<Member> searchByFeeRange(double min, double max) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE monthly_fee BETWEEN ? AND ? ORDER BY monthly_fee DESC";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Member(
                            rs.getInt("member_id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getString("membership_type"),
                            rs.getDouble("monthly_fee")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.close(conn);
        }
        return list;
    }
}
