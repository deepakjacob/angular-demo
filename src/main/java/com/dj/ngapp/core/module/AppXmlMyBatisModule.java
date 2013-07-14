package com.dj.ngapp.core.module;

import java.util.Properties;

import org.mybatis.guice.XMLMyBatisModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The module used by MyBatis to bootstrap DAO layer.
 */
public class AppXmlMyBatisModule extends XMLMyBatisModule {

    private static Logger logger = LoggerFactory.getLogger(AppXmlMyBatisModule.class);

    public static final String ENVIRONMENT_ID = "ngapp";

    @Override
    protected void initialize() {
        setEnvironmentId(ENVIRONMENT_ID);
        addProperties(getDbSettingAsProperties());
    }

    public Properties getDbSettingAsProperties() {
        Properties props = new Properties();
        props.setProperty("url", "jdbc:h2:mem");
        return props;
    }
}
