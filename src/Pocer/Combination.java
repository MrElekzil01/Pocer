package Pocer;

import java.util.*;

public class Combination {
     public Combination(Player player){
         List<Integer> carts = new ArrayList<>(); //Список числовых номеров карт
         int uniqueElements=0; //Уникальные карты в руке
         for (Cart cart: player.getHand()) { //Извлекаем карты(номера)
             if(!carts.contains(cart.getWeightNumber()))
                 uniqueElements++;
             carts.add(cart.getWeightNumber());
         }
         Collections.sort(carts);//Сортируем по возрастанию
         if(uniqueElements==player.getHand().size()) {//стрит, стритфлеш, флеш (5 уникальных)
             int streetIterator=0;
             int fleshIterator=0;

             for(int i=0;i<player.getHand().size()-1;i++) {
                 if(player.getHand().get(i).getColor().equals(player.getHand().get(i+1).getColor()))
                 {
                     fleshIterator++;
                 }
                 if(carts.get(i)==(carts.get(i+1)+1))
                 {
                     streetIterator++;
                 }
             }
             if(fleshIterator==4 && streetIterator==4) {
                 player.setCombination("Стрит флеш");
                 player.setCombinationNumber(8);
             }
             else if(fleshIterator==4) {
                 player.setCombination("Флеш");
                 player.setCombinationNumber(5);
             }
             else if(streetIterator==4) {
                 player.setCombination("Стрит");
                 player.setCombinationNumber(4);
             }
         }
         int iterator=0;
         if(uniqueElements==2) {
             for(int i=0;i<player.getHand().size()-1;i++) {
                 if(carts.get(i)==carts.get(i+1)){
                     iterator++;
                 }
                 else if(iterator==2)
                 {
                     player.setCombination("Фулл хаус");
                     player.setCombinationNumber(6);
                     break;
                 }
                 else if(iterator==3)
                 {
                     player.setCombination("Каре");
                     player.setCombinationNumber(7);
                     break;
                 }
             }
         }
         iterator=0;
         if(uniqueElements==3) {
             for(int i=0;i<player.getHand().size()-1;i++) {
                 if(carts.get(i)==carts.get(i+1)) {
                     iterator++;
                 }
                 else if(iterator==1) {
                     player.setCombination("Две пары");
                     player.setCombinationNumber(2);
                 }
                 else if(iterator==2)
                 {
                     player.setCombination("Тройка");
                     player.setCombinationNumber(3);
                 }
             }
         }
        if(uniqueElements==4) {
            player.setCombination("Пара");
            player.setCombinationNumber(1);
        }
        if(player.getCombination().isEmpty())
        {
            for (Cart cart: player.getHand()) {
                if(cart.getWeightNumber()==carts.get(carts.size()-1)) {
                    player.setCombination("Старшая карта - "+cart.getWeight());
                    player.setCombinationNumber(cart.getWeightNumber()/100);
                }
            }

        }
     }
}
