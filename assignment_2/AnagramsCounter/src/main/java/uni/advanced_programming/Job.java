package uni.advanced_programming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Arrays;

public class Job extends AJob<String, String>{

    /**
     * Absolute path of the file to analyze
     */
    private String absPath;

    public Job() {
    }

    public Job(String absPath) {
        this.absPath = absPath;
    }

    /**
     * Absolute path getter
     * @return absPath
     */
    public String getAbsPath() {
        return absPath;
    }

    /**
     * Set the absolute path of the file to analyze
     * @param absPath the absolute path of the file
     */
    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    /**
     * Transform a string in its ciao version (characters in alphabetic order and lower case letter)
     * @param s string to transform in ciao version
     * @return a string that is the ciao version of the input string
     */
    public static String toCiaoString(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * This function takes a file from the absolute path passed to the Job instance.
     * Then it takes the file lines (lines) and split it into words (flatMap), after that
     * filters the words according to the specific (length >= 4 and alphabetic string) (filter),
     * at the end create a new Pair instance with the ciao string and its original version in lowercase (map).
     * @return a stream containing the Pair instances in case of success, an empty stream if an exception arise.
     */
    @Override
    public Stream<Pair<String, String>> execute() {
        try {
            return Files.lines(Paths.get(absPath))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(word -> word.length() >= 4 && word.matches("^[A-Za-z]+"))
                    .map(word -> new Pair<>(toCiaoString(word), word.toLowerCase()));
        } catch (IOException e) {
            System.out.println("Invalid path "+absPath+". An error occurred opening file.");
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
