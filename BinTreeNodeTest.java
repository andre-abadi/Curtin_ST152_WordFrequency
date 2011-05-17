package wordfrequency;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class BinTreeNodeTest {
    public BinTreeNodeTest() {
    }


    private BinTreeNode instance;

    @Before
    public void setUp() {
      instance = new BinTreeNode("apple");
      BinTreeNode second = new BinTreeNode("banana");
      BinTreeNode third = new BinTreeNode("orange");
      instance.setLeftChild(second);
      instance.setRightChild(third);
    }

    @After
    public void tearDown() {
      instance = null;
    }

  /**
   * Test of getKey method, of class BinTreeNode.
   */
  @Test
  public void testGetKey() {
    System.out.println("getKey");
    String expResult = "apple";
    String result = instance.getKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCount method, of class BinTreeNode.
   */
  @Test
  public void testGetCount() {
    System.out.println("getCount");
    int expResult = 1;
    int result = instance.getCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCount method, of class BinTreeNode.
   */
  @Test
  public void testSetCount() {
    System.out.println("setCount");
    int newCount = 2;
    instance.setCount(newCount);
    int expResult = 2;
    int result = instance.getCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLeftChild method, of class BinTreeNode.
   */
  @Test
  public void testGetLeftChild() {
    System.out.println("getLeftChild");
    String expResult = "banana";
    String result = instance.getLeftChild().getKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getRightChild method, of class BinTreeNode.
   */
  @Test
  public void testGetRightChild() {
    System.out.println("getRightChild");
    String expResult = "orange";
    String result = instance.getRightChild().getKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of setLeftChild method, of class BinTreeNode.
   */
  @Test
  public void testSetLeftChild() {
    System.out.println("setLeftChild");
    BinTreeNode newChild = new BinTreeNode("guava");
    instance.setLeftChild(newChild);
    String expResult = "guava";
    String result = instance.getLeftChild().getKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of setRightChild method, of class BinTreeNode.
   */
  @Test
  public void testSetRightChild() {
    System.out.println("setRightChild");
    BinTreeNode newChild = new BinTreeNode("mango");
    instance.setLeftChild(newChild);
    String expResult = "mango";
    String result = instance.getLeftChild().getKey();
    assertEquals(expResult, result);
  }

}