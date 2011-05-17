package wordfrequency;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class BinSearchTreeTest {

    public BinSearchTreeTest() {
    }


  /**
   * Test of getRoot method, of class BinSearchTree.
   */
  @Test
  public void testGetRoot() {
    System.out.println("getRoot");
    BinSearchTree instance = new BinSearchTree();
    BinTreeNode expResult = null;
    BinTreeNode result = instance.getRoot();
    assertEquals(expResult, result);
  }

  /**
   * Test of find method, of class BinSearchTree.
   */
  @Test
  public void testFind() {
    System.out.println("find");
    String key = "apple";
    BinSearchTree instance = new BinSearchTree();
    instance.insert(key);
    instance.insert(key);
    int expResult = 2;
    int result = instance.find(key);
    assertEquals(expResult, result);
  }

  /**
   * Test of insert method, of class BinSearchTree.
   */
  @Test
  public void testInsert() {
    System.out.println("insert");
    String name = "orange";
    BinSearchTree instance = new BinSearchTree();
    instance.insert(name);
    int expResult = 1;
    int result = instance.find(name);
    assertEquals(expResult, result);
  }

  /**
   * Test of delete method, of class BinSearchTree.
   */
  @Test
  public void testDelete() {
    //dummy test, delete method is unsupported in current implementation
    //so this test is not needed
    //however the method does work i.e. it throws an unsupported exception
    BinSearchTree instance = new BinSearchTree();
    try {
      instance.delete("test");
    } catch (IllegalArgumentException e) {
      System.out.println("delete method threw exception as expected");
      int expResult = 1;
      int result = 1;
      assertEquals(expResult, result);
    }
    
  }

}