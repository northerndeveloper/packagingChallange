package com.mobiquityinc.packer;

import com.mobiquityinc.entity.BestPackage;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.PackageChallangeService;
import com.mobiquityinc.service.impl.PackageChallengeServiceImpl;

import java.io.IOException;
import java.util.List;

public class Packer {

    static PackageChallangeService packageChallangeService = new PackageChallengeServiceImpl();

    private Packer() {
    }


    public static void main(String[] args) throws APIException, IOException {

        Packer.pack("packagingTest.txt");

    }

    public static String pack(String filePath) throws APIException, IOException {

        String content = packageChallangeService.generateContentFromFilePath(filePath);

        List<BestPackage> bestPackageList = packageChallangeService.splitContentAndGeneratePackageList(content);

        List<String> bestPackageResults = packageChallangeService.findBestPackage(bestPackageList);

        for (String bestPackageResult : bestPackageResults) {
            System.out.println(bestPackageResult);
        }

        return null;
    }

}
