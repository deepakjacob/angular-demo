package com.dj.ngapp.test.resource;

import com.dj.ngapp.core.http.AppResponse;
import com.dj.ngapp.test.model.Model;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jacob
 * Date: 7/8/11
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/test")
/**
 * load    : {url: 'ajax/test/load', method: 'GET'},
 * create  : 'ajax/test/create',
 * destroy : 'ajax/test/destroy',
 * update  : 'ajax/test/update'
 */
public class Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/load")
    public AppResponse<Model> load() {
        List<Model> l = new ArrayList<Model>(5);
        Model m = new Model(100, "Deepak", "Jacob", "deepak.jacob@test.com");
        m = null;
        l.add(m);
        return new AppResponse.ResponseBuilder(true, l).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public AppResponse<Model> create(Model m) {
        m = new Model(2500, "X", "X", "x@x.com");
        //l.add(m);
        return new AppResponse.ResponseBuilder(true, m).totalResults(0).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public AppResponse<Model> delete(Model m) {

        m = new Model(-999, "X", "X", "x@x.com");
        //l.add(m);
        AppResponse<Model> r = new AppResponse.ResponseBuilder(true, m).build();
        return r;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public AppResponse<Model> update(Model m) {

        m = new Model(5000, "X", "X", "x@x.com");
        //l.add(m);
        return new AppResponse.ResponseBuilder(true, m).build();
    }


}
