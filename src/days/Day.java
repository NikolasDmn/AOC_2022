package days;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Day {
    protected final String input;
    protected final String sample;
    protected final int dayNum;
    protected final String[] inputAnswer;

    protected final String[] sampleAnswer;

    public Day(int dayNum) {
        this(dayNum, "sample.txt", "input.txt");
    }

    public Day(int dayNum, String sample_path, String input_path) {
        this.dayNum = dayNum;
        this.sample = this.getFiles(sample_path);
        this.input = this.getFiles(input_path);
        this.sampleAnswer = this.getAnswer(this.sample);
        this.inputAnswer = this.getAnswer(this.input);
    }

    protected String[] getAnswer(String data) {
        return new String[]{"TBD", "TBD"};
    }

    private String getFiles(String path) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            if (inputStream != null) {
                return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        return String.format("%n------------------+=[ Day %02d ]=+------------------%n" + "                  -+= Part 1 =+-%n" + "    Answer of Sample:        %s%n" + "    Answer of Input :        %s%n" + "                  -+= Part 2 =+-%n" + "    Answer of Sample:        %s%n" + "    Answer of Input :        %s%n", this.dayNum, this.sampleAnswer[0], this.inputAnswer[0], this.sampleAnswer[1], this.inputAnswer[1]);
    }
}