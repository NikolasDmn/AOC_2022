package days.day03;

import days.Day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Day03 extends Day {
    public Day03() {
        super(03, "sample.txt", "input.txt");
    }

    public Day03(String input_path, String sample_path) {
        super(03, input_path, sample_path);
    }

    public String[] parseInput(String input) {
        return input.split("\\r?\\n");
    }
    private Set<Character> findCommon(char[] firstCompartment, char[] secondCompartment){
        Set<Character> firstCompartnemntSet = new HashSet<>();
        Set<Character> secondCompartnemntSet = new HashSet<>();
        for(char chr:firstCompartment){
            firstCompartnemntSet.add(chr);
        }
        for(char chr:secondCompartment){
            secondCompartnemntSet.add(chr);
        }
        firstCompartnemntSet.retainAll(secondCompartnemntSet);
        return firstCompartnemntSet;
    }
    private int getPriority(char chr){
        if (Character.isUpperCase(chr)){
            return chr-'A'+27;
        }
        return chr-'a'+1;
    }
    public String part1(String input) {
        int sum =0;
        String[] data =parseInput(input);
        for(String line: data){
            char[] chars = line.toCharArray();
            Set<Character> commonBadges = findCommon(Arrays.copyOfRange(chars, 0, chars.length/2),Arrays.copyOfRange(chars, chars.length/2, chars.length));
            for(Character common: commonBadges){
                sum+=getPriority(common);
            }
        }
        return String.valueOf(sum);

    }

    public String part2(String input) {
        String[] data = parseInput(input);
        int sum=0;
        for(int i =0; i<data.length;i+=3){
            Set<Character> firstSecondElf = findCommon(data[i].toCharArray(),data[i+1].toCharArray());
            Set<Character> secondThirdElf = findCommon(data[i+1].toCharArray(),data[i+2].toCharArray());
            firstSecondElf.retainAll(secondThirdElf);
            Character badge = firstSecondElf.iterator().next();
            sum += getPriority(badge);

        }
        return String.valueOf(sum);
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day03());
    }
}
