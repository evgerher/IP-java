import java.io.IOException;
import java.net.URL;

public class WebResourceUtil extends ResourceUtil {
  public WebResourceUtil(URL url) throws IOException {
    super(url.openStream());
  }
}
