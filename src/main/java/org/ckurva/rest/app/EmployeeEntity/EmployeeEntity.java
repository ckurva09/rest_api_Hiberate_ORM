package org.ckurva.rest.app.EmployeeEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.List;

@Entity
public class EmployeeEntity extends PanacheEntityBase {
    @Id
    public Long empId;
    public String empName;
    public LocalDate joinDate;
}