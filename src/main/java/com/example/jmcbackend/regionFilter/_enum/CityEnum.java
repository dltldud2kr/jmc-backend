package com.example.jmcbackend.regionFilter._enum;

//관리 주체를 DB 테이블이 아닌 -> 객체로 사용함.
public enum CityEnum implements EnumMapperType {
    //동까지는 딱히 쓸일이 없을꺼 같긴한데 일단 확장성 생각해서 추가한것. 경우에 따라서 추가하고 사용하면됨.
    //지역안에 동일한 구,동은 존재할 수 있기때문에 구와동은 큰 카테고리 도시이름안에 종속되어야함.
    DAEGU_01("대구광역시","수성구"),
    DAEGU_02("대구광역시","남구"),
    DAEGU_03("대구광역시","북구"),
    DAEGU_04("대구광역시","달서구"),
    DAEGU_05("대구광역시","달성군"),
    GYUNGBOOK_01("경상북도","경산시"),
    GYUNGBOOK_02("경상북도","칠곡군"),
    EMPTY("없는 지역",""),
    ALL("전체","");


    public static CityEnum fromCode(String code) {
        for (CityEnum cityEnum : CityEnum.values()) {
            if (cityEnum.getCode().equalsIgnoreCase(code)) {
                return cityEnum;
            }
        }
        return null; // 매칭되는 ENUM 상수가 없는 경우 null을 반환.
    }
    private String regionName;
    private String subRegionName;

    CityEnum(String regionName, String subRegionName) {
        this.regionName = regionName;
        this.subRegionName = subRegionName;
    }



    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getResionName() {
        return regionName;
    }

    @Override
    public String getSubResionName() {
        return subRegionName;
    }
}
