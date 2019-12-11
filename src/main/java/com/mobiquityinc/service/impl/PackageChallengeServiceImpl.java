package com.mobiquityinc.service.impl;

import com.mobiquityinc.entity.BestPackage;
import com.mobiquityinc.entity.Pack;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.PackageChallangeService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * * Service implementation which has required signatures for creating best package list
 */
public class PackageChallengeServiceImpl implements PackageChallangeService {


    /**
     * Gets the file from the filepath , reads it and returns it as a String
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public String generateContentFromFilePath(String filePath) throws IOException {

        ClassLoader classLoader = new PackageChallengeServiceImpl().getClass().getClassLoader();

        File file = new File(classLoader.getResource(filePath).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }

    /***
     * Splits content which gets from the file and generate a packageList by that
     * @param content
     */
    public List<BestPackage> splitContentAndGeneratePackageList(String content) throws APIException {

        List<BestPackage> bestPackageList = new ArrayList<>();
        List<Pack> packToSendList;
        Pack packToSend;
        BestPackage bestPackage;

        validate(content.isEmpty(), "There is no value inside the file to manage best packages");

        String[] lines = content.split("\\r?\\n");
        validate(lines == null || lines.length == 0, "packaging test file is not valid to manage best packages");

        for (String line : lines) {
            packToSendList = new ArrayList<>();

            String[] parts = line.split("\\:");
            validate(parts == null || parts.length == 0, "packaging test file is not valid to manage best packages");

            String maxWeightOfPackage = parts[0];

            String[] packs = parts[1].split(" ");
            validate(packs == null || packs.length == 0, "packaging test file is not valid to manage best packages");

            for (String pack : packs) {
                if (pack == null || pack.isEmpty()) {
                    continue;
                }
                pack = pack.substring(pack.indexOf("(") + 1);
                pack = pack.substring(0, pack.indexOf(")"));
                String[] packParts = pack.split("\\,");
                validate(packParts == null || packParts.length == 0, "packaging test file is not valid to manage best packages");

                packToSend = new Pack(Integer.parseInt(packParts[0]), Double.parseDouble(packParts[1]), new BigDecimal(packParts[2].substring(1)));
                packToSendList.add(packToSend);

            }
            Collections.sort(packToSendList);
            bestPackage = new BestPackage(Double.parseDouble(maxWeightOfPackage), packToSendList);
            bestPackageList.add(bestPackage);
        }
        return bestPackageList;
    }

    private void validate(boolean b, String s) throws APIException {
        if (b) {
            throw new APIException(s);
        }
    }

    /**
     * Finds best packages with the most expensive and less weight packages
     *
     * @param bestPackageList
     * @return
     */
    public List<String> findBestPackage(List<BestPackage> bestPackageList) {

        List<String> bestPackageItems = new ArrayList<>();

        for (BestPackage bestPackage : bestPackageList) {

            StringBuilder bestPackageItem = new StringBuilder();
            double remainingWeight = bestPackage.getMaxWeightOfPackage();

            for (Pack pack : bestPackage.getPackerList()) {

                if (pack.getWeight() > remainingWeight) {
                    continue;
                } else {

                    if (!bestPackageItem.toString().isEmpty()) {
                        bestPackageItem.append(",");
                    }

                    remainingWeight = remainingWeight - pack.getWeight();

                    bestPackageItem.append(pack.getIndex());
                }
            }
            if (bestPackageItem.toString().isEmpty()) {
                bestPackageItem.append("-");
            }
            bestPackageItems.add(bestPackageItem.toString());
        }
        return bestPackageItems;
    }
}
