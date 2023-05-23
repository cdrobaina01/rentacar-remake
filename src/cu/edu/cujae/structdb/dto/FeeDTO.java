package cu.edu.cujae.structdb.dto;

public class FeeDTO implements AbstractDTO {
    private int id;
    private String name;
    private double dayCost;

    public FeeDTO() {

    };

    public FeeDTO(int id, String name, double dayCost){
        this.id = id;
        this.name = name;
        this.dayCost = dayCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){return this.name;}

    public void setName(String name){
        this.name = name;
    }

    public double getDayCost() {
        return dayCost;
    }

    public void setDayCost(double dayCost) {
        this.dayCost = dayCost;
    }
}
