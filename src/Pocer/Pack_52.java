package Pocer;

import java.util.*;

public class Pack_52 extends Pack{
    private List<String> colors = new ArrayList<String>(Arrays.asList("пик","крестей","бубей","червей"));
    private List<String> wheights = new ArrayList<String>(Arrays.asList("двойка","тройка","четвёрка","пятёрка","шестёрка", "семёрка", "восьмёрка",
            "девятка", "десятка", "валет", "дама", "король", "туз"));
    private List<Integer> wheightsNumber = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14));
    public Pack_52(){
        int colorNumber=0;
        for(int i=0;i<13;i++) //Create Pack of carts
        {
            createCards(i,colorNumber);
            createCards(i,colorNumber+1);
            createCards(i,colorNumber+2);
            createCards(i,colorNumber+3);
        }
        mixColoda();
    }
    private void createCards(int i,int y) {
        Cart cart = new Cart();
        cart.setWeight(wheights.get(i));
        cart.setColor(colors.get(y));
        cart.setWeightNumber(wheightsNumber.get(i));
        getColoda().add(cart);
    }
}
