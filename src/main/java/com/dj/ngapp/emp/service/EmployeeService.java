package com.dj.ngapp.emp.service;

import com.dj.ngapp.emp.model.Employee;

import java.util.List;

/**
 * EmployeeService - Transactional
 */
public interface EmployeeService {
    /**
     * Return the instance of the employee with the provided id.
     *
     * @param id the id of the employee to be returned.
     * @return the instance of the employee with the supplied id.
     */
    public Employee getEmployee(Long id);

    /**
     * Persist an employee into storage.
     *
     * @param employee the employee instance to be saved.
     * @return the count of inserted records.
     */
    public int createEmployee(Employee employee);

    /**
     * Get all the employees
     *
     * @return an instance of java.util.List implementation containing employee instances.
     */
    public List<Employee> getEmployees();

    /**
     * Delete the employee with the specified Id
     *
     * @param id the id of the employee to be deleted
     * @return the deleted employee instance
     */
    public int deleteEmployee(Long id);

    /**
     * Update the instance of employee
     *
     * @param employee the instance with updated attributes.
     * @return the updated instance.
     */
    public int updateEmployee(Employee employee);
}
