package com.dj.ngapp.emp.service.impl;

import com.dj.ngapp.core.UserUtil;
import com.dj.ngapp.emp.dao.EmployeeDao;
import com.dj.ngapp.emp.model.Employee;
import com.dj.ngapp.emp.service.EmployeeService;
import com.google.inject.Inject;

import org.mybatis.guice.transactional.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Employee Service Implementation
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    @Inject
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    @Transactional
    public Employee getEmployee(Long id) {
        return this.employeeDao.getEmployee(id);
    }

    @Override
    @Transactional
    public int createEmployee(Employee employee) {
        com.dj.ngapp.core.model.User user = UserUtil.getUser();
        logger.debug("{} accessing DAO to save the employee", user.getUsername());
        
        
        return this.employeeDao.createEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        com.dj.ngapp.core.model.User user = UserUtil.getUser();
        logger.debug(" {} trying to get all employees", user.getUsername());
        List<Employee> empList = employeeDao.getEmployees();

        return empList;
    }

    @Override
    public int deleteEmployee(Long id) {
        logger.debug("accessing DAO to delete the employee");
        return this.employeeDao.deleteEmployee(id);
    }

    @Override
    public int updateEmployee(Employee employee) {
        logger.debug("accessing DAO to update the employee");
        return this.employeeDao.updateEmployee(employee);
    }


}
