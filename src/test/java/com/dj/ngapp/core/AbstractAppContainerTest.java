package com.dj.ngapp.core;

import com.dj.ngapp.core.servlet.AppServletContextListener;
import com.google.common.collect.Lists;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.config.ClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import java.util.EventListener;
import java.util.List;

/**
 * Applite specific details such as context, port, scheme, other http path
 * details are specified here.
 *
 * @author Deepak Jacob
 */
public abstract class AbstractAppContainerTest extends com.dj.ngapp.core.JettyTestContainer {

    private static final String APP_AJAX_PATTERN = "/ajax";
    private static final String CONTEXT = "/";

    @Override
    protected String getContextPath() {
        return CONTEXT;
    }

    @Override
    protected List<HttpServlet> getServlets() {
        List<HttpServlet> servletList = Lists.newArrayList();
        return servletList;
    }

    @Override
    protected String getPattern() {
        return APP_AJAX_PATTERN;
    }

    @Override
    protected List<Filter> getFilters() {
        List<Filter> filterList = Lists.newArrayList();
        filterList.add(new GuiceFilter());
        return filterList;
    }

    @Override
    protected List<EventListener> getEventListeners() {
        List<EventListener> listenerList = Lists.newArrayList();
        listenerList.add(new AppServletContextListener());
        return listenerList;
    }

    @Override
    protected void registerProviderClasses(final ClientConfig cc) {
        cc.getClasses().add(JacksonJsonProvider.class);
    }
}
