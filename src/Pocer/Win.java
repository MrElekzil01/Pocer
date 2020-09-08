package Pocer;

import java.util.*;

public class Win {
    public int win(List<Player> player,StringBuilder string)
    {
        double max=0;
        boolean isDraw=false;
        Player winner=player.get(0);
        max = winner.getCombinationNumber();
        for (Player pl:player) {
            if(max==pl.getCombinationNumber()&&winner!=pl) {
                isDraw=true;
            }
            if(max<pl.getCombinationNumber()) {
                max=pl.getCombinationNumber();
                winner=pl;
                isDraw=false;
            }
        }
        if(isDraw){
            string.append("Ничья");
        }
        int win = player.indexOf(winner);
        return win;
    }
}
