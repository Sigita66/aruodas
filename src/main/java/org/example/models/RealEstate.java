package org.example.models;
import org.example.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RealEstate {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public String region;
    public String district;
    public String quartal;
    public String street;
    public String description;
    public String[] photos;
    public String youtube;
    public String tour3d;
    public String price;
    public String phoNo;
    public String email;
    public boolean[] bottomCheckboxes;

    public RealEstate() {
    }

    public RealEstate(String region, String district, String quartal, String street, String description, String[] photos, String youtube, String tour3d, String price, String phoNo, String email, boolean[] bottomCheckboxes) {
        this.region = region;
        this.district = district;
        this.quartal = quartal;
        this.street = street;
        this.description = description;
        this.photos = photos;
        this.youtube = youtube;
        this.tour3d = tour3d;
        this.price = price;
        this.phoNo = phoNo;
        this.email = email;
        this.bottomCheckboxes = bottomCheckboxes;
    }


    public void fillSubmit() {
        fill();
        submit();
    }

    public void submit() {
        clickElementId("submitFormButton");

    }

    public void fill() {
        fillLocation();
        fillDescription();
        loadPhotos();
        fillYoutube();
        fillTour3D();
        fillPrice();
        fillPhoNo();
        fillEmail();
        fill3bottomCheckboxes();
    }

    public void fillLocation() {
        fillRegion();
        fillDistrict();
        fillQuartal();
        fillStreet();
    }

    public void fillRegion() {
        driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[3]/span[1]/span")).click();
        List<WebElement> cities = driver.findElements(By.className("dropdown-input-values-address")).get(0).findElements(By.tagName("li"));
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getText().toLowerCase().contains(this.region.toLowerCase())) {
                cities.get(i).click();
                break;
            }
        }
    }

    public void fillDistrict() {
        Helper.wait(500);
        driver.findElement(By.xpath("//*[@id=\"district\"]/span")).click();
        List<WebElement> districts = driver.findElements(By.className("dropdown-input-values-address")).get(1).findElements(By.tagName("li"));
        for (int i = 0; i < districts.size(); i++) {
            if (districts.get(i).getText().toLowerCase().contains(this.district.toLowerCase())) {
                districts.get(i).click();
                break;
            }
            }

    }

    public void fillQuartal() {
        Helper.wait(500);
        String classList=driver.findElement(By.id("quartalField")).getAttribute("class");
        if (classList.contains("hide")||classList.contains("disabled")){
            return;
        }
        driver.findElement(By.xpath("//*[@id=\"quartalField\"]/span[1]/span[2]")).click();
        List<WebElement> quartals = driver.findElements(By.className("dropdown-input-values-address")).get(2).findElements(By.tagName("li"));
        for (int i = 0; i < quartals.size(); i++) {
            if (quartals.get(i).getText().toLowerCase().contains(this.quartal.toLowerCase())) {
                quartals.get(i).click();
                break;
            }
        }
    }

    public void fillStreet() {
        Helper.wait(500);
        String classList=driver.findElement(By.id("streetField")).getAttribute("class");
        if (classList.contains("disabled")){
            return;
        }
        driver.findElement(By.xpath("//*[@id=\"streetField\"]/span[1]/span[2]")).click();
        List<WebElement> streets;
        try {
            streets = driver.findElements(By.className("dropdown-input-values-address")).get(3).findElements(By.tagName("li"));
        }
        catch (Exception e){
            streets = driver.findElements(By.className("dropdown-input-values-address")).get(2).findElements(By.tagName("li"));
        }
        for (int i = 0; i < streets.size(); i++) {
            if (streets.get(i).getText().toLowerCase().contains(this.street.toLowerCase())) {
                streets.get(i).click();
                break;
            }
        }
    }

    public void fillDescription() {
        driver.findElement(By.name("notes_lt")).sendKeys(this.description);
    }

    public void loadPhotos() {
        for (int i = 0; i < photos.length; i++) {
            driver.findElement(By.xpath("//*[@id=\"uploadPhotoBtn\"]/input")).sendKeys(photos[i]);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"photosThumbsList\"]/div[" + (i + 1) + "]/a")));
        }

    }

    public void fillYoutube() {
        driver.findElement(By.name("Video")).sendKeys(this.youtube);
    }

    public void fillTour3D() {
        driver.findElement(By.name("tour_3d")).sendKeys(this.tour3d);
    }

    public void fillPrice() {
        driver.findElement(By.id("priceField")).sendKeys(this.price);
    }

    public void fillPhoNo() {
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys(this.phoNo);
    }

    public void fillEmail() {
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(this.email);
    }

    public void fill3bottomCheckboxes() {
        if (bottomCheckboxes[0]) {
            driver.findElement(By.cssSelector("label[for='cbdont_show_in_ads'] span")).click();
        }
        if (bottomCheckboxes[1]) {
            driver.findElement(By.cssSelector("label[for='cbdont_want_chat'] span")).click();
        }
        if (bottomCheckboxes[2]) {
            driver.findElement(By.cssSelector("label[for='cbagree_to_rules'] span")).click();
        }
    }

            public void clickElementId (String id){
                driver.findElement(By.id(id)).click();
            }

            public void clickElementXpath (String xpath){
                driver.findElement(By.xpath(xpath)).click();
            }


        }

