package days.day07;

import days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Comparator.*;

public class Day07 extends Day {
    public interface Item {
        int getSize();

    }
    private static class Fl implements Item {
        private final int size;
        private final String path;
        public int getSize(){
            return this.size;
        }
        public Fl(String path,int i){
            this.path = path;
            this.size = i;
        }

        public String toString() {
            return this.path;
        }
    }
    private static class Dir implements Item {
        private final ArrayList<Item> items = new ArrayList<>();
        private final String path;
        public int getSize(){
            int sum=0;
            for(Item item: this.items){
                sum+=item.getSize();
            }
            return sum;
        }
        public Dir(String path){
            this.path =path;
        }
        public void addItem(Item item){
            this.items.add(item);
        }
        public String getPath(){
            return this.path;
        }

        public String toString() {
            return this.path;
        }
    }
    public Day07() {
        super(07, "sample.txt", "input.txt");
    }

    public Day07(String input_path, String sample_path) {
        super(07, input_path, sample_path);
    }

    public ArrayList<ArrayList<String>> parseInput(String input) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String[] lines =input.split("\\r?\\n");
        for(String line:lines){
            data.add(new ArrayList<>((Arrays.asList(line.split(" ")))));
        }
        return data;
    }
    public Dir getWorkingDirectory(ArrayList<Dir> data){
        for(Dir dir: data){
            if(dir.getPath().equals(path)){
                return dir;
            }
        }
        return null;
    }
    private String path="/";
    private ArrayList<Dir> getFileTree(String input) {
        ArrayList<Dir> directories = new ArrayList<>();
        ArrayList<ArrayList<String>> data = parseInput(input);
        directories.add(new Dir("/"));
        for(ArrayList<String> line:data){
            if(line.get(0).equals("$")){
                handleUserInput(line);
                continue;
            }
            Dir directory = getWorkingDirectory(directories);
            if(directory==null){
                System.out.println("Something was wrong:\n" + path+"\n" + line);
                break;
            }
            if(line.get(0).equals("dir")){
                Dir newDir = new Dir(path + line.get(1) + "/");
                directories.add(newDir);
                directory.addItem(newDir);
            }
            else {
                int size = Integer.parseInt(line.get(0));
                directory.addItem(new Fl(path + line.get(1), size));
            }
        }
        return directories;
    }

    private void handleUserInput(ArrayList<String> line) {
        if(!line.get(1).equals("cd")) {
            return;
        }
        switch(line.get(2)){
            case "..":
                path = String.join("/",Arrays.copyOf(path.split("/"),path.split("/").length-1))+"/";
                break;
            case "/":
                path="/";
                break;
            default:
                path+= line.get(2)+"/";
        }
    }

    public String part1(String input) {
        ArrayList<Dir> directories = getFileTree(input);
        int sum=0;
        for(Dir dir:directories){
            int size=dir.getSize();
            if (size<=100000){
                sum+=size;
            }
        }
        return String.valueOf(sum);
    }

    public String part2(String input) {
        ArrayList<Dir> directories = getFileTree(input);
        directories.sort(comparing(Dir::getSize));
        int needed_space = 30000000-(70000000- directories.get(directories.size()-1).getSize());
        for(Dir dir:directories){
            if(dir.getSize() >= needed_space){
                return String.valueOf(dir.getSize());
            }
        }
        return "TBD";
    }

    @Override
    protected String[] getAnswer(String input) {
        return new String[]{this.part1(input), this.part2(input)};
    }

    public static void main(String[] args) {
        System.out.println(new Day07());
    }
}
