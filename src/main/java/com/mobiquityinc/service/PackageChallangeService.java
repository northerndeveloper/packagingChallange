package com.mobiquityinc.service;

import com.mobiquityinc.entity.BestPackage;
import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.util.List;

/**
 * Service interface which has required signatures for creating best package list
 */
public interface PackageChallangeService {

    String generateContentFromFilePath(String filePath) throws IOException;

    List<BestPackage> splitContentAndGeneratePackageList(String content) throws APIException;

    List<String> findBestPackage(List<BestPackage> bestPackageList);

}
