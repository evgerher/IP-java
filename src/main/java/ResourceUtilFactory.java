import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Simple factory for code simplification
 */
public class ResourceUtilFactory {
  private ResourceUtilFactory() {}
  public static ResourceUtil createResourceUtil(File file) throws IOException {
    return new FileResourceUtil(file);
  }

  public static ResourceUtil createResourceUtil(URL url) throws IOException {
    return new WebResourceUtil(url);
  }
}
