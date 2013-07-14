package com.dj.ngapp.emp.module.resource;

import com.dj.ngapp.emp.resource.EmployeeResource;
import com.google.inject.AbstractModule;

/**
 * Module for employee resources
 */
public class EmployeeResourceModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(EmployeeResource.class);
    }
}
