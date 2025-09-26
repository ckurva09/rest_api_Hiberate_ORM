package org.ckurva.rest.app.EmployeeEntity;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ckurva.rest.app.EmployeeEntity.EmployeeEntity;

import java.util.List;

@Path("/entity/ssr/emp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeEntityResource {

    @GET
    public List<EmployeeEntity> getAll() {
        return EmployeeEntity.listAll();
    }

    @POST
    @Transactional
    public EmployeeEntity create(EmployeeEntity emp) {
        emp.persist();
        return emp;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public EmployeeEntity update( @PathParam("id") Long id, EmployeeEntity emp) {
        EmployeeEntity entity = EmployeeEntity.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the EmployeeEntity parameter to the existing entity
        entity.empName = emp.empName;
        entity.joinDate = emp.joinDate;
        return entity;
    }

    @GET
    @Path("/{id}")
    public EmployeeEntity getById(@PathParam("id") Long id) {
        return EmployeeEntity.findById(id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        EmployeeEntity.deleteById(id);
    }
}