import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ResourceFactory {
  private ResourceFactory() {}
  public static ResourceUtil createResourceUtil(File file) throws IOException {
    return new FileResourceUtil(file);
  }

  public static ResourceUtil createResourceUtil(URL url) throws IOException {
    return new WebResourceUtil(url);
  }
}
