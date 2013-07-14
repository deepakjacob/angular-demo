package com.dj.ngapp.core.login;

import com.dj.ngapp.core.AbstractAppContainerTest;
import com.dj.ngapp.core.http.AppResponse;
import com.dj.ngapp.core.model.User;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertTrue;

/**
 * Tests 'Employee Resource' class
 *
 * @author Deepak Jacob
 */
public class LoginResourceTest extends AbstractAppContainerTest {


    private final Logger logger = LoggerFactory.getLogger(LoginResourceTest.class);

    @Test
    public void testLogin() {
        logger.debug("About to try login with test/test as username/password");
        GenericType<AppResponse<User>> genericType = new GenericType<AppResponse<User>>() {
        };
        WebResource wr = getWebResource();
        WebResource.Builder wrb = wr.path("login").path("login").type(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON);
        wrb.post("username=test&password=test");
    }
}

