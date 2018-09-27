package HW1.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple factory for code simplification
 */
public class ResourceUtilFactory {
  private ResourceUtilFactory() {}
  public static ResourceUtil createResourceUtil(URI url) throws IOException {
    return new ResourceUtil(url);
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
    URI turi;
    for (Object b: resources) {
      if (b == null)
        throw new NullPointerException("Null object passed");
      if (b instanceof URI) {
        turi = (URI) b;
        utils.add(createResourceUtil(turi));
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
