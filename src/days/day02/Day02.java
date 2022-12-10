package days.day02;

import days.Day;



public class Day02 extends Day {
    public Day02() {
        super(02, "sample.txt", "input.txt");
    }

    public Day02(String input_path, String sample_path) {
        super(02, input_path, sample_path);
    }

    public int[][] parseInput(String input) {

        String[] lines = input.split("\\r?\\n");
        int[][] data = new int[lines.length][2];
        for(int i =0; i<lines.length;i++){
            data[i][0] = lines[i].charAt(0)-'A';
            data[i][1] = lines[i].charAt(2)-'X';
        }
        return data;
    }

    public String part1(String input) {
        int[][] data = parseInput(input);
        int score = 0;
        for(int[] game:data){
            score +=(game[1]+1);
            if (game[0] ==  game[1]){
                score += 3;
            }
            else if((game[0] + 1)%3 ==game[1]) {
                score+=6;
            }
        }
        return String.valueOf(score);
    }

    public String part2(String input) {
        int[][] data = parseInput(input);
        int score = 0;
        for(int[] game:data){
            score +=3*(game[1]);
//            This equation works by first getting the value of the opponent
//            Then, by doing figuring out which outcome we want, we can add
//            a specific number to our opponent to get the correct move
            score += (game[0] + game[1] +2)%3+1;
        }
        return String.valueOf(score);
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day02());
    }
}
