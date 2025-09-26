package org.ckurva.rest.app.NoPanache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeRepository {

    @Inject
    DataSource dataSource;

//    private final String url = "jdbc:postgresql://localhost:5432/postgres";
//    private final String user = "postgres";
//    private final String password = "1234";

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employeeList =  new ArrayList<>();
        String sql = "SELECT empId, empName, joinDate FROM grobdb";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("empId");
                    String name = rs.getString("empName");
                    Date joinDate = rs.getDate("joinDate");
                    employeeList.add(new Employee(id, name, joinDate.toLocalDate()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
            return employeeList;
        }
    }

    public Employee getEmployee(int empId) throws SQLException {
        String sql = "SELECT empId, empName, joinDate FROM grobdb WHERE empId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getDate("joinDate").toLocalDate());
                } else {
                    return null;
                }
            }
        }
    }

    public Employee createEmployee(int empId, String empName, LocalDate joinDate) throws SQLException {
        String sql = "INSERT INTO grobdb(empId, empName, joinDate) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.setString(2, empName);
            ps.setDate(3, java.sql.Date.valueOf(joinDate));
            ps.executeUpdate();
            return new Employee(empId, empName, joinDate);
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.out.println("Employee with ID " + empId + " already exists.");
                return null;
            } else {
                throw e;
            }
        }
    }

    public Employee updateEmployee(int empId, String newEmpName) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE grobdb SET empName = ? where empId = " + empId)) {
            ps.setString(1, newEmpName);
            int rows = ps.executeUpdate();
            return rows > 0 ? getEmployee(empId) : null;
        }
    }

    public String deleteEmployee(int empId) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate("DELETE FROM grobdb where empId = " + empId);
            return rows > 0 ? "Delete Emp: " + empId + " is completed" : null;
        }
    }
}