package com.automation.utils;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;


public class CustomCucumberListener implements ConcurrentEventListener {

    private static final Logger log = LogManager.getLogger(CustomCucumberListener.class);

    private final AtomicInteger passed  = new AtomicInteger(0);
    private final AtomicInteger failed  = new AtomicInteger(0);
    private final AtomicInteger skipped = new AtomicInteger(0);

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::onScenarioFinished);
        publisher.registerHandlerFor(TestRunFinished.class,  this::onRunFinished);
    }

    private void onScenarioFinished(TestCaseFinished event) {
        String name   = event.getTestCase().getName();
        Status status = event.getResult().getStatus();

        switch (status) {
            case PASSED  : { passed.incrementAndGet();  log.info("  ✅ PASSED  → {}", name); }
            case FAILED  : { failed.incrementAndGet();  log.error("  ❌ FAILED  → {}", name); }
            case SKIPPED : { skipped.incrementAndGet(); log.warn("  ⚠️  SKIPPED → {}", name); }
            default      : log.info("  [{}] → {}", status, name);
        }
    }

    private void onRunFinished(TestRunFinished event) {
        int total = passed.get() + failed.get() + skipped.get();
        log.info("══════════════════════════════════════════════════");
        log.info("  SUITE SUMMARY");
        log.info("  Total   : {}", total);
        log.info("  Passed  : {}", passed.get());
        log.info("  Failed  : {}", failed.get());
        log.info("  Skipped : {}", skipped.get());
        log.info("══════════════════════════════════════════════════");
    }
}
