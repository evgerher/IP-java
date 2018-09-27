package HW1.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

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

  /**
   * I have never tried methods with dynamic length :)
   * @param resources - series of resources
   * @return list of constructed ResourceUtil instances
   * @throws FactoryException in case of incorrect type
   * @throws IOException
   */
  public static List<ResourceUtil> createResourceUtils(Object ... resources) throws FactoryException, IOException {
    List<ResourceUtil> utils = new LinkedList<>();
    File tf;
    URL turl;
    for (Object b: resources) {
      if (b == null)
        throw new NullPointerException("Null object passed");
      if (b instanceof File) {
        tf = (File) b;
        utils.add(createResourceUtil(tf));
      } else if (b instanceof URL) {
        turl = (URL) b;
        utils.add(createResourceUtil(turl));
      } else {
        throw new FactoryException("Incorrect object passed");
      }
    }

    return utils;
  }

  public static class FactoryException extends RuntimeException {
    FactoryException(String reason) { super(reason); }
  }
}
