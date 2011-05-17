package wordfrequency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class LinkTest {

    private Link<String> instance;

    public LinkTest() {
    }

    @Before
    public void setUp() {
      instance = new Link<String>("test");
    }

    @After
    public void tearDown() {
      instance = null;
    }

  /**
   * Test of getNext method, of class Link.
   */
  @Test
  public void testGetNext() {
    System.out.println("getNext");
    assertEquals(null, instance.getNext());
  }

  /**
   * Test of setNext method, of class Link.
   */
  @Test
  public void testSetNext() {
    System.out.println("setNext");
    Link<String> newNext = new Link<String>("test2");
    instance.setNext(newNext);
    assertEquals(newNext.getValue(),
            instance.getNext().getValue());
  }

  /**
   * Test of getValue method, of class Link.
   */
  @Test
  public void testGetValue() {
    System.out.println("getValue");
    String expResult = "test";
    String result = instance.getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of setValue method, of class Link.
   */
  @Test
  public void testSetValue() {
    System.out.println("setValue");
    instance.setValue("test3");
    assertEquals("test3", instance.getValue());
  }

}