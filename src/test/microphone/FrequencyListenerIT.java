package microphone;

import microphone.FrequencyListener;
import util.drivers.AdvancedChromeOption;
import util.drivers.EnhancedWebDriver;
import util.drivers.AdvancedChromeDriver;
import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class FrequencyListenerIT {
    private static EnhancedWebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        driver = new EnhancedWebDriver(AdvancedChromeDriver.getDriver(AdvancedChromeOption.Microphone));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(5000);
    }
    @Test
    public void FrequencyListener_Should_CreatePointList() throws InterruptedException {
        assertThat("The page did not load correctly",
                FrequencyListener.start(driver),
                is(equalTo("Virtual Oscilloscope | Academo.org - Free, interactive, education."))
        );
    }
}
