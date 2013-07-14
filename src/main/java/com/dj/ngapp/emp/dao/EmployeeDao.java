package com.dj.ngapp.emp.dao;

import com.dj.ngapp.emp.model.Employee;

import java.util.List;

/**
 * Data Access Object for Employee
 *
 * @author Deepak Jacob
 */
public interface EmployeeDao {

    /**
     * Retrieve the employee instance from the persistent storage.
     *
     * @param id the of the employee to be retrieved.
     * @return the employee instance matching the id.
     */
    Employee getEmployee(Long id);

    /**
     * Create the employee instance in the persistence store.
     *
     * @param employee the employee object to be saved.
     * @return id of the employee just created.
     */
    int createEmployee(Employee employee);

    /**
     * Get all the employees
     *
     * @return a list of all employees.
     */
    List<Employee> getEmployees();

    /**
     * Delete the employee
     *
     * @param id
     * @return
     */
    int deleteEmployee(Long id);

    /**
     * Update the employee.
     *
     *
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);
}
