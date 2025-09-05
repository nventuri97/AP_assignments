package uni.advanced_programming;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcreteOutputStrategy implements AbstractOutputStrategy<String,String>{
    private final Path outputFile;

    public ConcreteOutputStrategy(String fileName) {
        this.outputFile = Path.of(fileName);
    }

    @Override
    public void output(Stream<Pair<String, List<String>>> data) {
        // Transform to "key -> count" lines
        List<String> lines = data
                .map(pair -> pair.getKey() + " -> " + pair.getValue().size())
                .collect(Collectors.toList());

        try {
            Files.write(outputFile, lines);
            System.out.println("Results written to " + outputFile.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
