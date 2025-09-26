package org.ckurva.rest.app.EmployeeRepoApproach;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class EmployeeRepoApproach {
    @Id
    public Long empId;
    public String empName;
    public LocalDate joinDate;
}