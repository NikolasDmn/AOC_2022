package days.day09;

import days.Day;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Day09 extends Day {

    public Day09() {
        super(9, "sample.txt", "input.txt");
    }

    public Day09(String input_path, String sample_path) {
        super(9, input_path, sample_path);
    }

    public List<String[]> parseInput(String input) {
        return input.lines().map(l -> l.split(" ")).collect(Collectors.toList());
    }
    public static class Point{
        public int x = 0;
        public int y = 0;
        public Point(){}

        @Override
        public String toString() {
            return String.format("(%d,%d)",x,y);
        }
    }
    public void shift(String direction,Point point){
        switch (direction){
            case "U":
                point.y++;
                break;
            case "D":
                point.y--;
                break;
            case "R":
                point.x++;
                break;
            case "L":
                point.x--;
                break;
            default:
                System.out.println("Incorrect direction: " + direction);
        }
    }
    public int getPlaces(List<String[]> commands, int knotAmount){
        Point[] knots = new Point[knotAmount];
        for(int i = 0;i<knotAmount;i++) knots[i] = new Point();
        HashSet<String> positions = new HashSet<>();
        for(String[] command: commands){
            String direction = command[0];
            int steps = Integer.parseInt(command[1]);
            for(int i =0;i<steps;i++){
                shift(direction,knots[0]);
                moveTail(knots);
                positions.add(String.valueOf(knots[knots.length-1]));
            }
        }

        return positions.size();
    }
    public static float getTailStep(int val) {
        return Math.max(-1, Math.min(1, val));
    }
    private void moveTail(Point[] knots) {
        for(int i = 1;i<knots.length;i++){
            Point prev = knots[i-1];
            Point current = knots[i];
            if (determineMove(prev,current)){
                current.x += getTailStep(prev.x-current.x);
                current.y += getTailStep(prev.y-current.y);
            }
        }
    }

    private boolean determineMove(Point p1, Point p2) {
        return Math.abs(p1.x-p2.x) > 1 || Math.abs(p1.y-p2.y)>1;
    }

    public String part1(String input) {
       return String.valueOf(getPlaces(parseInput(input),2));
    }


    public String part2(String input) {
        return String.valueOf(getPlaces(parseInput(input),10));

    }
    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day09());
    }
}
