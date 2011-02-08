package com.voltvoodoo;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ResourcesTest  {

    private static final String OUTPUT_FILE = "main-built.js";
    private static String[] FILE_IDENTIFIERS = new String[] {"one.js","two.js","three.js"};
    
    private File output = new File("target/classes");

    @Test
    public void outputFileShouldExist() throws Exception {
        File outputFile = new File(output, OUTPUT_FILE);
        assertExists(outputFile);
    }
    
    @Test
    public void outputFileShouldContainAllExpectedFiles() throws Exception {
        File outputFile = new File(output, OUTPUT_FILE);
        for(String key : FILE_IDENTIFIERS) {
            assertContains(outputFile, key);
        }
    }
    
    @Test
    public void outputFileShouldNotContainComments() throws Exception {
        File file = new File(output, OUTPUT_FILE);
        assertNotContains(file, "//");
        assertNotContains(file, "/*");
    }

    private static void assertExists(File file) throws Exception {
        assertThat( file.exists(), is( true ) );
    }

    private static void assertNotExists(File file) throws Exception {
        assertThat( file.exists(), is( false) );
    }
    
    private static void assertContains(File file, String substr) throws IOException {
        assertThat(FileUtils.readFileToString( file ), (containsString( "//" )));
    }
    
    private static void assertNotContains(File file, String substr) throws IOException {
        assertThat(FileUtils.readFileToString( file ), not(containsString( "//" )));
    }
}
