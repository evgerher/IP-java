package HW1.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;

/**
 * Class allows to operate on text files
 * Read entire file or word by word (space separated)
 * Contains subclass Resource - closeable object, all the operations are still on the ResourceUtil class
 */
public class ResourceUtil {
  // End of file on Win?
  private final String charSet = "UTF-8";
  private final int EOF_SIGNAL = -1;
  private final char SPACE = ' ';
  private final InputStream is;
  private final String description;

  // End Of File flag
  private boolean EOF = false;

  public String toString() {
    return this.description;
  }

  public ResourceUtil(URI uri) throws IOException {
    URL url = uri.toURL();
    this.is = url.openStream();
    this.description = url.toString();
  }

  public String getDescription() {
    return description;
  }

  public Resource getResource() throws UnsupportedEncodingException {
    return new Resource(is);
  }

  public class Resource implements AutoCloseable {
    final InputStreamReader stream;

    /**
     * Constructor for class HW1.utilResourceUtil
     * Creates initial InputStreamReader with UTF-8 in order to operate with cyrillic symbols
     * @param is
     * @throws UnsupportedEncodingException if any
     */
    private Resource(InputStream is) throws UnsupportedEncodingException {
      stream = new InputStreamReader(is, charSet);
    }

    /**
     * Method reads entire file
     * @return content of the file
     * @throws IOException if any
     */
    public String readContent() throws IOException {
      StringBuilder builder = new StringBuilder();
      int ch;
      while ((ch = stream.read()) != EOF_SIGNAL) {
        builder.append((char)ch);
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
      int ch;
      while ((ch = stream.read()) != SPACE) {
        if (ch == EOF_SIGNAL) {
          EOF = true;
          break;
        }
        b.append((char) ch);
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
}
