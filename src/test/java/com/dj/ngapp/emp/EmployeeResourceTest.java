package com.dj.ngapp.emp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.dj.ngapp.core.AbstractAppGuiceContainerTest;
import com.dj.ngapp.core.module.AppXmlMyBatisModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dj.ngapp.core.http.AppResponse;
import com.dj.ngapp.emp.model.Employee;
import com.google.inject.Module;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

/**
 * Tests 'Employee Resource' class
 *
 * @author Deepak Jacob
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class EmployeeResourceTest extends AbstractAppGuiceContainerTest {

    private static final String ID_TO_BE_TESTED = "4";

    private final Logger logger = LoggerFactory.getLogger(EmployeeResourceTest.class);

    @Test
    public void testSaveEmployee() {
        logger.debug("About to save employee having id...");
        GenericType<AppResponse<Employee>> genericType = new GenericType<AppResponse<Employee>>() {
        };
        WebResource wr = getWebResource();
        Employee employee = new Employee("Deepak", "Jacob", new Date(), new BigDecimal("345678.678"));
        WebResource.Builder wrb = wr.path("employee").path("save").type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        AppResponse<Employee> postedData = wrb.post(genericType, employee);
        assertTrue("The employee has been saved successfully", postedData.getSuccess());
        assertNotNull(postedData.getModel().getId());

    }

   @Test
    public void testGetEmployeeWithId() throws Exception {
        logger.debug("About to get employee having id...");
        GenericType<AppResponse<Employee>> genericType = new GenericType<AppResponse<Employee>>() {
        };
        WebResource wr = getWebResource();
        WebResource.Builder wrb = wr.path("employee").path("get").path(ID_TO_BE_TESTED).type(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON);
        AppResponse<Employee> emp = wrb.post(genericType);
        logger.debug("Resulting employee : {}", emp);
        String expectedName = "Deepak";
        final Employee model = emp.getModel();
//        assertTrue(expectedName.equals(model.getFirstName()));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        logger.debug("Trying to get all employees");
        GenericType<AppResponse<List<Employee>>> genericType = new GenericType<AppResponse<List<Employee>>>() {
        };
        WebResource wr = getWebResource();
        WebResource.Builder wrb = wr.path("employee").path("employees").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        AppResponse<List<Employee>> appResponse = wrb.get(genericType);
        //List<Employee> model = emps.getModel();
        int totalResults = appResponse.getTotalResults();
        assertTrue(totalResults > 0);

    }

    @Override
    public List<Module> createMybatisModule() {
        final AppXmlMyBatisModule module = new AppXmlMyBatisModule();
        final List<Module> moduleList = new ArrayList<Module>() {{
            add(module);
        }};
        return moduleList;
    }

    @Override
    public Reader getInitializationScripts() {
        return null;
    }
}

