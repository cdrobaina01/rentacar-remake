package cu.edu.cujae.structdb.dto;

public class TouristDTO implements AbstractDTO {
    private String passport;
    private String name;
    private int age;
    private String sex;
    private String contact;
    private AuxiliaryDTO country;

    public TouristDTO() {

    }

    public TouristDTO(String passport, String name, int age, String sex, String contact, AuxiliaryDTO country) {
        this.passport = passport;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.country = country;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public AuxiliaryDTO getCountry() {
        return country;
    }

    public void setCountry(AuxiliaryDTO country) {
        this.country = country;
    }
}
