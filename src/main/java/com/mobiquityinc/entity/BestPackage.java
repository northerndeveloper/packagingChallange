package com.mobiquityinc.entity;

import com.mobiquityinc.entity.Pack;

import java.util.List;

public class BestPackage {

    public double maxWeightOfPackage;

    public List<Pack> packList;

    public BestPackage(double maxWeightOfPackage, List<Pack> packList) {
        this.maxWeightOfPackage = maxWeightOfPackage;
        this.packList = packList;
    }

    public double getMaxWeightOfPackage() {
        return maxWeightOfPackage;
    }

    public void setMaxWeightOfPackage(double maxWeightOfPackage) {
        this.maxWeightOfPackage = maxWeightOfPackage;
    }

    public List<Pack> getPackerList() {
        return packList;
    }

    public void setPackerList(List<Pack> packerList) {
        this.packList = packerList;
    }
}
