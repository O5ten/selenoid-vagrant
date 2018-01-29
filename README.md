# Selenoid Grid Example

This repo gives an idea on how to get started with running Selenium-tests using a Virtualized Selenium Grid. This project uses a quite modern approach to make it possible to run Selenium tests against any version of browser in an easy manner.

### Prerequisites
The following must be installed on your local system aside from enabling VT-x in your BIOS.

- [Vagrant](https://www.vagrantup.com)
- [VirtualBox](https://www.virtualbox.org)
- [Java 9 SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (To run the Selenium Remote Test Java Example)

## Usage
The Grid can be used for development of selenium tests towards any particular browser.

###  Run the grid
run the following at the root of this maven module

```bash
vagrant up
```

The Vagrantfile is a wrapper for virtualbox that downloads and provisions an image for ubuntu/trusty64 by updating the system and downloads ```Docker CE``` and provisions ```selenoid``` and ```selenoid-ui``` using the selenoid configuration manager.

##### The Selenoid grid
```
http://localhost:4444/wd/hub
```

##### The Selenoid UI  
```
http://localhost:8080
```

### Capabilities
This default setup of Selenoid comes packaged with two versions of each browser, that is, however highly configurable inside your vagrant machine. ```vagrant ssh``` gets you inside the virtualbox. Consult the Selenoid documentation on how to modify the browsers.json file and restart docker for the new capabilities to take effect.

- Chromes latest and one earlier
- Firefoxs latest and one earlier
- Operas latest and one earlier

### Selenium Java Example
Now you can start developing towards the Selenoid grid. This repository contains a single example for triggering a JUnit test using the basic Java bindings towards the selenoid-grid. Implement using your favourite language, IDE and build system.

Or run a clean install of this module.
```
mvn clean install
```

```java
public class SeleniumTest {
    private WebDriver browser;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities abilities = DesiredCapabilities.chrome();
        this.browser = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), abilities);
        this.browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.browser.manage().window().setPosition(new Point(220, 10));
        this.browser.manage().window().setSize(new Dimension(1000,650));
    }

    @Test
    public void testSimple() throws Exception {
        this.browser.get("http://www.google.com");
        assertEquals("Google", this.browser.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        this.browser.quit();
    }
}
```

### Teardown
When you have no more need of running selenium-tests you can stop the Vagrant machine using
```
vagrant halt
```
or destroy it to have a clean environment for next day using
```
vagrant destroy
```

### More Reading
- [Vagrant](https://www.vagrantup.com)
- [VirtualBox](https://www.virtualbox.org)
- [Selenoid](https://github.com/aerokube/selenoid)
- [Selenoid UI](https://github.com/aerokube/selenoid-ui)
- [Selenoid Complete Reference Guide](http://aerokube.com/selenoid-ui/latest/)
