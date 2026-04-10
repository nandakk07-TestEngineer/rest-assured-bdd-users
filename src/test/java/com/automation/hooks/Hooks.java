package com.automation.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automation.utils.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private static final Logger log = LogManager.getLogger(Hooks.class);
	private ScenarioContext ctx;
	
	public Hooks(ScenarioContext ctx) {
		this.ctx = ctx;
	}
	 @Before
	    public void beforeScenario(Scenario scenario) {
	        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	        log.info("▶ SCENARIO STARTED: {}", scenario.getName());
	        log.info("  Tags: {}", scenario.getSourceTagNames());
	        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	    }

	    @After
	    public void afterScenario(Scenario scenario) {
	        if (scenario.isFailed()) {
	            log.error("❌ SCENARIO FAILED: {}", scenario.getName());

	            // Attach response body to report if available
	            if (ctx.getResponse() != null) {
	                String body = ctx.getResponse().asPrettyString();
	                log.error("Last Response Body:\n{}", body);

	                scenario.attach(
	                    body.getBytes(),
	                    "application/json",
	                    "Failed Response Body"
	                );
	            }
	        } else {
	            log.info("✅ SCENARIO PASSED: {}", scenario.getName());
	        }
	        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
	    }
	}
