package wordfrequency;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class WordFrequencyTest {

    public WordFrequencyTest() {
    }

  /**
   * Test of processDir method, of class WordFrequency.
   */
  @Test
  public void testProcessDir() {
    System.out.println("processDir part 1");
    WordFrequency instance = new WordFrequency();
    String dir = "C:\\My Dropbox\\dev\\WordFrequency\\src\\wordfrequency\\ownTest";
    Word[] words = instance.processDir(dir);
    String expResult = "two";
    String result = words[0].getName();
    assertEquals(expResult,result);

    System.out.println("processDir part 2");
    instance = new WordFrequency();
    dir = "C:\\My Dropbox\\dev\\WordFrequency\\src\\wordfrequency\\ownTest2";
    words = instance.processDir(dir);
    expResult = "australian";
    result = words[163].getName();
    assertEquals(expResult,result);

    System.out.println("processDir part 3 - the wild");
    instance = new WordFrequency();
    dir = "C:\\My Dropbox\\dev\\WordFrequency\\src\\wordfrequency\\corpusDocs";
    words = instance.processDir(dir);
    expResult = "rockefeller";
    result = words[2491].getName();
    assertEquals(expResult,result);


  }


  /**
   * Test of mergeSort method, of class WordFrequency.
   */
  @Test
  public void testMergeSort() {
    System.out.println("mergeSort");
    Word[] words = new Word[1];
    words[0] = new Word(1,"zebra");
    WordFrequency instance = new WordFrequency();
    instance.mergeSort(words);
    String expResult = "zebra";
    String result = words[0].getName();
    assertEquals(expResult,result);

    System.out.println("");
    System.out.println("mergeSort 2");
    words = new Word[2];
    words[0] = new Word(1,"zebra");
    words[1] = new Word(1,"badger");
    instance.mergeSort(words);
    expResult = "badger";
    result = words[0].getName();
    assertEquals(expResult,result);
    expResult = "zebra";
    result = words[1].getName();
    assertEquals(expResult,result);

    System.out.println("");
    System.out.println("mergeSort 3");
    words = new Word[3];
    words[0] = new Word(5,"badger");
    words[1] = new Word(4,"platypus");
    words[2] = new Word(4,"cantelope");
    instance.mergeSort(words);
    expResult = "cantelope";
    result = words[0].getName();
    assertEquals(expResult,result);
    expResult = "platypus";
    result = words[1].getName();
    assertEquals(expResult,result);
    expResult = "cantelope";
    result = words[0].getName();
    assertEquals(expResult,result);

    System.out.println("");
    System.out.println("mergeSort 4");
    words = new Word[4];
    words[0] = new Word(5,"badger");
    words[1] = new Word(4,"platypus");
    words[2] = new Word(4,"cantelope");
    words[3] = new Word(3,"zebra");
    instance.mergeSort(words);
    expResult = "zebra";
    result = words[0].getName();
    assertEquals(expResult,result);
    expResult = "cantelope";
    result = words[1].getName();
    assertEquals(expResult,result);
    expResult = "platypus";
    result = words[2].getName();
    assertEquals(expResult,result);
    expResult = "badger";
    result = words[3].getName();
    assertEquals(expResult,result);

    System.out.println("");
    System.out.println("mergeSort 5");
    words = new Word[5];
    words[0] = new Word(5,"badger");
    words[1] = new Word(4,"platypus");
    words[2] = new Word(4,"cantelope");
    words[3] = new Word(3,"zebra");
    words[4] = new Word(6,"beaver");
    instance.mergeSort(words);
    expResult = "zebra";
    result = words[0].getName();
    assertEquals(expResult,result);
    expResult = "cantelope";
    result = words[1].getName();
    assertEquals(expResult,result);
    expResult = "platypus";
    result = words[2].getName();
    assertEquals(expResult,result);
    expResult = "badger";
    result = words[3].getName();
    assertEquals(expResult,result);
    expResult = "beaver";
    result = words[4].getName();
    assertEquals(expResult,result);
  }

  /**
   * Test of merge method, of class WordFrequency.
   */
  @Test
  public void testMerge() {
    System.out.println("Merge");
    Word[] words = new Word[2];
    words[0] = new Word(1,"banana");
    words[1] = new Word(1,"apple");
    WordFrequency instance = new WordFrequency();
    instance.merge(words,0,0,1,1);
    String expResult = "banana";
    String result = words[1].getName();
    assertEquals(expResult,result);

    expResult = "apple";
    result = words[0].getName();
    assertEquals(expResult,result);

    words = new Word[3];
    words[0] = new Word(1,"banana");
    words[1] = new Word(1,"apple");
    words[2] = new Word(2,"king");
    instance.merge(words,0,1,2,2);
    expResult = "king";
    result = words[2].getName();
    assertEquals(expResult,result);

    words = new Word[7];
    words[0] = new Word(1,"kyte");
    words[1] = new Word(2,"king");
    words[2] = new Word(3,"ant");
    words[3] = new Word(1,"javo");
    words[4] = new Word(2,"mike");
    words[5] = new Word(3,"queen");
    words[6] = new Word(4,"java");
    instance.merge(words,0,3,4,6);
    expResult = "queen";
    result = words[5].getName();
    assertEquals(expResult,result);
  }



  /**
   * Test of growWordTree method, of class WordFrequency.
   */
  @Test
  public void testInOrderTraversal() {
    System.out.println("inOrderTraversal");
    LinkedStack<String> stack = new LinkedStack<String>();
    stack.push("one");
    stack.push("zebra");
    stack.push("two");
    stack.push("three");
    stack.push("apple");
    stack.push("zebra");
    stack.push("aardvark");
    stack.push("two");
    stack.push("three");
    stack.push("four");
    stack.push("apple");
    BinSearchTree tree = new BinSearchTree();
    WordFrequency instance = new WordFrequency();
    instance.growWordTree(tree, stack);
    Word[] words = new Word[10000];
    instance.inOrderTraversal(tree.getRoot(), words);
    String expResult = "aardvark";
    String result = words[0].getName();
    int counter = 0;
    while (words[counter] != null) {
      System.out.println(words[counter].getName() + ": " + words[counter].getCount());
      counter += 1;
    }
    assertEquals(expResult, result);
  }

  /**
   * Test of growWordTree method, of class WordFrequency.
   */
  @Test
  public void testGrowWordTree() {
    System.out.println("growWordTree");
    LinkedStack<String> stack = new LinkedStack<String>();
    stack.push("one");
    stack.push("two");
    stack.push("three");
    stack.push("two");
    BinSearchTree tree = new BinSearchTree();
    WordFrequency instance = new WordFrequency();
    instance.growWordTree(tree, stack);
    int expResult = 2;
    int result = tree.find("two");
    assertEquals(expResult, result);
    expResult = 1;
    result = tree.find("one");
    assertEquals(expResult, result);
  }
  
  /**
   * Test of queueDir method, of class WordFrequency.
   */
  @Test
  public void testQueueDir() {
    System.out.println("queueDir");
    File dir = new File("C:\\My Dropbox\\dev\\WordFrequency\\src\\wordfrequency\\corpusDocs");
    WordFrequency instance = new WordFrequency();
    LinkedStack<String> words = new LinkedStack<String>();
    instance.queueDir(words,dir);
    String expResult = "Monday";
    String result = words.peek().getValue();
    assertEquals(expResult, result);
  }

  /**
   * Test of parseFile method, of class WordFrequency.
   */
  @Test
  public void testqueueFile() {
    System.out.println("queueFile");
    LinkedStack<String> words = new LinkedStack<String>();
    File filename = new File(
            "C:\\My Dropbox\\dev\\WordFrequency\\src\\wordfrequency\\ownTest\\test2.txt");
    WordFrequency instance = new WordFrequency();
    instance.queueFile(words, filename);
    String expResult = "one";
    String result = words.pop().getValue();
    assertEquals(expResult,result);
  }

  /**
   * Test of processLine method, of class WordFrequency.
   */
  @Test
  public void testqueueLine() {
    System.out.println("queueLine");
    LinkedStack<String> words = new LinkedStack<String>();
    String line = "one two three four";
    WordFrequency instance = new WordFrequency();
    instance.queueLine(words, line);
    String expResult = "three";
    words.pop();
    String result = words.peek().getValue();
    assertEquals(expResult,result);
  }

}