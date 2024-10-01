package puzzles.clock;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.LinkedList;

public class Clock {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Clock hours start end");
        } else {
            int hours = Integer.parseInt(args[0]);
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);
            ClockConfig startClock = new ClockConfig(hours, start, start, end);
            ClockConfig endClock = new ClockConfig(hours, end, start, end);
            System.out.println("Hours: " + hours + ", Start: " + start + ", End: " + end);
            Solver clockSolver = new Solver(startClock);
            LinkedList<Configuration> path = new LinkedList<>(clockSolver.solve(startClock, endClock));
            System.out.println("Total configs: " + clockSolver.getTotal());
            System.out.println("Unique configs: " + clockSolver.getUnique());
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
