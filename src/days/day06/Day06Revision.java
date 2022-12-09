package days.day06;

import days.Day;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Day06Revision extends Day {
    public Day06Revision() {
        super(06, "sample.txt", "input.txt");
    }

    public Day06Revision(String input_path, String sample_path) {
        super(06, input_path, sample_path);
    }

    public List<Character> parseInput(String input) {
        return input.chars().mapToObj(chr -> (char) chr).collect(Collectors.toList());
    }

    public int getIndexUnique(int length,List<Character> data){
        for(int i =0;i<data.size()-length-1;i++) {
            if (data.subList(i,i+length).stream().distinct().count() == length) return i+length;
        }
        return -1;
    }
    public String part1(String input) {
        List<Character> data =parseInput(input);
        return String.valueOf(getIndexUnique(4,data));
    }

    public String part2(String input) {
        List<Character> data =parseInput(input);
        return String.valueOf(getIndexUnique(14,data));
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day06Revision());
    }
}
