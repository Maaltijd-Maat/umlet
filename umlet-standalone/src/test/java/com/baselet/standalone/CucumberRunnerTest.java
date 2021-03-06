package com.baselet.standalone;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber.json"},
        features = "src/test/resources/cucumber",
        glue = "com/baselet/standalone",
        tags = "not @ignore")
public class CucumberRunnerTest {
}