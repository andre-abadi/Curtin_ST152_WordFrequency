package wordfrequency;

/**
 * A binary search tree containing up to two children per node.
 * Adapted from:
 *  Curtin University's Software Technology 152 Lecture 7
 * @author sajuuk
 */
public class BinSearchTree {
  private BinTreeNode root;

  /**
   * Default constructor, creates an empty tree
   */
  public BinSearchTree() {
    root = null;
  }

  /**
   * Gets the root node of the tree
   * @return BinTreeNode The root of the tree
   */
  public BinTreeNode getRoot() {
    return root;
  }

  /**
   * Finds the count of the key entered
   * @param key The key for which to return the count
   * @return int count of the key
   */
  public int find (String key) {
    BinTreeNode current = root;

//    System.out.println("Starting search");
    while ((current != null) && (key.equals(current.getKey()) == false) ) {
      if (key.compareToIgnoreCase(current.getKey()) < 0) {
//        System.out.println("Going left");
        current = current.getLeftChild();
      } else {
//        System.out.println("Going right");
        current = current.getRightChild();
      }
    }

    if (current == null) {
      throw new IllegalArgumentException("Key " + key + " not found.");
    }

//    System.out.println("Found key " + key + " to have occurred "
//            + current.getCount() + " times.");
    return current.getCount();
  }

  /**
   * Inserts node with given key or increments node with existing key
   * @param name String name of the node to insert
   */
  public void insert (String name) {
    String key = name.toLowerCase();
    BinTreeNode currentNode    = root;
    BinTreeNode parentNode  = null;
    BinTreeNode duplicate   = null;
    int comparison;

    while (currentNode != null && duplicate == null) {
      parentNode = currentNode;
      comparison = key.compareToIgnoreCase(currentNode.getKey());
      if (comparison == 0) {
        duplicate = currentNode;
        break;
      } else if (comparison < 0) {
        currentNode = currentNode.getLeftChild();
      } else {
        currentNode = currentNode.getRightChild();
      }
    }

    if (duplicate == null) {
      BinTreeNode newNode = new BinTreeNode(key);
      if (parentNode == null) {
        root = newNode;
      }
      else if (key.compareToIgnoreCase(parentNode.getKey()) < 0) {
        parentNode.setLeftChild(newNode);
      }
      else {
        parentNode.setRightChild(newNode);
      }
    }
    else {
      int count = duplicate.getCount();
      count += 1;
      duplicate.setCount(count);
    }

  }

  public void delete (String name) {
    throw new IllegalArgumentException("Cannot delete, method unsupported.");
  }
}