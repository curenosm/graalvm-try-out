import org.graalvm.polyglot.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static java.lang.System.out;


public class Main {

  public static void main(String[] args) {

    String venvExePath = Main.class
      .getClassLoader()
      .getResource(Paths.get("graal-env", "bin", "graalpy").toString())
      .getPath();

    out.println("\n\n\n");

    try (var context = Context.newBuilder()
          .out(out)
          .allowAllAccess(true)
          .option("python.Executable", venvExePath)
          .build()) {
      
      // This line is necessary since it loads the dependencies
      context.eval("python", "import site");

      var file = Path.of("main.py");
      try (var lines = Files.lines(file)) {
        context.eval("python", 
          lines
            .peek(out::println)
            .collect(Collectors.joining("\n")));
      } catch (Exception e) {
        e.printStackTrace();
      }

      out.println("\n\n\n");

      file = Path.of("main.js");
      try (var lines = Files.lines(file)) {
        context.eval("js", 
          lines
            .peek(out::println)
            .collect(Collectors.joining("\n")));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
