package days.day02;

import days.Day;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day02Revision extends Day {
    public Day02Revision() {
        super(02, "sample.txt", "input.txt");
    }

    public Day02Revision(String input_path, String sample_path) {
        super(02, input_path, sample_path);
    }

    public Stream<int[]> parseInput(String input) {
        return input.lines().map(ln -> new int[]{ln.charAt(0) - 'A',ln.charAt(2)-'X'});
    }

    public String part1(String input) {
        return "TBD";
//        return String.valueOf(parseInput(input).mapToInt(i-> 3*((4+i[1]-i[0]%3)) + i[1]+1).sum());
    }

    public String part2(String input) {
        return String.valueOf(parseInput(input).mapToInt(i-> 3*i[1]+(i[0]+i[1]+2)%3+1).sum());
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {System.out.println(new Day02());
        System.out.println(new Day02Revision());
    }
}
