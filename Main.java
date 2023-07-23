import org.graalvm.polyglot.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        
        try (Context context = Context.create()) {

            var file = Path.of("main.py");
            try (var lines = Files.lines(file)) {
                String script = lines.collect(Collectors.joining("\n"));
                System.out.println(script);
                
                context.eval("python", script);
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = Path.of("main.js");
            try (var lines = Files.lines(file)) {
                String script = lines.collect(Collectors.joining("\n"));
                System.out.println(script);
                
                context.eval("js", script);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
