import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Class allows to operate on text files
 * Read entire file or word by word (space separated)
 */
public abstract class ResourceUtil implements AutoCloseable {
  // End of file on Win?
  private final char EOF_SIGNAL = '\uFFFF';
  private final char SPACE = ' ';
  private final InputStreamReader stream;

  // End Of File flag
  private boolean EOF = false;

  /**
   * Constructor for class ResourceUtil
   * Creates initial InputStreamReader with UTF-8 in order to operate with cyrillic symbols
   * @param is
   * @throws UnsupportedEncodingException if any
   */
  public ResourceUtil(InputStream is) throws UnsupportedEncodingException {
    stream = new InputStreamReader(is, "UTF-8");
  }

  /**
   * Method reads entire file
   * @return content of the file
   * @throws IOException if any
   */
  public String readContent() throws IOException {
    StringBuilder builder = new StringBuilder();
    char ch;
    while ((ch = (char) stream.read()) != EOF_SIGNAL) {
      builder.append(ch);
    }
    EOF = true;

    return builder.toString();
  }

  /**
   * Method read one word from a file, stops on next SPACE symbol or EOF
   * @return next word
   * @throws IOException if any
   */
  public String readWord() throws IOException {
    StringBuilder b = new StringBuilder();
    char ch;
    while ((ch = (char) stream.read()) != SPACE) {
      if (ch == EOF_SIGNAL) {
        EOF = true;
        break;
      }
      b.append(ch);
    }

    return b.toString();
  }

  /**
   *
   * @return status of the stream
   */
  public boolean hasContent() {
    return !EOF;
  }

  /**
   * Closes the stream
   * @throws IOException
   */
  public void close() throws IOException {
    stream.close();
  }
}
