package wordfrequency;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class LinkedListTest {
  
    private LinkedList<String> instance;

    public LinkedListTest() {
    }

    @Before
    public void setUp() {
      instance = new LinkedList<String>();
      String object = "test1";
      instance.insertFirst(object);
      object = null;
      String object2 = "test2";
      instance.insertLast(object2);
      object2 = null;
      String object3 = "test3";
      instance.insertLast(object3);
      object3 = null;
    }

    @After
    public void tearDown() {
      instance = null;
    }

  /**
   * Test of isEmpty method, of class LinkedList.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of insertFirst method, of class LinkedList.
   */
  @Test
  public void testInsertFirst() {
    System.out.println("insertFirst");
    String object = "newFirst";
    instance.insertFirst(object);
    object = null;
    String expResult = "newFirst";
    String result = instance.getFirst().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of insertLast method, of class LinkedList.
   */
  @Test
  public void testInsertLast() {
    System.out.println("insertLast");
    String object = "newLast";
    instance.insertLast(object);
    object = null;
    String expResult = "newLast";
    String result = instance.getLast().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of getFirst method, of class LinkedList.
   */
  @Test
  public void testGetFirst() {
    System.out.println("getFirst");
    String expResult = "test1";
    String result = instance.getFirst().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLast method, of class LinkedList.
   */
  @Test
  public void testGetLast() {
    System.out.println("getLast");
    String expResult = "test3";
    String result = instance.getLast().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of deleteFirst method, of class LinkedList.
   */
  @Test
  public void testDeleteFirst() {
    System.out.println("deleteFirst");
    String expResult = "test1";
    String result = instance.deleteFirst().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of iterator method, of class LinkedList.
   */
  @Test
  public void testIterator() {
    System.out.println("iterator");
    //create an iterator to iterate through our test (instance) list
    Iterator<String> it = instance.iterator();
    //we will construct 2 arrays, one with expected values
    //the other with the values that the iterator pulled out
    //we will then compare the arrays and below shows that we expect the
    //comparison function to return true
    boolean expResult = true;
    //an array containing the correct weights of the OrePile Links
    //that were created in setUp()
    String[] expected = new String[3];
    expected[0] = "test1";
    expected[1] = "test2";
    expected[2] = "test3";
    //creates an array of length matching the number of links
    //created in setUp()
    String[] results = new String[3];
    //counter for dumping iterated link weight values into array
    int ii = 0;
    while (it.hasNext() == true) {
      //put the weight of the next link into the array of results
      results[ii] = it.next();
      //increment the array-dumping counter
      ii += 1;
    }
    //the array comparison function should return true and thereby match our
    //expected result of true
    //the iterator and predefine arrays should both at this point contain
    // {1000, 2000, 3000}
    assertEquals(expResult, Arrays.equals(expected, results));
  }
}