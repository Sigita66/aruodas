import org.example.Helper;
import org.example.models.SearchRE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchRETests {
    static WebDriver driver;
    static WebDriverWait wait;

    @Test
    public void positiveFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus", "Antakalnis", "Oginskio", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbv@mano.lt", new boolean[]{true,true,true},"Sklypai mieste");
        sre.fillSubmit();

        String actual = driver.findElement(By.xpath("//*[@id=\"btPlanChooseOrder\"]")).getText();
        Assert.assertEquals(actual, "Užsakyti");
    }


    @Test
    public void noEmailFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "Antakalnio", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "", new boolean[]{true,true,true}, "Butai pirkti");
        sre.fillSubmit();
        String actual = driver.findElement(By.cssSelector("#newObjectForm > ul > li.required-form-field.has-error > span.error-arrow")).getText();
        Assert.assertEquals(actual, "Nurodykite el. pašto adresą, kad vėliau galėtumėte redaguoti arba pašalinti skelbimą");
    }

    @Test
    public void emailNoEtaFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbbvmano.lt", new boolean[]{true,true,true}, "Namai pirkti");
        sre.fillSubmit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[22]/span[2]")));
        String actual = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[22]/span[2]")).getText();
        Assert.assertEquals(actual, "Blogas el. pašto adresas");
    }

    @Test
    public void emailNoDomainFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "filaret", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "3000", "+37068756256", "nbb@vmano", new boolean[]{true,true,true}, "Namai pirkti");
        sre.fillSubmit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[22]/span[2]")));
        String actual = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[22]/span[2]")).getText();
        Assert.assertEquals(actual, "Blogas el. pašto adresas");
    }

    @Test
    public void noPriceFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "", "+37068756256", "nbbv@mano.lt", new boolean[]{true,true,true}, "Butai pirkti");
        sre.fillSubmit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[20]/span[3]")));
        String actual = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[20]/span[3]")).getText();
        Assert.assertEquals(actual, "Neteisinga kaina");
    }

    @Test
    public void priceMinusFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "-1000", "+37068756256", "nbbv@mano.lt", new boolean[]{true,true,true}, "Butai pirkti");
        sre.fill();

        String actual = driver.findElement(By.id("priceField")).getAttribute("value");
        Assert.assertEquals(actual, "1000");
    }

    @Test
    public void noPhoNoFillFormTest() {
        SearchRE sre = new SearchRE("Vilnius", "Vilniaus m.", "Antakalnis", "A. Domaševičiaus g.", "Perku buta", new String[]{"C:\\Users\\santa\\html visual studio\\images\\B copy.jpg", "C:\\Users\\santa\\html visual studio\\images\\puppy.jpg"}, "https://www.youtube.com/watch?v=4Nu2aQm65DM", "www.3d.lt", "5000", "", "nbbv@mano.lt", new boolean[]{true,true,true}, "Butai pirkti");
        sre.fillSubmit();
        String actual = driver.findElement(By.cssSelector("#newObjectForm > ul > li.required-form-field.has-error > span.error-arrow")).getText();
        Assert.assertEquals(actual, "Neteisingas telefono numeris");
    }


    @BeforeClass
    public void beforeClass() {
        Helper.setUp();
        driver= Helper.driver;
        wait=Helper.wait;

    }

    @BeforeMethod
    public void beforeMethod() {

        driver.get("https://www.aruodas.lt/ideti-skelbima/?obj=10");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
