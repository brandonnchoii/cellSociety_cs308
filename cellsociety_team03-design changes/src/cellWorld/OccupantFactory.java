package cellWorld;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import grid.Grid;
import grid.location.*;
import occupant.character.predatorprey.Fish;
import occupant.character.predatorprey.Shark;
import occupant.state.fire.Burning;
import occupant.state.fire.Empty;
import occupant.state.fire.Tree;
import occupant.state.gol.Alive;
import occupant.state.gol.Dead;

public class OccupantFactory {

    public OccupantFactory () {
    }

    public void create (String s, Grid<Location> g, Location l, Map<String, Double> map) {
        switch (s) {
        case "Fire":
            g.put(l, new Burning(g, l));
            break;
        case "Tree":
            g.put(l, new Tree(g, l, map.get("probability")));
            break;
        case "Empty":
            g.put(l, new Empty(g, l));
            break;
        case "Fish":
            int fishBreed = (int) Math.round(map.get("FishBreed"));
            g.put(l, new Fish(g, l, fishBreed));
            break;
        case "Shark":
            int sharkBreed = (int) Math.round(map.get("SharkBreed"));
            int sharkStarve = (int) Math.round(map.get("SharkStarve"));
            g.put(l, new Shark(g, l, sharkBreed, sharkStarve));
            break;
        case "Dead":
            g.put(l, new Alive(g, l));
            break;
        case "Alive":
            g.put(l, new Dead(g, l));
            break;
        }
    }

    public void validateParams (String title, Set<String> occSet, Map<String, Double> miscMap) {
        boolean isValid = false;
        switch (title) {
        case "Spreading of Fire":
            double p = miscMap.get("probability");
            isValid = (occSet.size() == 3 &&
                       occSet.containsAll(Arrays.asList("Fire", "Empty", "Tree")) && p > 0 && p < 100);
            break;
        case "Game of Life":
            isValid = (occSet.size() == 2 && occSet.containsAll(Arrays.asList("Dead", "Alive")));
            break;
        case "Predator and Prey":
            int sharkBreed = (int) Math.round(miscMap.get("SharkBreed"));
            int sharkStarve = (int) Math.round(miscMap.get("SharkStarve"));
            int fishBreed = (int) Math.round(miscMap.get("FishBreed"));
            isValid = (occSet.size() == 2 && occSet.containsAll(Arrays.asList("Shark", "Fish")) &&
                       sharkBreed > 0 && sharkStarve > 0 && fishBreed > 0);
            break;
        case "Segregation":
            isValid = true;
            break;
        }
    }
}
