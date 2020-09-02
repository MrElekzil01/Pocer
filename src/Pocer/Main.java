package Pocer;

import java.util.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Player winPlayer;
     Pack_36 pa = new Pack_36();
     int numberPlayers = 3;
     for (int i=0;i<numberPlayers;i++) {
         Player player = new Player();
         player.getCarts(pa);
         players.add(player);
     }
        for (int i=0;i<numberPlayers;i++) {
            Combination comb = new Combination(players.get(i));
        }
        for (int i=0;i<numberPlayers;i++) {
            if(i==0){
                System.out.println("Ваши карты:");
            }else{
                System.out.println("Карты "+(i+1)+"-того игрока");
            }
            for (Cart cart:players.get(i).getHand()) {
                System.out.println(cart.getWeight()+" "+cart.getColor());
            }
            System.out.println();
            System.out.println(players.get(i).getCombination());
            System.out.println();
        }
        Win wins = new Win();
        StringBuilder draw = new StringBuilder();
        winPlayer=wins.win(players,draw);
        if(!draw.toString().isEmpty()) {
            System.out.println(draw.toString());
        }
        else {
            System.out.println("Победитель:");
            for (Cart cart:winPlayer.getHand()) {
                System.out.println(cart.getWeight()+" "+cart.getColor());
            }
            System.out.println();
            System.out.println(winPlayer.getCombination());
        }
    }
}
