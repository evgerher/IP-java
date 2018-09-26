import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

public class ResourceUtilTest {

  private Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = ResourceUtilTest.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }

  @Test
  public void fileTest() throws URISyntaxException, IOException {
    File input = getResourcePath("letter.txt").toFile();
    ResourceUtil util = new FileResourceUtil(input);
    String content;
    try (ResourceUtil.Resource res = util.getResource()) {
      content = res.readContent();
    }

    String expected = "Dear Evgeny!\n"
        + "\n"
        + "Unfortunately, due to technical problems Medical center can’t accept students on the dates specified earlier.\n"
        + "Medical examination has to be postponed. We will inform you in advance about the correct dates.\n"
        + "\n"
        + "Have a nice weekend!";
    assertEquals(expected, content);
  }

}
