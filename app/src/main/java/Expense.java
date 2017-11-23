/**
 * Created by Bpatel0967 on 11/23/2017.
 */

public class Expense {

    private  double amount;
    private  String location;
    private  String description;
    private  String category;

    public Expense(double amount, String location, String description, String category){
        this.amount = amount;
        this.location = location;
        this.description = description;
        this.category = category;
    }

    //getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
