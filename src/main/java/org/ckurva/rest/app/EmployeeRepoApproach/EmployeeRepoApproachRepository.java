package org.ckurva.rest.app.EmployeeRepoApproach;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmployeeRepoApproachRepository implements PanacheRepository<EmployeeRepoApproach> {
    // To Add custom queries here
    public List<EmployeeRepoApproach> findByDeptId(Long deptId) {
        return find("SELECT e FROM EmployeeRepoApproach e WHERE e.deptId = ?1", deptId).list();
    }
}
