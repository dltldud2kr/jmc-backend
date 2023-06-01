package com.example.jmcbackend.regionFilter._enum;

import lombok.Getter;


public class EnumMapperValue  {
    private String code;
    private String regionName;
    private String subRegionName;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        code = enumMapperType.getCode();
        regionName = enumMapperType.getResionName();
        subRegionName = enumMapperType.getSubResionName();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getSubRegionName() {
        return subRegionName;
    }

    public void setSubRegionName(String subRegionName) {
        this.subRegionName = subRegionName;
    }
}

