package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {
    private String name;
    private BigDecimal taxPercentage;

    protected void setName(String name) {
        this.name = name;
    }

    BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    protected void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
}
