package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Collection;

public class ClockConfig implements Configuration {
    private int totalHours;
    private int hour;
    private int start;
    private int end;
    public ClockConfig(int totalHours, int hour, int start, int end){
        this.hour = hour;
        this.totalHours = totalHours;
        this.start = start;
        this.end = end;
    }
    public int getTotalHours(){return this.totalHours;}
    public int getHour(){return this.hour;}
    public int getStart(){return start;}
    public int getEnd(){return this.end;}

    @Override
    public boolean isSolution() {
        return this.hour == this.end;
    }

    /**
     * Generates neighbors in order one hour earlier then one our later (ex 6 oclock neighbors 5 and 7)
     * @return ArrayList of 2 neighbors
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighbors = new ArrayList<>();
        int tHours = this.totalHours;
        int hour = this.hour;
        int start = this.start;
        int end = this.end;
        if(this.hour == 1){
            ClockConfig neighborOne = new ClockConfig(tHours, tHours, start, end);
            ClockConfig neighborTwo = new ClockConfig(tHours, hour +1, start, end);
            neighbors.add(neighborOne);
            neighbors.add(neighborTwo);
            return neighbors;
        } else if(this.hour == this.totalHours){
            ClockConfig neighborOne = new ClockConfig(tHours, hour - 1, start, end);
            ClockConfig neighborTwo = new ClockConfig(tHours, 1, start, end);
            neighbors.add(neighborOne);
            neighbors.add(neighborTwo);
            return neighbors;
        } else{
            ClockConfig neighborOne = new ClockConfig(tHours, hour - 1, start, end);
            ClockConfig neighborTwo = new ClockConfig(tHours, hour + 1, start, end);
            neighbors.add(neighborOne);
            neighbors.add(neighborTwo);
            return neighbors;
        }
    }

    /**
     * determines whether an object is equal to the current clock
     * @param other - the other object
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof ClockConfig otherClock){
            if(this.totalHours == otherClock.getTotalHours()){ //Check total hours
                if(this.hour == otherClock.getHour()){ //Check current hour
                    if(this.start == otherClock.getStart()){ //Check start hour
                        return this.end == otherClock.getEnd(); //Check end hour
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.hour;
    }

    @Override
    public String toString() {
        return String.valueOf(this.hour);
    }
}
