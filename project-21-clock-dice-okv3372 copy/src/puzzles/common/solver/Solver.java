package puzzles.common.solver;

import java.util.*;

public class Solver {
    Configuration config;
    int total = 1;
    int unique = 0;
    public Solver(Configuration config){
        this.config = config;
    }

    private List<Configuration> constructPath(Map<Configuration,Configuration> predecessors,
                                            Configuration start, Configuration finish) {
        List<Configuration> path = new LinkedList<>();

        if(predecessors.containsKey(finish)) {
            Configuration currNode = finish;
            while (currNode != start) {
                path.addFirst(currNode);
                currNode = predecessors.get(currNode);
            }
            if(!path.contains(start)){
                path.addFirst(start);
            }
        }

        return path;
    }

    public Collection<Configuration> solve(Configuration start, Configuration finish){

        List<Configuration> queue = new LinkedList<>();
        queue.add(start);

        Map<Configuration, Configuration> predecessors = new HashMap<>();
        predecessors.put(start, start);

        while(!queue.isEmpty()){
            Configuration current = queue.removeFirst();
            if(current.isSolution()){
                break;
            }
            ArrayList<Configuration> neighborCoords = new ArrayList<>(current.getNeighbors());
            for(Configuration nbr: neighborCoords){
                total += 1;
                if(!predecessors.containsKey(nbr)){
                    predecessors.put(nbr, current);
                    queue.add(nbr);
                }
            }
        }
        unique += predecessors.size();
        return constructPath(predecessors, start, finish);
    }
    public int getUnique(){
        return unique;
    }
    public int getTotal(){
        return total;
    }

}
