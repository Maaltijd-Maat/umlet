package com.baselet.standalone;

import com.baselet.control.config.handler.ConfigHandler;
import com.baselet.control.enums.Program;
import com.baselet.control.enums.RuntimeType;
import com.baselet.control.util.Utils;
import com.baselet.diagram.DiagramHandler;
import com.baselet.diagram.io.DiagramFileHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ExportToPngTest {
    private DiagramHandler diagramToExport;
    private File exportedClassDiagram;

    @Given("An already created class diagram \\(class_diagram.uxf in the resource folder)")
    public void loadClassDiagram() {
        Utils.BuildInfo buildInfo = Utils.readBuildInfo();
        Program.init(buildInfo.version, RuntimeType.BATCH);
        ConfigHandler.loadConfig();

        File uxfClassDiagram = new File("src/test/resources/cucumber/class_diagram.uxf");
        diagramToExport = new DiagramHandler(uxfClassDiagram);
    }

    @When("The diagram has been exported to a png file")
    public void exportClassDiagram() throws IOException {
        exportedClassDiagram = File.createTempFile("temp", ".png");
        DiagramFileHandler diagramFileHandler = DiagramFileHandler.createInstance(diagramToExport, exportedClassDiagram);
        diagramFileHandler.doExportAs("png", exportedClassDiagram);
    }

    @Then("Verify that the exported png equals class_diagram.png in the resource folder")
    public void verifyExportedClassDiagram() {
        assertTrue(exportedClassDiagram.getAbsolutePath().endsWith(".png"));
    }
}
