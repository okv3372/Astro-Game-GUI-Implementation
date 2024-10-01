package puzzles.dice;
/**
 * Represents the dice config
 * Name: Oliver Varney
 */

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class DiceConfig implements Configuration {
    String start;
    String end;
    String value;
    static HashMap<Integer, HashMap<Character, LinkedHashSet<Character>>> neighbors;
    public DiceConfig(String value, String start, String end){
        this.start = start;
        this.end = end;
        this.value = value;
    }

    /**
     * checks if its a solution
     * @return true or false if its solution
     */
    @Override
    public boolean isSolution() {
        return this.value.equals(this.end);
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighborList = new ArrayList<>();
        for(int i = 0; i<neighbors.size(); i++){
            StringBuilder neighbor = new StringBuilder(this.value);
            HashMap<Character, LinkedHashSet<Character>> neighborFaces = neighbors.get(i);
            for(Character c: neighborFaces.get(neighbor.charAt(i))){
                neighbor.setCharAt(i, c);
                DiceConfig neighborDice = new DiceConfig(neighbor.toString(), start, end);
                neighborList.add(neighborDice);
            }
        }
        return neighborList;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof DiceConfig dice){
            return dice.value.equals(this.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return this.value;
    }

    public void setNeighbors(HashMap<Integer, HashMap<Character, LinkedHashSet<Character>>> neighborMap){
        neighbors = neighborMap;
    }
}
