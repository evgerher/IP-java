package HW1;

import static org.junit.Assert.assertEquals;

import HW1.util.ResourceUtil;
import HW1.util.ResourceUtilFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class ResourceUtilFactoryTest {
  private Path getResourcePath(String resource) throws URISyntaxException {
    final URL url = ResourceUtilTest.class.getClassLoader().getResource(resource);
    return Paths.get(url.toURI()).toAbsolutePath();
  }

  @Test
  public void resourceListTest() throws URISyntaxException, IOException {
    final File a = getResourcePath("letter.txt").toFile();
    final URL url = new URL("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css");

    List<ResourceUtil> utils = ResourceUtilFactory.createResourceUtils(a.toURI(), url.toURI());
    assertEquals(2, utils.size());

    ResourceUtil first = utils.get(0);
    ResourceUtil second = utils.get(1);

    assertEquals("file:/C:/cygwin64/home/evger/JavaProjects/ip-java/hw1/target/test-classes/letter.txt", first.getDescription());
    assertEquals("https://cfl.dropboxstatic.com/static/css/sprites/web_sprites-vflv2MHAO.css", second.getDescription());
  }

  @Test(expected = ResourceUtilFactory.FactoryException.class)
  public void exceptionTest() throws URISyntaxException, IOException {
    final File a = getResourcePath("letter.txt").toFile();
    final Object b = new Object();
    List<ResourceUtil> utils = ResourceUtilFactory.createResourceUtils(a, b);
  }
}
