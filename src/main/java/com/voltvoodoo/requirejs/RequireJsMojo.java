package com.voltvoodoo.requirejs;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * 
 * @goal optimize
 * @phase process-resources
 * 
 */
public class RequireJsMojo extends AbstractMojo {

    /**
     * Javascript source directory. 
     *
     * @parameter expression="${project.build.sourceDirectory}/../javascript"
     */
    private File sourceDirectory;

    /**
     * The name of your application starting point. This is usually "main", which
     * would mean your starting point would be a file named "main.js" somewhere
     * inside the {@link #sourceDirectory}. If there are several files with
     * that name, the first one will be used.
     *
     * @parameter expression="${requirejs.appName}" default-value="main"
     */
    private File appName;
    
    /**
     * The output directory into which to copy the resources.
     *
     * @parameter expression="${project.build.outputDirectory}/main-built.js"
     */
    private File outputFile;

    /**
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
          
            File mainFile = getMainFile();
            
        } catch (RuntimeException exc) {
            throw exc;
        } catch (Exception exc) {
            throw new MojoExecutionException("wrap: " + exc.getMessage(), exc);
        }
    }

    private File getMainFile() throws MojoFailureException {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(sourceDirectory);
        scanner.setIncludes(new String[] {appName + ".js"});
        scanner.scan();
        
        for(String relativePath :scanner.getIncludedFiles() ) {
            return new File(sourceDirectory, relativePath);
        }
        
        throw new MojoFailureException("Unable to find application starting point. Given the appname '" + appName + "', looking for a file named '" + appName + ".js' in '" + sourceDirectory.getAbsolutePath() + "', but could not find it.");
    }
    
}
