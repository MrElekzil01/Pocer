package Pocer;

import java.util.*;

public class Win {
    public Player win(List<Player> player)
    {
        double max=0;
        Player Winner=player.get(0);
        for (Player pl:player) {
            if(max<pl.getCombinationNumber()) {
                max=pl.getCombinationNumber();
                Winner=pl;
            }
            max = pl.getCombinationNumber();
        }
        return Winner;
    }
}
