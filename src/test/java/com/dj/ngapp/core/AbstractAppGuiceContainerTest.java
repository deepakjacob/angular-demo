package com.dj.ngapp.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.List;

/**
 * Abstract base class
 */
abstract public class AbstractAppGuiceContainerTest extends AbstractAppContainerTest {

    final private Injector injector;


    protected AbstractAppGuiceContainerTest() {
        try {
            this.injector = Guice.createInjector(createMybatisModule());
            Environment environment = this.injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
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
            throw new RuntimeException(e);
        }
    }

    abstract public List<Module> createMybatisModule();

    abstract public Reader getInitializationScripts();


}
