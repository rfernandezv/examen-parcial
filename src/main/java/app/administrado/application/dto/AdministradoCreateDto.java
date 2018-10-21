package app.administrado.application.dto;

import java.math.BigDecimal;

public class AdministradoCreateDto {
    private long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Boolean isActive;
    
    private String code;
    private String process;
    private BigDecimal balance;
    private String currency;
    
    private String serie;
    private String brand;
    private String model;
   
    public AdministradoCreateDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
    }
    
    public String getIdentityDocument() {
            return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
    }

    public Boolean getIsActive() {
            return isActive;
    }

    public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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


    
}
