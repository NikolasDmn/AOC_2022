package days.day05;

import days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


public class Day05 extends Day {
    public Day05() {
        super(05, "sample.txt", "input.txt");
    }

    public Day05(String input_path, String sample_path) {
        super(05, input_path, sample_path);
    }
    public ArrayList<Stack<Character>> get_init_stack(String input){
        ArrayList<Stack<Character>> stacks = new ArrayList<>();
        String[] lines =  input.split("\\r?\\n");
        int max = (lines[0].length()+1)/4;
        int index = 1;
        while(stacks.size()<max){
            Stack<Character> stack = new Stack<>();
            for(int i=lines.length-1; i>=0 ;i--){
                char chr = lines[i].charAt(index);
                if(Character.isUpperCase(chr)){
                    stack.push(chr);
                }
            }
            stacks.add(stack);
            index+=4;
        }
        return stacks;
    }

    public int[][] get_moves(String input) {
        String[] lines = input.split("\\r?\\n");
        int[][] moves = new int[lines.length][3];
        for(int i =0; i<lines.length;i++){
            String[] arguments = lines[i].split(" ");
            for(int a =0;a<3;a++) {
                moves[i][a] = Integer.parseInt(arguments[a + a + 1]);
            }
        }
        return moves;
    }

    public String part1(String input) {
        ArrayList<Stack<Character>> stacks = get_init_stack(input.split("α")[0]);
        int[][] moves = get_moves(input.split("α")[1]);
        for (int[] move:moves){
            for(int i=0;i<move[0];i++){
                stacks.get(move[2]-1).push(stacks.get(move[1]-1).pop());
            }
            System.out.println(stacks);
        }
        StringBuilder rtrn = new StringBuilder();
        for(Stack<Character>stack: stacks){
            rtrn.append(stack.peek());
        }
        return rtrn.toString();
    }


    public String part2(String input) {
        ArrayList<Stack<Character>> stacks = get_init_stack(input.split("α")[0]);
        int[][] moves = get_moves(input.split("α")[1]);
        for (int[] move:moves){
            ArrayList<Character> moved_elements = new ArrayList<>();
            for(int i=0;i<move[0];i++){
                moved_elements.add(stacks.get(move[1]-1).pop());
            }
            Collections.reverse(moved_elements);
            for(Character chr:moved_elements){
                stacks.get(move[2]-1).push(chr);

            }
            System.out.println(stacks);
        }
        StringBuilder rtrn = new StringBuilder();
        for(Stack<Character>stack: stacks){
            rtrn.append(stack.peek());
        }
        return rtrn.toString();
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day05());
    }
}
