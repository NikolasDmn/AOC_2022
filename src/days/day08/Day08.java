package days.day08;

import days.Day;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day08 extends Day {
    public Day08() {
        super(8, "sample.txt", "input.txt");
    }

    public Day08(String input_path, String sample_path) {
        super(8, input_path, sample_path);
    }

    public List<List<Integer>> parseInput(String input) {
        List<List<Integer>> data = new ArrayList<>();
        input.lines().forEach(e -> {
            List<Integer> ln = new ArrayList<>();
            for(char chr: e.toCharArray()){
                ln.add(Integer.parseInt(String.valueOf(chr)));
            }
            data.add(ln);
        });
        return data;
    }
    private List<List<Integer>> data;
    public String part1(String input) {
        this.data = parseInput(input);
        int sum =0;
        for(int row=0; row<this.data.size();row++){
            for(int tree_index =0;tree_index<this.data.get(0).size();tree_index++){
                sum+= determineIfVisible(row,tree_index);
            }
        }
        return String.valueOf(sum);
    }

    private int determineIfVisible(int row, int treeIndex) {
//        if(row==0 || row==this.data.size()-1 || treeIndex==0 || treeIndex == this.data.get(row).size()-1) return 1;
        int tree = this.data.get(row).get(treeIndex);
        boolean isVisible =true;
        for(int i=row-1; i >=0; i--){
            if (tree <= this.data.get(i).get(treeIndex)) isVisible =false;
        }
        if(isVisible){
            return 1;
        }
        isVisible =true;
        for(int i =row+1;i<this.data.size();i++){

            if (tree <= this.data.get(i).get(treeIndex)) isVisible =false;
        }
        if(isVisible){
            return 1;
        }
        isVisible =true;
        for(int i=treeIndex-1; i >=0; i--){
            if (tree <= this.data.get(row).get(i)) isVisible =false;
        }if(isVisible){
            return 1;
        }
        isVisible =true;
        for(int i =treeIndex+1;i<this.data.size();i++){
            if (tree <= this.data.get(row).get(i)) return 0;
        }
        return isVisible? 1:0;
    }
    private int determineScenicScore(int row,int col){
        int score = 1;
        int tree = this.data.get(row).get(col);
        int sum=0;
        for(int i=row-1; i >=0; i--){
            sum++;
            if (tree <= this.data.get(i).get(col)) break;
        }
        score*=sum;
        sum=0;
        for(int i =row+1;i<this.data.size();i++){
            sum++;
            if (tree <= this.data.get(i).get(col)) break;
        }
        score*=sum;
        sum=0;
        for(int i=col-1; i >=0; i--){
            sum++;
            if (tree <= this.data.get(row).get(i)) break;
        }
        score*=sum;
        sum=0;
        for(int i =col+1;i<this.data.size();i++){
            sum++;
            if (tree <= this.data.get(row).get(i)) break;
        }
        score*=sum;
        return score;
    }
    public String part2(String input) {
        this.data = parseInput(input);
        int max =0;
        int rowMax =0;
        int treeMax=0;
        for(int row=0; row<this.data.size();row++){
            for(int tree_index =0;tree_index<this.data.get(0).size();tree_index++){
                int visible = determineScenicScore(row,tree_index);
                if(visible>max) {
                    max = visible;
                    rowMax=row;
                    treeMax =tree_index;
                };
            }
        }
        System.out.printf("%s at: %s,%s%n",max,rowMax,treeMax);
        return String.valueOf(max);
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day08());
    }
}
