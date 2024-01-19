package org.example.models;
import org.example.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Plot extends RealEstate {
    public String addressNo;
    public String rcNo;
    public String area;
    public String[] purposes;
    public String[] features;
    public boolean interChange;
    public boolean auction;

    public Plot() {
    }

    public Plot(String region, String district, String quartal, String street, String description, String[] photos, String youtube, String tour3d, String price, String phoNo, String email, boolean[] bottomCheckboxes, String addressNo, String rcNo, String area, String[] purposes) {
        super(region, district, quartal, street, description, photos, youtube, tour3d, price, phoNo, email, bottomCheckboxes);
        this.addressNo = addressNo;
        this.rcNo = rcNo;
        this.area = area;
        this.purposes = purposes;
    }

    public Plot(String region, String district, String quartal, String street, String description, String[] photos, String youtube, String tour3d, String price, String phoNo, String email, boolean[] bottomCheckboxes, String addressNo, String rcNo, String area, String[] purposes, String[] features, boolean interChange, boolean auction) {
        super(region, district, quartal, street, description, photos, youtube, tour3d, price, phoNo, email, bottomCheckboxes);
        this.addressNo = addressNo;
        this.rcNo = rcNo;
        this.area = area;
        this.purposes = purposes;
        this.features = features;
        this.interChange = interChange;
        this.auction = auction;
    }


    @Override
    public void fillSubmit() {
        fill();
        super.submit();
    }

    @Override
    public void fill() {
        super.fill();
        fillAddressNo();
        fillRCNo();
        fillArea();
        fillPurposes();
    }

    public void fillAllFeatures() {
        fill();
        fillFeatures();
        fillInterestedChange();
        fillAuction();
        super.submit();
    }

    public void fillAddressNo() {
        driver.findElement(By.name("FHouseNum")).sendKeys(this.addressNo);

    }


    public void fillRCNo() {
        driver.findElement(By.name("RCNumber")).sendKeys(this.rcNo);

    }

    public void fillArea() {
        driver.findElement(By.id("fieldFAreaOverAll")).sendKeys(this.area);
    }


    public void fillPurposes() {
        for (int i = 0; i < purposes.length; i++) {
            switch (purposes[i]) {
                case "Namų valda":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[1]/label");
                    break;
                case "Daugiabučių statyba":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[2]/label");
                    break;
                case "Žemės ūkio":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[3]/label");
                    break;
                case "Sklypas soduose":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[4]/label");
                    break;
                case "Miškų ūkio":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[5]/label");
                    break;
                case "Pramonės":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[6]/label");
                    break;
                case "Sandėliavimo":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[7]/label");
                    break;
                case "Komercinė":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[8]/label");
                    break;
                case "Rekreacinė":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[9]/label");
                    break;
                case "Kita":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[16]/div/div[10]/label");
                    break;

            }

        }
    }

    public void fillFeatures() {
        super.clickElementId("showMoreFields");
        for (int i = 0; i < features.length; i++) {
            switch (features[i]) {
                case "Elektra":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[1]/label");
                    break;
                case "Dujos":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[2]/label");
                    break;
                case "Vanduo":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[3]/label");
                    break;
                case "Kraštinis sklypas":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[4]/label");
                    break;
                case "Greta miško":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[5]/label");
                    break;
                case "Be pastatų":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[6]/label");
                    break;
                case "Geodeziniai matavimai":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[7]/label");
                    break;
                case "Su pakrante":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[8]/label");
                    break;
                case "Asfaltuotas privažiavimas":
                    super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[20]/div/div[9]/label");
                    break;
            }
        }
    }

    public void fillInterestedChange() {
        if (interChange) {
            super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[22]/div/div/div/label");
        }
    }

    public void fillAuction() {
        if (auction) {

            super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[23]/div/div/div/label");
        }
    }

    public void openCloseFeatures() {
        super.clickElementId("showMoreFields");
        super.clickElementXpath("//*[@id=\"newObjectForm\"]/ul/li[19]/span/a/img");
    }

    public boolean searchRegion(String search) {

        driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[3]/span[1]/span")).click();
        driver.findElement(By.className("dropdown-input-search-value")).sendKeys(search);

        Helper.wait(100);
        List<WebElement> citiesSearch = driver.findElement(By.id("regionDropdown")).findElements(By.xpath("//li[contains(@class, 'temp-searchresult-street')]"));
        boolean found = false;

        for (int i = 0; i < citiesSearch.size(); i++) {
            if (citiesSearch.get(i).getText().toLowerCase().contains(search.toLowerCase())) {
                found = true;
            }
        }
        return found;
    }


    public boolean checkPriceOneAcre() {
        boolean temp = false;
        int areaInput = Integer.parseInt(area);
        int priceInput = Integer.parseInt(price);
        String priceOneAcreTxt = driver.findElement(By.id("pricePerField")).getText();

        int priceOneAcreInt = Integer.parseInt(priceOneAcreTxt.split(" ")[0]);
        if (priceOneAcreInt == priceInput / areaInput) {
            temp = true;
        }

        return temp;
    }

}




