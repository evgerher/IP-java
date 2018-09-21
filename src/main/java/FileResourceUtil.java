import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class FileResourceUtil extends ResourceUtil {

  public FileResourceUtil(File src) throws FileNotFoundException, UnsupportedEncodingException {
    super(new FileInputStream(src));
  }
}
