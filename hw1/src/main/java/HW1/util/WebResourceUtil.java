package HW1.util;

import java.io.IOException;
import java.net.URL;

/**
 * Class wrapper for Web resources
 */
public class WebResourceUtil extends ResourceUtil {
  public WebResourceUtil(URL url) throws IOException {
    super(url.toString(), url.openStream());
  }
}
