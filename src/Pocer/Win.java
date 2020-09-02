package Pocer;

import java.util.*;

public class Win {
    public Player win(List<Player> player,StringBuilder string)
    {
        double max=0;
        boolean isDraw=false;
        Player Winner=player.get(0);
        max = Winner.getCombinationNumber();
        for (Player pl:player) {
            if(max==pl.getCombinationNumber()&&Winner!=pl) {
                isDraw=true;
            }
            if(max<pl.getCombinationNumber()) {
                max=pl.getCombinationNumber();
                Winner=pl;
                isDraw=false;
            }
        }
        if(isDraw){
            string.append("Ничья");
        }
        return Winner;
    }
}
