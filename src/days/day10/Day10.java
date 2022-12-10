package days.day10;

import days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day10 extends Day {
    public Day10() {
        super(10, "sample.txt", "input.txt");
    }

    public Day10(String input_path, String sample_path) {
        super(10, input_path, sample_path);
    }

    public List<String[]> parseInput(String input) {
        return input.lines().map(ln -> ln.split(" ")).collect(Collectors.toList());
    }
    public int determineCycle(String command){
        switch (command){
            case "addx": return 2;
            case "noop": return 1;
            default: return 1;
        }
    }
    public String part1(String input) {
        int cycle = 0;
        int registerX=0;
        int sum = 0;
        List<Integer> stoppingPoints = Arrays.asList(20,60,100,140,180,220);

        for (String[] command: parseInput(input)){
            if (command.length>1)registerX++;
            for(int i =0;i<determineCycle(command[0]);i++){
                cycle++;
                if(stoppingPoints.contains(cycle)) {
                    sum += registerX * cycle;
                }
            }
            if (command.length>1) registerX+= Integer.parseInt(command[1])-1;
        }
        return String.valueOf(sum);
    }
    private String[][] getScreen(){
        String[][] array = new String[6][40];
        for(int i=0;i<array.length;i++){
            for(int a=0;a<array[i].length;a++){
                array[i][a] = ".";
            }
        }
        return array;
    }
    public String part2(String input) {
        String[][] screen = getScreen();
        List<String[]> commands = parseInput(input);
        int cycle = 0;
        int x = 0;
        for(String[] command: commands){
            int cycleLength = determineCycle(command[0]);
            if(cycleLength == 1) {
                draw(screen, x, cycle);
                cycle+=1;
               continue;
            }
            x+=1;
            draw(screen, x, cycle );
            cycle+=1;
            draw(screen, x, cycle);
            cycle+=1;
            x += Integer.parseInt(command[1])-1;
        }
        System.out.println(print(screen));
        return print(screen);
    }
    private String print(String[][] screen){
        String s = "\n";
        for(String[] line:screen){
            for(String pixel:line){
                s+=pixel;
            }
            s+="\n";
        }
        return s;
    }
    public void draw(String[][] screen, int x, int y){
        if (Math.abs(y%40-x)<2) screen[y/40][y%40] = "#";
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day10());
    }
}
