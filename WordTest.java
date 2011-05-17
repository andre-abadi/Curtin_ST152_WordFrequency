package wordfrequency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class WordTest {

    private Word instance;

    public WordTest() {
    }

    @Before
    public void setUp() {
      instance = new Word(27,"mouse");
    }

    @After
    public void tearDown() {
      instance = null;
    }

  /**
   * Test of getCount method, of class Word.
   */
  @Test
  public void testGetCount() {
    System.out.println("getCount");
    int expResult = 27;
    int result = instance.getCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of getName method, of class Word.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    String expResult = "mouse";
    String result = instance.getName();
    assertEquals(expResult, result);
  }

}