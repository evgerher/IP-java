import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Class wrapper for File resources
 */
public class FileResourceUtil extends ResourceUtil {

  public FileResourceUtil(File src) throws FileNotFoundException, UnsupportedEncodingException {
    super(src.getName(), new FileInputStream(src));
  }
}
