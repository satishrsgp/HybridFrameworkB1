package org.Abc.testCases;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class baseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties pr;


    @Parameters({"browser", "os"})
    @BeforeClass(groups = {"sanity", "regression", "master", "dataDriven"})
    public void setUp(String browser, String os) {

        pr = new Properties();
        try {
            FileReader fileReader = new FileReader("src/test/resources/configFiles/config.properties");
            pr.load(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger = LogManager.getLogger(this.getClass());
        logger.info("Base class initiated");
        //platform
        if (pr.getProperty("ExecutionMode").equalsIgnoreCase("remote")) {
            System.setProperty("selenium.telemetry.enabled", "false");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else if (os.equalsIgnoreCase("Mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                logger.error("Please select correct operating system");
                return;
            }

            //browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    logger.error("Invalid browser name");
                    return;

            }

            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }


        if (pr.getProperty("ExecutionMode").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    logger.error("Invalid browser name");
                    return;
            }
        }

        logger.info("Driver Object created");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //https://tutorialsninja.com/demo/
        driver.get(pr.getProperty("url"));
        driver.manage().window().maximize();
    }

    // Close the browser
    @AfterClass(groups = {"sanity", "regression", "master", "dataDriven"})
    public void tearDown() {
        driver.quit();
    }

    public String captureScreenshot(String testcasename) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testcasename + timestamp + ".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE); // Screenshot
        String path = System.getProperty("user.dir") + "\\screenShots\\" + fileName;
        File targetfile = new File(path);
        sourcefile.renameTo(targetfile); // Saves the file
        return path;
    }
}
