package DTO;

public class CarDTO {
    private String id;
    private String brand;
    private String model;
    private String version;
    private String car_condition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCarCondition() {
        return car_condition;
    }

    public void setCarCondition(String car_condition) {
        this.car_condition = car_condition;
    }
}