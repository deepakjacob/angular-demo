package com.dj.ngapp.emp.resource;

import com.dj.ngapp.core.http.AppResponse;
import com.dj.ngapp.emp.model.Employee;
import com.dj.ngapp.emp.service.EmployeeService;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * All requests that has a <strong>/employee</strong> just after the context will get serviced from this class.
 *
 * @author Deepak Jacob
 */
@Path("/employee")
public class EmployeeResource {

    private static Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

    private EmployeeService employeeService;

    @Inject
    public EmployeeResource(final EmployeeService employeeService) {
        logger.debug("Created object of " + this.getClass().getCanonicalName() + ", instance " + this);
        this.employeeService = employeeService;
    }

    @POST
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AppResponse<Employee> getEmployeeWithId(@PathParam("id") final Long id) {
        //validate(id);
        logger.debug("Getting the employee with the id {} from  the persistence storage", id);
        Employee emp = employeeService.getEmployee(id);
        AppResponse<Employee> response = new AppResponse.ResponseBuilder(true, emp).build();
        return response;
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AppResponse<Employee> saveEmployee(final Employee employee) {
        //validate(id);
        @SuppressWarnings("unused")
		int rowsInserted = employeeService.createEmployee(employee);
        AppResponse<Employee> response = new AppResponse.ResponseBuilder(true, employee).build();
        return response;
    }

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public AppResponse<List<Employee>> getAllEmployees() {
        logger.debug("trying to get all employees");
        AppResponse<List<Employee>> employees = new AppResponse.ResponseBuilder(true, employeeService.getEmployees()).build();
        return employees;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/g/load")
    public AppResponse<List<Employee>> load() {
        return new AppResponse.ResponseBuilder(true, employeeService.getEmployees()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/g/create")
    public AppResponse<Employee> create(Employee employee) {
        int rowsInserted = employeeService.createEmployee(employee);
        AppResponse<Employee> response = new AppResponse.ResponseBuilder(true, employee).build();
        return response;
    }


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/g/destroy/{id}")
    public AppResponse<Employee> delete(@PathParam("id") Long id) {
        int i = employeeService.deleteEmployee(id);
        return new AppResponse.ResponseBuilder(true, i).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/g/update/{id}")
    public AppResponse<Employee> update(@PathParam("id") Long id, Employee employee) {
        int i = employeeService.updateEmployee(employee);
        return new AppResponse.ResponseBuilder(true, i).build();
    }


}
