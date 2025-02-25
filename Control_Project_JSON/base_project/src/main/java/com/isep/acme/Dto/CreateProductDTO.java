package com.isep.acme.Dto;

public class CreateProductDTO {

    private String designation;
    private String description;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String designation, String description) {
        this.designation = designation;
        this.description = description;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
