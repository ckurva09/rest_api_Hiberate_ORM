package org.ckurva.rest.app.EmployeeRepoApproach;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/repository/ssr/emp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRepoApprachResource {

    @Inject
    EmployeeRepoApproachRepository repository;

    @Inject
    @Channel("employees-events")
    Emitter<String> emitter;

    @GET
    public List<EmployeeRepoApproach> getAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public EmployeeRepoApproach getById(@PathParam("id") Long id) {
        EmployeeRepoApproach emp = repository.findById(id);
        if (emp == null) {
            throw new NotFoundException();
        }
        publishEmployeeEvent(emp);
        return emp;
    }

    private void publishEmployeeEvent(EmployeeRepoApproach emp) {
        String payload = String.format("{\"empId\":%d,\"empName\":\"%s\",\"joinDate\":\"%s\",\"deptId\":%d}",
                emp.empId, emp.empName, emp.joinDate, emp.deptId);

        emitter.send(payload);
    }

    @GET
    @Path("/dept/{deptId}")
    public List<EmployeeRepoApproach> getEmployeeByDeptId(@PathParam("deptId") Long deptId) {
        List<EmployeeRepoApproach> emps = repository.findByDeptId(deptId);
        if (emps == null || emps.isEmpty()) {
            throw new NotFoundException();
        }
        return emps;
    }

    @POST
    @Transactional
    public EmployeeRepoApproach create(EmployeeRepoApproach emp) {
        repository.persist(emp);
        return emp;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public EmployeeRepoApproach update(@PathParam("id") Long id, EmployeeRepoApproach emp) {
        EmployeeRepoApproach entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.empName = emp.empName;
        entity.joinDate = emp.joinDate;
        entity.deptId = emp.deptId;
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}
