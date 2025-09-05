package uni.advanced_programming;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ConcreteEmitStrategy is a class that implements the AbstractEmitStrategy interface.
 * Following the Strategy Pattern Framework design, it is used to make concrete the abstract strategy defined by the
 * framework. In this version, it takes a path to a directory from input and extracts all the .txt files from this
 * directory to generate the stream of AJob
 */
public class ConcreteEmitStrategy implements AbstractEmitStrategy{

    public ConcreteEmitStrategy() {
    }

    /**
     * Implementation of emit method
     * @return a stream of AJob containing all the .txt file extracted from the directory
     */
    @Override
    public Stream<AJob> emit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter absolute path to directory with .txt files: ");
        String dirPath = scanner.nextLine();

        try {
            List<Path> txtFiles = Files.list(Paths.get(dirPath))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toList()); // collect first to avoid resource leak
            return txtFiles.stream()
                    .map(p -> new Job(p.toFile().getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
