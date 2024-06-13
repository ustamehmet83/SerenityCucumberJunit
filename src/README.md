## The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                        Test runners and supporting code
    + resources
      + features                  Feature files
        + WikiSearch                  Feature file subdirectories
                 WikiSearch.feature
```
## Serenity BDD with Feature Files under src/test/resources

Serenity BDD uses Cucumber to write feature files that describe the behavior of the system in a natural language format. 
These feature files are a core part of the BDD approach and help in creating automated acceptance tests.
Feature files are written in Gherkin, a language that uses plain text to describe features, scenarios, and steps

## The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, Mehmet is performing a search on the wikipedia,
You should add new scenario feature file with Gherkin language using Given When Then annotation under src/test/resources folder.

```Gherkin
Feature: Wikipedia search functionality and verifications


  Scenario: Wikipedia Search Functionality Title Verification
    Given User is on Wikipedia home page
    When User types "Steve Jobs" in the wiki search box
    And User clicks wiki search button
    Then User sees "Steve Jobs" is in the wiki title

```

## Step Definitions under src/test/java
A Step Definition is a method with an expression that links it to one or more Gherkin steps. When Cucumber executes a Gherkin step in a scenario, it will look for a matching step definition to execute.

To illustrate how this works, look at the following Gherkin Scenario:
```Gherkin
Scenario: Wikipedia Search Functionality Header Verification
Given User is on Wikipedia home page
```

User is on Wikipedia home page part of the step (the text following the Given keyword) will match the following step definition:

```Gherkin
 @Given("User is on Wikipedia home page")
public void user_is_on_wikipedia_home_page() {
    wikiSearchPage.getDriver().get(ConfigReader.getProperty("webdriver.base.wikipedia.url"));
}
```
## Create Serenity Test Runner under src/test/java
We cannot run a Feature file on its own in a cucumber-based framework.
We need to create a Java class, which will run the Feature File. It is the starting point for JUnit to start executing the tests.
SerenityRunnerTests class creates under src/test/java. When you run the tests with serenity, you use the CucumberWithSerenity test runner.
If the feature files are not in the same package as the test runner class, you also need to use the @CucumberOptions class to provide the root directory
where the feature files found.
```js
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {
                "pretty", "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/cucumber_report.json",
                "rerun:target/serenity-reports/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "com.example.steps_definitions",
        dryRun = false,
        tags = "@wiki",
        publish=true

)
```
### Cucumber Options

In your Serenity BDD project, you can configure Cucumber options to specify how tests should be executed and reported. Below is an example configuration:

### plugin: 
Specifies the output formats and locations for the test results.
### features:
Specifies the path to the directory containing your feature files.
### dryRun:
When set to true, Cucumber will check that every step in the feature files has a corresponding step definition without actually running the tests.
### tags:
Filters the scenarios to be executed based on tags.
### publish:
When set to true, it publishes the report to reports.cucumber.io, which provides a detailed and visual representation of the test results.

## Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.
### Webdriver configuration
Serenity uses serenity.conf file in the src/test/resources directory to configure test execution options. serenity.config can also contain the environment URL and other options like headless mode and soon.
The WebDriver configuration is managed entirely from this file, as illustrated below:
```js
environments {
  chrome {
    webdriver {
      driver = "chrome"
      capabilities {
        browserName = "chrome"
        "goog:chromeOptions" {
          args = ["test-type", "ignore-certificate-errors", "start-maximized"
          "incognito", "disable-infobars", "disable-gpu", "disable-default-apps", "disable-popup-blocking"]
        }
      }
    }
  }
  edge {
    webdriver {
      capabilities {
        browserName = "MicrosoftEdge"
        "ms:edgeOptions" {
          args = ["test-type", "ignore-certificate-errors", "headless",
                  "incognito", "disable-infobars", "disable-gpu", "disable-default-apps", "disable-popup-blocking"]
        }
      }
    }
  }
  firefox {
    webdriver {
      //      driver = "firefox"
      capabilities {
        browserName = "firefox"

        timeouts {
          implicit = 1000
          script = 1000
          pageLoad = 1000
        }
        pageLoadStrategy = "normal"
        acceptInsecureCerts = true
        unhandledPromptBehavior = "dismiss"
        strictFileInteractability = true

        "moz:firefoxOptions" {
          args = ["-headless"],
          prefs {
            "dom.ipc.processCount": 8,
                    "javascript.options.showInConsole": false
          },
          log {"level": "trace"},
          env {
            "MOZ_LOG": "nsHttp:5",
          }
        }
      }
    }
  }

```

## Writing an API test

Let us start with writing our API test. In this test, we will test the GET /pet/{petId} API. This API will return a pet when you give its id in the URL.

However, we cannot call this API directly without any id. Hence, our test needs to first create a Pet and get its id before it calling the GET /pet/{petId} API end point.

In other words, we could write our test in the Given-When-Then format as follows.

```java
Given User create a pet using the Json file
When User create a pet using Pojo pet class structure
Then User gets Created previous test's pet calling details
```

## Adding the Serenity RestAssured Dependency
Serenity Rest Assured is a powerful tool that combines Serenity BDD and Rest Assured to provide a comprehensive framework for writing and managing API tests. This setup allows you to take advantage of Serenity's rich reporting and living documentation capabilities while leveraging Rest Assured's ease of use for REST API testing.

```java
<dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-rest-assured</artifactId>
<version>${serenity.version}</version>
      <scope>test</scope>
  </dependency>
```

## Gson (com.google.code.gson)
Gson is a Java library that can be used to convert Java Objects into their JSON representation and vice versa. It is a powerful and easy-to-use library developed by Google.
Add the following dependency to your pom.xml:
```java
 <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>${gson.version}</version>
 </dependency>
```
## JSON Schema Validator
he JSON Schema Validator library provides a comprehensive and robust solution for validating JSON documents against JSON schema definitions. This library is often used in applications where JSON data needs to be validated for correctness and adherence to specified formats.
```java
 <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>${json-schema-validator.version}</version>
 </dependency>
```
## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the `pom.xml` file causes the reports to be generated automatically once all the tests have completed when you run `mvn verify`?

```
             <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.plugin.version}</version>
                <configuration>
                    <parallel>methods</parallel>
                    <threadCount>1</threadCount>
                    <perCoreThreadCount>false</perCoreThreadCount>
                    <testFailureIgnore>true</testFailureIgnore>
                    <runOrder>Alphabetical</runOrder>
                    <includes>
                        <include>**/SerenityRunnerTests.java</include>
                        <include>**/CucumberTestSuite.java</include>
                    </includes>
                </configuration>
            </plugin>

            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>${maven.failsafe.plugin.version}</version>
                <configuration>
                    <includes>
                        <include>**/SerenityRunnerTests.java</include>
                        <include>**/CucumberTestSuite.java</include>
                    </includes>
                    <systemPropertyVariables>
                        <webdriver.base.url>${webdriver.base.url}</webdriver.base.url>
                    </systemPropertyVariables>
                    <parallel>classes</parallel>
                    <threadCount>${parallel.tests}</threadCount>
                    <forkCount>${parallel.tests}</forkCount>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.plugin.version}</version>
                <configuration>
                    <source>${maven.compiler.source.version}</source>
                    <target>${maven.compiler.target.version}</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>net.serenity-bdd</groupId>
                        <artifactId>serenity-single-page-report</artifactId>
                        <version>${serenity.version}</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```