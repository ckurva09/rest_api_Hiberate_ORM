package org.ckurva.rest.app.EmployeeRepoApproach;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepoApproachRepository implements PanacheRepository<EmployeeRepoApproach> {
    // To Add custom queries here
}
