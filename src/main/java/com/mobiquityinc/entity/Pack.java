package com.mobiquityinc.entity;

import java.math.BigDecimal;

public class Pack implements Comparable<Pack> {

    private int index;

    private double weight;

    private BigDecimal cost;

    public Pack() {

    }

    public Pack(int index, double weight, BigDecimal cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(Pack u) {
        if (getCost() == null || u.getCost() == null) {
            return 0;
        }
        if(u.getCost().compareTo(getCost()) == 0) {
           return BigDecimal.valueOf(getWeight()).compareTo(BigDecimal.valueOf(u.getWeight()));
        }
        return u.getCost().compareTo(getCost());
    }
}
