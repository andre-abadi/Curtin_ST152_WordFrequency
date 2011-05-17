package wordfrequency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class LinkedStackTest {

    private LinkedStack<String> instance;

    public LinkedStackTest() {
    }

    @Before
    public void setUp() {
      instance = new LinkedStack<String>();
    }

    @After
    public void tearDown() {
      instance = null;
    }

  /**
   * Test of push method, of class LinkedStack.
   */
  @Test
  public void testPush() {
    System.out.println("push");
    String object = "mouse";
    instance.push(object);
    object = null;
    String expResult = "mouse";
    String result = instance.peek().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of pop method, of class LinkedStack.
   */
  @Test
  public void testPop() {
    System.out.println("pop");
    String object1 = "cat";
    String object2 = "mouse";
    //push them onto the stack
    instance.push(object1);
    instance.push(object2);
    object1 = null;
    object2 = null;
    //popping the top of the stack twice should give object1 (second last added)
    //and we'll use its weight as the expected result
    String expResult = "cat";
    instance.pop();
    String result = instance.pop().getValue();
    //now we'll check whether they're equal
    assertEquals(expResult, result);
  }

  /**
   * Test of peek method, of class LinkedStack.
   */
  @Test
  public void testPeek() {
    System.out.println("peek");
    String object1 = "beer";
    String object2 = "wine";
    //push them onto the stack
    instance.push(object1);
    instance.push(object2);
    object1 = null;
    object2 = null;
    //peeking at the top of the stack should give object2 (last added)
    String expResult = "wine";
    //and peek at the top object's weight
    String result = instance.peek().getValue();
    //now we'll check whether they're equal
    assertEquals(expResult, result);
  }

  /**
   * Test of isEmpty method, of class LinkedStack.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    boolean expResult = false;
    String object1 = "moose";
    instance.push(object1);
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    instance.pop();
    expResult = true;
    result = instance.isEmpty();
    assertEquals(expResult, result);
  }

}