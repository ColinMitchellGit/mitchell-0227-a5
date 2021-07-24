/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InventoryItem {
    private String symbol = "$";
    private BigDecimal value;
    private String serial;
    private String name;

    //InventoryItem constructor
    public InventoryItem(String value, String serial, String name) {
        this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        this.serial = serial;
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
}
