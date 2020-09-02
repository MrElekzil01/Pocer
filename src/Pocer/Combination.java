package Pocer;

import java.util.*;

public class Combination {
    private List<Integer> carts = new ArrayList<>(); //список числовых номеров карт
    private int nonUniqueCartNumber;// используется для корректоного вывода пар, троек и каре
     public Combination(Player player){
         int uniqueElements=0; //уникальные карты в руке
         for (Cart cart: player.getHand()) { //извлекаем карты(номера)
             if(!carts.contains(cart.getWeightNumber()))
                 uniqueElements++;
             else
                 nonUniqueCartNumber = cart.getWeightNumber();
             carts.add(cart.getWeightNumber());
         }
         Collections.sort(carts);//сортируем по возрастанию
         if(uniqueElements==player.getHand().size()) {//стрит, стритфлеш, флеш (5 уникальных)
             int streetIterator=0;
             int fleshIterator=0;

             for(int i=0;i<player.getHand().size()-1;i++) {
                 if(player.getHand().get(i).getColor().equals(player.getHand().get(i+1).getColor()))
                 {
                     fleshIterator++;
                 }
                 if((carts.get(i)+1)==carts.get(i+1))
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
                 else if(iterator==2||iterator==1) {
                     player.setCombination("Фулл хаус");
                     player.setCombinationNumber(6);
                     break;
                 }
                 else if(iterator==3) {
                     player.setCombination("Каре "+identifyCart(player,nonUniqueCartNumber));
                     player.setCombinationNumber(7 + ((double)nonUniqueCartNumber)/100);
                     break;
                 }
                if(iterator==3&&i==player.getHand().size()-2) {
                    player.setCombination("Каре "+identifyCart(player,nonUniqueCartNumber));
                    player.setCombinationNumber(7 + ((double)nonUniqueCartNumber)/100);
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
                     break;
                 }
                 else if(iterator==2)
                 {
                     player.setCombination("Тройка "+identifyCart(player,nonUniqueCartNumber));
                     player.setCombinationNumber(3 + ((double)nonUniqueCartNumber)/100 );
                     break;
                 }
                 if(iterator==2&&i==player.getHand().size()-2) {
                     player.setCombination("Тройка "+identifyCart(player,nonUniqueCartNumber));
                     player.setCombinationNumber(3 + ((double)nonUniqueCartNumber)/100 );
                 }
             }
         }
        if(uniqueElements==4) {
            player.setCombination("Пара "+identifyCart(player,nonUniqueCartNumber));
            player.setCombinationNumber(1+((double)(nonUniqueCartNumber)/100));
        }
        if(player.getCombination().isEmpty())
        {
            for (Cart cart: player.getHand()) {
                if(cart.getWeightNumber()==carts.get(carts.size()-1)) {
                    player.setCombination("Старшая карта - "+cart.getWeight());
                    player.setCombinationNumber(((double)cart.getWeightNumber())/100);
                }
            }

        }
     }

     private String identifyCart(Player player,int iterator){
         StringBuilder cartName=new StringBuilder();
         for (Cart cart: player.getHand()) {
             if(cart.getWeightNumber()==iterator) {
                 cartName.append(cart.getWeight());
                 break;
             }
         }
         switch (cartName.toString()){
             case "валет":
             {
                 cartName.deleteCharAt(3);
                 cartName.insert(cartName.length()-1,"ь");
                 cartName.append("ов");
                break;
             }
             case "дама":
             {
                 cartName.deleteCharAt(3);
                 break;
             }
             case "король":
             {
                 cartName.deleteCharAt(5);
                 cartName.append("ей");
                 break;
             }
             case "туз":
             {
                 cartName.append("ов");
                 break;
             }
             default:
             {
                 cartName=cartName.deleteCharAt(cartName.length()-1);
                 cartName=cartName.insert(cartName.length()-1,"о");
                 break;
             }
         }
         return cartName.toString();
     }
}
