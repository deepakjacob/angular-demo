package com.dj.ngapp.core.servlet;

import java.io.IOException;
import java.io.Reader;

import javax.sql.DataSource;

import com.dj.ngapp.core.module.AppJerseyServletModule;
import com.dj.ngapp.core.module.AppXmlMyBatisModule;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dj.ngapp.emp.module.dao.EmployeeDaoModule;
import com.dj.ngapp.emp.module.service.EmployeeServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Servlet context listener for the Web Application. Responsible for bringing up
 * the Guice dependency inject mechanism and wiring of application specific
 * components using Guice.
 *
 * @author Deepak Jacob
 */
public class AppServletContextListener extends GuiceServletContextListener {
    
    private static Logger logger = LoggerFactory.getLogger(AppServletContextListener.class);

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(
                new AppJerseyServletModule(),
                new AppXmlMyBatisModule(),
                
                //Application Modules
                new EmployeeServiceModule(),
                new EmployeeDaoModule()
        );
        
        executeDdls(injector);
        
        return injector;

    }

    private void executeDdls(Injector injector) {
        try {
           
            Environment environment = injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
            DataSource dataSource = environment.getDataSource();
            ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.setAutoCommit(true);
            scriptRunner.setStopOnError(true);
            Reader scripts = getInitializationScripts();
            if (scripts != null) {
                scriptRunner.runScript(scripts);
            }
            scriptRunner.closeConnection();
        } catch (Exception e) {
            logger.error("Error executing ddl resource - Object may already exist {}", e.getMessage());
        }
        
    }

    private Reader getInitializationScripts() {
        String resource = "com/dj/ngapp/ddl/ddl.sql";
        try {
            return Resources.getResourceAsReader(resource );
        } catch (IOException e) {
            logger.error("Error getting ddl resource {}", resource);
            throw new RuntimeException(e);
        }
    }

}
