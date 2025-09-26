package org.ckurva.rest.app.NoPanache;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/ssr/emp")
public class EmpResource {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.getAllEmployees();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") int id) throws SQLException {
        return employeeRepository.getEmployee(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee) throws SQLException {
        Employee result = employeeRepository.createEmployee(employee.getEmpId(), employee.getEmpName(), employee.getJoinDate());
        return result != null ? result : employeeRepository.getEmployee(employee.getEmpId());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee updateEmployee(Employee employee) throws SQLException {
        return employeeRepository.updateEmployee(employee.getEmpId(), employee.getEmpName());
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmployee(@PathParam("id") int id) throws SQLException {
        return employeeRepository.deleteEmployee(id);
    }
}
