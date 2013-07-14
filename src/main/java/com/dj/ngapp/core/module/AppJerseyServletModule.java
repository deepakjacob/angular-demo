package com.dj.ngapp.core.module;

import com.dj.ngapp.core.servlet.AppSecurityFilter;
import com.google.common.collect.Maps;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Map;

/**
 * Responsible for setting up -
 * <ul>
 * <li>Jersey resources scanning locations</li>
 * <li>Initializing security filter for all URLs with /ajax/* pattern</li>
 * <li>Setting up dependency injection support Jersey Resources</li>
 * </ul>
 */
public class AppJerseyServletModule extends JerseyServletModule {

    /**
     * Jersey scanner will be looking in these packages for resources and providers
     */
    private static final String SCANNING_LOCATIONS = "com.dj.ngapp;" + "com.dj.ngapp.core.servlet;" + "org.codehaus.jackson.jaxrs";
    private static final String URL_PATTERN = "/ajax/*";

    @Override
    protected void configureServlets() {
        final Map<String, String> servletParameters = Maps.newHashMap();
        servletParameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, SCANNING_LOCATIONS);
        servletParameters.put("com.sun.jersey.config.feature.Trace", "true");
        servletParameters.put("com.sun.jersey.spi.container.ResourceFilters", "com.sun.jersey.api.container.filter.ResourceDebuggingFilterFactory");
        filter(URL_PATTERN).through(AppSecurityFilter.class);
        serve(URL_PATTERN).with(GuiceContainer.class, servletParameters);
    }
}
