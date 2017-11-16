import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities abilities = DesiredCapabilities.chrome();
        abilities.setCapability("version", "61.0");
        abilities.setCapability("name", "Testing Selenium-2 Remote WebDriver");
        driver = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), abilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(220, 10));
        driver.manage().window().setSize(new Dimension(1000,650));
    }
    
    @Test
    public void testSimple() throws Exception {
        this.driver.get("http://www.google.com");
        assertEquals("Google", this.driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}
