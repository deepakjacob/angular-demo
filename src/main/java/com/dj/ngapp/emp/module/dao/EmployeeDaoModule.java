package com.dj.ngapp.emp.module.dao;

import com.dj.ngapp.emp.dao.EmployeeDao;
import com.dj.ngapp.emp.dao.impl.EmployeeDaoImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * All related DAO implementation related dependency injection.
 *
 */
public class EmployeeDaoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class).in(Scopes.SINGLETON);
    }
}
