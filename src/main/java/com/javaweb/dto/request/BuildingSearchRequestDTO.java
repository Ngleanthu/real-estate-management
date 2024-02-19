package com.javaweb.dto.request;

import com.javaweb.dto.AbstractDTO;

public class BuildingSearchRequestDTO extends AbstractDTO {

    private Long id;
    private String name;
    private String district;
    private String ward;
    private String street;
    private Long floorArea;
    private Long numberOfBasement;
    private String direction;
    private String level;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private String managerName;
    private String managerPhone;


    private Long staffId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public void setRentPriceFrom(Long rentPriceFrom) {
        this.rentPriceFrom = rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public void setRentPriceTo(Long rentPriceTo) {
        this.rentPriceTo = rentPriceTo;
    }

    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }

    public void setRentAreaFrom(Long rentAreaFrom) {
        this.rentAreaFrom = rentAreaFrom;
    }

    public Long getRentAreaTo() {
        return rentAreaTo;
    }

    public void setRentAreaTo(Long rentAreaTo) {
        this.rentAreaTo = rentAreaTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
