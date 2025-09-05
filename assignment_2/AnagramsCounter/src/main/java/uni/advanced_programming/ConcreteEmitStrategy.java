package uni.advanced_programming;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcreteEmitStrategy implements AbstractEmitStrategy{
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
