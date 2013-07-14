package com.dj.ngapp.login.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.dj.ngapp.core.http.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dj.ngapp.core.model.User;
import com.dj.ngapp.core.servlet.HttpServletUtil;
import com.google.inject.Inject;

@Path("/login")
public class LoginResource {

    private static Logger logger = LoggerFactory.getLogger(LoginResource.class);

	
    /* Name under which the user object is saved under Http Session scope */
    private static final String APP_SESSION_KEY = "sessionKey";
    public static final String COOKIE_NAME = "ngapp";

    @Inject
    public LoginResource() {
    }

    /**
     * Handles user log in requests. Expects username and password.
     *
     * @param username The user's sign-in name
     * @param password The user's password.
     * @param request  The injected HTTPServletRequest object.
     * @return The wrapped user object in a way front-end expects.
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/login")
    public AppResponse<User> login(@FormParam("username") String username,
                                  @FormParam("password") String password,
                                  @Context HttpServletRequest request,
                                  @Context HttpServletResponse response) {
        User user = new User(username);
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.setAttribute(APP_SESSION_KEY, user);
        }
        HttpServletUtil.addCookieToResponse(COOKIE_NAME, username, request, response);
        AppResponse<User> result = new AppResponse.ResponseBuilder<User>(true, user).build();
        logger.info("Login status for user {} - {}", result.getModel().getName(), result.getSuccess());
        return result;
    }

    /**
     * Handles user log out requests.
     *
     * @param request The injected HTTPServletRequest object.
     * @return The wrapped user object in a way front-end expects.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/logout")
    public AppResponse<User> logout(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(APP_SESSION_KEY);
        }
        AppResponse<User> result = new AppResponse.ResponseBuilder<User>(true, null).build();
        return result;
    }

    /**
     * If a valid session exists for the use then return the user object, else return null.
     *
     * @param request the HttpServletRequest object.
     * @return if a valid session exists the user object is returned, else return null.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Path("/check")
    public AppResponse<User> checkSession(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(APP_SESSION_KEY);
        AppResponse<User> result = null;
        if (session != null) {
            if (null != user) {
                result = new AppResponse.ResponseBuilder<User>(true, user).build();
            } else {
                result = new AppResponse.ResponseBuilder<User>(true, null).build();
            }
        }
        return result;
    }

}
