package days.day04;

import days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Day04 extends Day {
    public Day04() {
        super(04, "sample.txt", "input.txt");
    }

    public Day04(String input_path, String sample_path) {
        super(04, input_path, sample_path);
    }

    public ArrayList<String[]> parseInput(String input) {
        ArrayList<String[]> data =new ArrayList<>();
        for(String line:input.split("\\r?\\n")){
            data.add(line.split(","));
        }
        return data;
    }

    public String part1(String input) {
        ArrayList<String[]> data = parseInput(input);
        int sum = 0;
        for(String[] line: data){
            int[] elem1 = Arrays.stream(line[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elem2 = Arrays.stream(line[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (elem1[0] <= elem2[0] && elem1[1]>=elem2[1]){
                sum+=1;
            }
            else if (elem2[0] <= elem1[0] && elem2[1]>=elem1[1]){
                sum+=1;
            }
        }
        return String.valueOf(sum);
    }

    public String part2(String input) {
        ArrayList<String[]> data = parseInput(input);
        Set<Integer> overlamps = new HashSet<>();
        int sum = 0;
        for(String[] line: data){
            int[] elem1 = Arrays.stream(line[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elem2 = Arrays.stream(line[1].split("-")).mapToInt(Integer::parseInt).toArray();
            for(int i = elem1[0]; i<=elem1[1];i++){
                if (i>=elem2[0] && i<=elem2[1]){
                    sum+=1;
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day04());
    }
}
