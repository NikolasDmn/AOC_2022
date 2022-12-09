package days.day06;

import days.Day;

import java.util.HashSet;
import java.util.List;


public class Day06 extends Day {
    public Day06() {
        super(06, "sample.txt", "input.txt");
    }

    public Day06(String input_path, String sample_path) {
        super(06, input_path, sample_path);
    }

    public char[] parseInput(String input) {
        return input.toCharArray();
    }

//    if ((new HashSet<>(List.of(data).subList(i, i+length)).size() == length)) return i;
    public int getIndexUnique(int length,char[] data){
        for(int i =0;i<data.length-1-length;i++) {
            HashSet<Character> set =new HashSet<>();
            for(int a =0;a<length;a++) set.add(data[i+a]);
            if(set.size() == length) return i+length;
        }
        return -1;

    }
    public String part1(String input) {
        char[] data =parseInput(input);
        return String.valueOf(getIndexUnique(4,data));
    }

    public String part2(String input) {
        char[] data =parseInput(input);
        return String.valueOf(getIndexUnique(14,data));
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day06());
    }
}
