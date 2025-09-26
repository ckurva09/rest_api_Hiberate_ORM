package org.ckurva.rest.app.EmployeeRepoApproach;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/repository/ssr/emp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRepoApprachResource {

    @Inject
    EmployeeRepoApproachRepository repository;

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
        return emp;
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
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}
