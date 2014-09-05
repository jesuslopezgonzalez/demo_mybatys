package com.intelygenz.psg01.services;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        super(Services.class, ServicesFieldInjectedResource.class, MultiPartFeature.class);
    }
}