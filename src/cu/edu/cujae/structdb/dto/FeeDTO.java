package cu.edu.cujae.structdb.dto;

public class FeeDTO implements AbstractDTO {
    private int id;
    private String fee;
    private double day_cost;

    public FeeDTO () {};
    public FeeDTO(int id,String fee, double day_cost){
        this.id = id;
        this.fee = fee;
        this.day_cost = day_cost;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getFee(){return this.fee;}

    public String setFee(){
        this.fee = fee;
        return null;
    }

    public double getDay_cost() {
        return day_cost;
    }
    public double setDay_cost() {
        this.day_cost = day_cost;
        return 0;
    }
}
