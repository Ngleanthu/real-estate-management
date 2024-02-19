package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum districtCode {
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),
    QUAN_9 ("Quận 9"),
    QUAN_10 ("Quận 10"),
    QUAN_11 ("Quận 11"),
    QUAN_12 ("Quận 12");

    private final String districtName;

    districtCode(String districtName){
        this.districtName = districtName;
    }

    public String getDistrictName(){
        return districtName;
    }

    public static String getDistrictNameByCode(String codeOfDistrict){
        for(districtCode item : districtCode.values()){
            if(item.name().equals(codeOfDistrict)){
                return item.getDistrictName();
            }
        }
        return null;
    }
    public static Map<String, String> district() {
        Map<String, String> listDistrict= new LinkedHashMap<>();
        for(districtCode item : districtCode.values()){
            listDistrict.put(item.toString(), item.getDistrictName());
        }
        return listDistrict;
    }
}
