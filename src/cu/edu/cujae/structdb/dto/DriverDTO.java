package cu.edu.cujae.structdb.dto;

public class DriverDTO implements AbstractDTO {
    private String dni;
    private String name;
    private AuxiliaryDTO category;
    private String address;

    public DriverDTO() {
    }

    public DriverDTO(String dni, String name, AuxiliaryDTO category, String address) {
        this.dni = dni;
        this.name = name;
        this.category = category;
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuxiliaryDTO getCategory() {
        return category;
    }

    public void setCategory(AuxiliaryDTO category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
