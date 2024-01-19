package org.example.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class SearchRE extends RealEstate {

    public String objtype;

    public SearchRE(String region, String district, String quartal, String street, String description, String[] photos, String youtube, String tour3d, String price, String phoNo, String email, boolean[] bottomCheckboxes, String objtype) {
        super(region, district, quartal, street, description, photos, youtube, tour3d, price, phoNo, email, bottomCheckboxes);
        this.objtype = objtype;
    }

    @Override
    public void fill() {
        super.fill();
        fillObjType();
    }
    @Override
    public void fillSubmit() {
        super.fill();
        fillObjType();
        super.submit();
    }


    public void fillObjType() {

        driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[7]/span[1]/span")).click();

        List<WebElement> dropdown = driver.findElement(By.xpath("//*[@id=\"newObjectForm\"]/ul/li[7]/span[1]/ul")).findElements(By.tagName("li"));
        for (int i = 0; i < dropdown.size(); i++) {
            if (dropdown.get(i).getText().toLowerCase().contains(this.objtype.toLowerCase())) {
                dropdown.get(i).click();
                break;
            }
        }
    }


}
