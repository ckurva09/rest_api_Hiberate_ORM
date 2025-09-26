package org.ckurva.rest.app.NoPanache;

import java.time.LocalDate;

public class Employee {
    private int empId;
    private String empName;
    private LocalDate joinDate;

    public Employee(int empId, String empName, LocalDate joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.joinDate = joinDate;
    }

    public Employee() {
        // Default constructor required for Jackson
    }
    // Getters and setters
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
}