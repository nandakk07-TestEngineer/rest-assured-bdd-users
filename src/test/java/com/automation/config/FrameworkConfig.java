package com.automation.config;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"system:env",
	"classpath:config/config.properties"
})

public interface FrameworkConfig extends Config{
	
	@Key("base.url")
	String baseUrl();
	
	@Key("env")
	String env();
	
	@Key("request.timeout")
	int requestTimeout();
	
	@Key("log.request")
	boolean logRequest();
	
	@Key("log.response")
	boolean logResponse();
	
}
