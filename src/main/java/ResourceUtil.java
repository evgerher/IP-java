import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class ResourceUtil implements AutoCloseable {
  private final InputStreamReader stream;
  private final char EOF_SIGNAL = '\uFFFF';
  private final char SPACE = ' ';

  private boolean EOF = false;

  public ResourceUtil(InputStream is) throws UnsupportedEncodingException {
    stream = new InputStreamReader(is, "UTF-8");
  }

  public String readContent() throws IOException {
    StringBuilder builder = new StringBuilder();
    char ch;
    while ((ch = (char) stream.read()) != EOF_SIGNAL) {
      builder.append(ch);
    }
    EOF = true;

    return builder.toString();
  }

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

  public boolean hasContent() {
    return !EOF;
  }

  public void close() throws IOException {
    stream.close();
  }
}
