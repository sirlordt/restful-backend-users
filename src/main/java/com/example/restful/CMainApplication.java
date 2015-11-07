package com.example.restful;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class CMainApplication extends ResourceConfig {

	public CMainApplication() {

		register( JacksonFeature.class );
	
	}
	
}
