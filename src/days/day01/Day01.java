package days.day01;

import days.Day;

import java.util.ArrayList;
import java.util.Collections;


public class Day01 extends Day {
    public Day01() {
        super(01, "sample.txt", "input.txt");
    }

    public Day01(String input_path, String sample_path) {
        super(01, input_path, sample_path);
    }

    public ArrayList<Integer> parseInput(String input) {
        String[] lines = input.split("\\r?\\n");
        ArrayList<Integer> data = new ArrayList<>();
        int sum = 0;
        for (String line : lines) {
            try {
                sum += Integer.parseInt(line);
            } catch (Exception e) {
                data.add(Integer.valueOf(sum));
                sum = 0;
            }
        }
        data.add(Integer.valueOf(sum));
        return data;
    }

    public String part1(String input) {
        ArrayList<Integer> data = parseInput(input);


        return String.valueOf(Collections.max(data));
    }

    public String part2(String input) {
        ArrayList<Integer> data = parseInput(input);
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            int max = Collections.max(data);
            sum += max;
            data.remove(Integer.valueOf(max));
        }
        return String.valueOf(sum);
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }


    public static void main(String[] args) {
        System.out.println(new Day01());
    }
}
