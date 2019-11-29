package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {

  private final BigDecimal bigDecimal;
  private String name;
  private BigDecimal taxPercentage;

  protected Category(String name, BigDecimal bigDecimal) {
    this.name = name;
    this.bigDecimal = bigDecimal;
  }

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
