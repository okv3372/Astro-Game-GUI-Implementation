package puzzles.dice;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dice {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Dice start end die1 die2...");
        } else {
            String start = args[0];
            String end = args[1];
            LinkedHashSet<DiceConfig> diceList = new LinkedHashSet<>();
            HashMap<Integer, HashMap<Character, LinkedHashSet<Character>>> diceNeighbors = new HashMap<>(); //HASHMAP DICE-SIZE: FACE: NEIGHBORS
            for(int i = 2; i < args.length; i++){
                System.out.println("Die #" + (i-2) + ": File: die-" + args[i] + ".txt, Faces: " + args[i]);
                try (BufferedReader reader = new BufferedReader(new FileReader("die-"+args[i]+".txt"))) {
                    String line;
                    reader.readLine();
                    reader.readLine();
                    HashMap<Character, LinkedHashSet<Character>> neighborMap = new HashMap<>();//HASHMAP FACE: NEIGHBORS FOR INDIVIDUAL DICE USED IN THE PUZZLE
                    while((line = reader.readLine()) != null){ //READS IN THE DICE FILE FOR EACH DICE, MAKES NEIGHBOR HASHMAP FOR INDIVIDUAL DICE
                        line = line.replaceAll("\\s+", "");
                        char[] splitLine = line.toCharArray();
                        neighborMap.put(splitLine[0], new LinkedHashSet<>());
                        System.out.print("\t" + splitLine[0] + "=");
                        for(int j = 1; j < splitLine.length; j++){
                            neighborMap.get(splitLine[0]).add(splitLine[j]);
                        }
                        System.out.print(neighborMap.get(splitLine[0]) + "\n");
                    }
                    diceNeighbors.put(i-2, neighborMap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            DiceConfig dice = new DiceConfig(start, start, end);
            dice.setNeighbors(diceNeighbors);
            System.out.println("Start: " + start + ", End: " + end);
            Solver diceSolver = new Solver(dice);
            DiceConfig startDice = new DiceConfig(start, start, end);
            DiceConfig endDice = new DiceConfig(end, start, end);
            LinkedList<Configuration> path = new LinkedList<>(diceSolver.solve(startDice, endDice));
            System.out.println("Total configs: " + diceSolver.getTotal());
            System.out.println("Unique configs: " + diceSolver.getUnique());
            int step = 0;
            if(path.isEmpty()){
                System.out.println("No solution!");
            }else {
                for (Configuration config : path) {
                    System.out.println("Step " + step + ": " + config.toString());
                    step += 1;
                }
            }
        }
    }
}