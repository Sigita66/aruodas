import org.example.Helper;
import org.example.models.Plot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PlotTests {
    static WebDriver driver;
    static WebDriverWait wait;

    @Test
    public void positiveFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus", "Baltupiai", "Oginskio", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "1234", "123412341234", "20", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"});

        p.fillSubmit();
        String actual = driver.findElement(By.xpath("//*[@id=\"btPlanChooseOrder\"]")).getText();
        Assert.assertEquals(actual, "Užsakyti");
    }

    @Test
    public void positiveFeaturesFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus", "Baltupiai", "Smetonos", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "1234", "123412341234", "20", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"}, new String[]{"Elektra", "Dujos", "Vanduo", "Kraštinis sklypas", "Greta miško", "Be pastatų", "Geodeziniai matavimai", "Su pakrante", "Asfaltuotas privažiavimas"}, true, true);

        p.fillAllFeatures();
        String actual = driver.findElement(By.xpath("//*[@id=\"btPlanChooseOrder\"]")).getText();
        Assert.assertEquals(actual, "Užsakyti");
    }

    @Test
    public void checkCloseFeaturesFillPlot() {
        Plot p = new Plot();

        p.openCloseFeatures();
        if (driver.findElement(By.className("field-extra-row")).isDisplayed()) {
            Assert.fail();
        }
    }

    @Test
    public void checkSearhRegionFillPlot() {
        Plot p = new Plot();
        if (!p.searchRegion("vil")) {
            Assert.fail();
        }
    }


    @Test
    public void addrresNoMore4SymbolsFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "12345", "123412341234", "20", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"});
        p.fillSubmit();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ErrorDiv"))));

        String actual = driver.findElement(By.cssSelector("#newObjectForm > ul > li.has-error > span.error-arrow")).getText();
        Assert.assertEquals(actual, "Neteisingai įvesta reikšmė");
    }

    @Test
    public void rcNoLess12SymbolsFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "123", "12341234123", "20", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"});
        p.fillSubmit();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ErrorDiv"))));
        String actual = driver.findElement(By.cssSelector("#newObjectForm > ul > li.form-field-extra-margin.has-error > span")).getText();
        Assert.assertEquals(actual, "Neteisingas numerio formatas");
    }

    @Test
    public void rcNoMore12SymbolsFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus m.", "Žirmūnai", "Juozapav", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "123", "1234123412348", "20", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"});
        p.fillSubmit();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ErrorDiv"))));
        String actual = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[11]/span")).getText();
        Assert.assertEquals(actual, "Neteisingas numerio formatas");
    }
    @Test
    public void noPurposesFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus m.", "Žirmūnai", "Juozapav", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "123", "123412341234", "20", new String[]{/*"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"*/});
        p.fillSubmit();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ErrorDiv"))));
        String actual = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[16]/label/span[2]")).getText();
        Assert.assertEquals(actual, "Pasirinkite paskirtį");
    }
    @Test
    public void checkPriceOneAcreFillPlot() {
        Plot p = new Plot("Vilnius", "Vilniaus m.", "Žirmūnai", "Juozapav", "Perku sklypa", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "2000", "+37068756256", "nbbv@mano.lt", new boolean[]{true, true, true}, "123", "123412341234", "51", new String[]{"Namų valda", "Daugiabučių statyba", "Žemės ūkio", "Sklypas soduose", "Miškų ūkio", "Pramonės", "Sandėliavimo", "Komercinė", "Rekreacinė", "Kita"});
        p.fill();
        if (!p.checkPriceOneAcre()) {
            Assert.fail();
        }

    }


    @BeforeClass
    public void beforeClass() {
        Helper.setUp();
        driver = Helper.driver;
        wait = Helper.wait;

    }

    @BeforeMethod
    public void beforeMethod() {

        driver.get("https://www.aruodas.lt/ideti-skelbima/?obj=11&offer_type=1");
    }
    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
