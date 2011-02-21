# Requirejs-maven

Requirejs-maven is a maven plugin that uses the require.js optimization tool
to aggregate and minify your require js projects.

### Usage

Requirejs-maven is not yet in maven central, so for now you have to install
it manually:

    git clone git://github.com/jakewins/requirejs-maven.git
    cd requirejs-maven
    mvn clean install

Then, add the plugin to your pom:

    <plugins> 
      ..
      <plugin>
        <groupId>com.voltvoodoo</groupId>
        <artifactId>requirejs-maven</artifactId>
        <version>1.0-SNAPSHOT</version>
        <executions>
          <execution>
            <goals>
              <goal>build</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>

### Goals

Requirejs-maven has one goal, "build", by default attached to the
"process-resources" maven phase. The "build" goal will go through
all the require modules you have defined, aggregate and minify them,
and put the result in your output directory.

### Default settings

By default, requirejs-maven looks for a js file named "main.js" in 
${basedir}/src/main/javascript, and puts the resulting main.js aggregate
file in ${project.build.outputDirectory}.

You can change the module name from the default ("main") by setting the
"module" config property. You can also provide a list of modules using the
"modules" config property, the modules setting overrides the module setting.

Minimum settings for the "modules" property would be:

    <configuration>
      ..
      <modules>
        <module>
          <name>myapp</name> <!-- Will lead to looking for a file named "myapp.js" -->
        </module>
        ..
      </modules>
    </configuration>

Requirejs-maven supports almost all settings that the requirejs optimization
tool does. Unfortunately, these are currently not documented properely.

Until they are, please refer to the java-doc in com.voltvoodoo.requirejs.RequireJsMojo