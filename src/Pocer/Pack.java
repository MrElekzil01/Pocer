package Pocer;

import java.util.*;
import java.util.Random;

abstract public class Pack {
    private List<Cart> coloda=new ArrayList<Cart>();

    public List<Cart> getColoda() {
        return coloda;
    }

    public void mixColoda(){
        Random rand = new Random();
        Cart interimFirst;
        Cart interimLast;
        int random;
        for(int i=0;i<35;i++) {//Mix carts in Pack
            random=rand.nextInt(35-i);
            interimFirst=getColoda().get(i);
            interimLast=getColoda().get(i+1+random);
            getColoda().set(i,interimLast);
            getColoda().set(i+1+random,interimFirst);
        }
    }

}
