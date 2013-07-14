package com.dj.ngapp.emp.dao.impl;

import com.dj.ngapp.emp.dao.EmployeeDao;
import com.dj.ngapp.emp.model.Employee;
import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * Employee Dao implementation.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private SqlSession sqlSession;

    @Inject
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Employee getEmployee(Long id) {
        return (Employee) this.sqlSession.selectOne("com.dj.ngapp.emp.Employee.getById", id);
    }

    @Override
    public int createEmployee(Employee employee) {
        return this.sqlSession.insert("com.dj.ngapp.emp.Employee.add", employee);
    }

    @Override
    public List<Employee> getEmployees(){
        return this.sqlSession.selectList("com.dj.ngapp.emp.Employee.getAll");
    }

    @Override
    public int deleteEmployee(Long id) {
        return this.sqlSession.delete("com.dj.ngapp.emp.Employee.delete", id);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return this.sqlSession.update("com.dj.ngapp.emp.Employee.update", employee);
    }


}
