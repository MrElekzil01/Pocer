package Pocer;

import java.util.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Player winPlayer;
     Pack_36 pa = new Pack_36();
     int numberPlayers = 4;
     for (int i=0;i<numberPlayers;i++) {
         Player player = new Player();
         player.getCarts(pa);
         players.add(player);
     }
        for (int i=0;i<numberPlayers;i++) {
            Combination comb = new Combination(players.get(i));
        }
        System.out.println("Ваши карты:");
        for (Cart cart:players.get(0).getHand()) {
            System.out.println(cart.getWeight()+" "+cart.getColor());
        }
        System.out.println(players.get(0).getCombination());
        Win wins = new Win();
        winPlayer=wins.win(players);
        System.out.println();
        System.out.println("Победитель:");
        for (Cart cart:winPlayer.getHand()) {
            System.out.println(cart.getWeight()+" "+cart.getColor());
        }
     System.out.println(winPlayer.getCombination());
    }
}
