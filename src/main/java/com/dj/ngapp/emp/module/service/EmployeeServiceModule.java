package com.dj.ngapp.emp.module.service;

import com.dj.ngapp.emp.service.EmployeeService;
import com.dj.ngapp.emp.service.impl.EmployeeServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Employee Module responsible for managing dependency injection
 */
public class EmployeeServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EmployeeService.class).to(EmployeeServiceImpl.class).in(Scopes.SINGLETON);
    }
}
