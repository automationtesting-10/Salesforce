/*
 * @(#) RunCukesTest.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av. Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.foundation.salesforce.runner;

import com.foundation.salesforce.core.report.GeneratorReport;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterTest;


/**
 * RunCukesTest class.
 *
 * @author Cristian Lujan
 * @version 1.0
 */
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-pretty",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"},
        glue = {"com/foundation/salesforce/steps"},
        features = {"src/test/resources/features/TaskAcceptance.feature"},
        monochrome = true)
public class RunCukesTest extends AbstractTestNGCucumberTests {

    /**
     * The method afterExecution executes the project and generate it report.
     */
    @AfterTest
    public void afterExecution() {
        GeneratorReport.getInstance().generateReport();
    }
}
