package Pocer;

public class Cart {
    private String weight; //вес (пятёрка, девятка, валет и т.д.)
    private String color; //масть
    private int weightNumber; //вес, выраженный в виде числа

    public int getWeightNumber() {
        return weightNumber;
    }

    public void setWeightNumber(int weightNumber) {
        this.weightNumber = weightNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public  String getColor(){
            return color;
    }

    public void setColor(String color){
        this.color=color;
    }
}
