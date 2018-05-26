package com.siva.poc.hazelcast.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/test")
@Api(value = "JerseyRestTestResources resource", produces = "application/json")
public class JerseyRestTestResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyRestTestResources.class);

    @GET
    @Path("v1/hello/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = Hello.class)
    public Response getHelloVersionInUrl(@ApiParam @PathParam("name") String name) {
        LOGGER.info("getHelloVersionInUrl() v1");
        return this.getHello(name, "Version 1 - passed in URL");
    }

    private Response getHello(String name, String partialMsg) {
        if ("404".equals(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Hello result = new Hello();
        result.setMsg(String.format("Hello %s. %s", name, partialMsg));
        return Response.status(Response.Status.OK).entity(result).build();
    }

    public static class Hello {
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
