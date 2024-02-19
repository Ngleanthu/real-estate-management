package com.javaweb.dto.response;

public class BuildingSearchResponse {

    private Long id;
    private String name;
    private String address;
    private Long numberOfBasement;
    private String managerName;
    private Long floorArea;
    private String emptyArea;
    private String rentArea;
    private String serviceFee;
    private String brokerageFee;

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(String emptyArea) {
        this.emptyArea = emptyArea;
    }

    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public Long getFloorArea() {return floorArea;}
    public void setFloorArea(Long floorArea) {this.floorArea = floorArea;}

    public String getRentArea() {return rentArea;}
    public void setRentArea(String rentArea) {this.rentArea = rentArea;}

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
