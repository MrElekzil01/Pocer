package Pocer;

import java.util.*;

public class Player {
    private List<Cart> hand =new ArrayList<>();
    private String combination="";
    private double combinationNumber; // 1 - пара, 2- 2 пары, 3 - тройка, 4- Стрит, 5 - Флеш, 6- Фул Хаус, 7- Карэ, 8 - Стрит флеш
    public List<Cart> getHand() {
        return hand;
    }
    public void setHand(ArrayList<Cart> hand) {
        this.hand = hand;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public double getCombinationNumber() {
        return combinationNumber;
    }

    public void setCombinationNumber(int combinationNumber) {
        this.combinationNumber = combinationNumber;
    }

    public void getCarts(Pack pack){
        if(pack.getColoda().size()>4)
            for(int i=0;i<5;i++)
            {
                hand.add(pack.getColoda().get(i));
                pack.getColoda().remove(i);
            }
    }
    public void switchCarts(ArrayList<Integer> cards,Pack pack) {
        Cart interim;
        for (int i:cards) {
            interim = hand.get(i);
            hand.remove(i);
            hand.add(pack.getColoda().get(0));
            pack.getColoda().remove(0);
            pack.getColoda().add(pack.getColoda().size(),interim);
        }
    }
}
