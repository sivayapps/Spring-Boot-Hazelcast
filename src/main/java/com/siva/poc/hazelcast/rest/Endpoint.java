package com.siva.poc.hazelcast.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/hello")
@Api(value = "Hello resource", produces = "application/json")
public class Endpoint {

    private final Service service;

    public Endpoint(Service service) {
        this.service = service;
    }

    @GET
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = String.class)
    public String message() {
        return "Hello " + this.service.message();
    }

}
