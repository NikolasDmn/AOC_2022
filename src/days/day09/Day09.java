package days.day09;

import days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Day09 extends Day {
    private class Pos{
        public int x=0,y=0;
        public Pos(){}
        public void up(){
            x++;
        }
        public void down(){
            x--;
        }
        public void right(){
            y++;
        }
        public void left(){
            y--;
        }

        public String toString() {
            return String.format("(%d,%d)",y,x);
        }
    }
    private class Rope{
        HashSet <String> tailLocations = new HashSet<>();
        private final Pos head = new Pos();
        private final Pos tail = new Pos();
        public Rope(){
        }
        private int distance(){
            return (Math.abs(head.x-tail.x) + Math.abs(head.y-tail.y));
        }
        public void move(char direction, int steps){
            switch(direction){
                case 'U':
                    goUp(steps);
                    break;
                case 'D':
                    goDown(steps);
                    break;
                case 'R':
                    goRight(steps);
                    break;
                case 'L':
                    goLeft(steps);
                    break;
                default:
                    System.out.println("Wrong direction");
            }
        }
        private void handleTail() {
            if(tail.x == head.x && distance()==2) {
                tail.y += head.y > tail.y ? 1 : -1;
                return;
            };
            if(tail.y == head.y && distance()==2) {
                tail.x += head.x > tail.x ? 1 : -1;
                return;
            }
            if(distance() !=3) return;
            tail.y += head.y > tail.y ? 1 : -1;
            tail.x += head.x > tail.x ? 1 : -1;
        }

        private void goUp(int steps) {
            for(int i = 0; i< steps; i++) {
                head.up();handleTail();
                addLocation();
            }
        }


        private void goDown(int steps) {
            for(int i = 0; i< steps; i++) {
                head.down();handleTail();
                addLocation();
            }
        }

        private void goRight(int steps) {
            for(int i = 0; i< steps; i++) {
                head.right();handleTail();
                addLocation();
            }

        }

        private void goLeft(int steps) {
            for(int i = 0; i< steps; i++) {
                head.left();handleTail();
                addLocation();
            }
        }

        private void addLocation() {
            tailLocations.add(String.valueOf(tail));
        }

        public HashSet<String> getLocations(){
            return tailLocations;
        }

    }
    public Day09() {
        super(9, "sample.txt", "input.txt");
    }

    public Day09(String input_path, String sample_path) {
        super(9, input_path, sample_path);
    }

    public List<String[]> parseInput(String input) {
        return input.lines().map(l -> l.split(" ")).collect(Collectors.toList());
    }

    public String part1(String input) {
        Rope rope = new Rope();
        List<String[]> data = parseInput(input);
        for(String[] entry: data){
            rope.move(entry[0].charAt(0), Integer.parseInt(entry[1]));
        }
        System.out.println(rope.getLocations());
        return String.valueOf(rope.getLocations().size());
    }

    public String part2(String input) {
        return "TBD";
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day09());
    }
}
