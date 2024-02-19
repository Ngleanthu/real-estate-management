package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum typeCode {

    TANG_TRET ( "Tầng trệt"),

    NOI_THAT ( "Nội thất"),

    NGUYEN_CAN ( "Nguyên căn");

    private final String buildingType;
    typeCode(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingType(){
        return buildingType;
    }


    public static Map<String, String> typeCode(){
        Map<String, String> listType = new HashMap<>();
        for(typeCode item : typeCode.values()){
            listType.put(item.toString(), item.getBuildingType());
        }
        return listType;
    }
}

