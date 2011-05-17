package wordfrequency;

/**
 * The node of a binary tree.
 * All fields can be changed but key, which cannot be changed.
 * Adapted from:
 *  Curtin University's Software Technology 152 Lecture 6
 * @author sajuuk
 */
public class BinTreeNode {
  
  private String key;
  private int count;
  private BinTreeNode leftChild;
  private BinTreeNode rightChild;

  /**
   * Default constructor, populates all fields
   * @param name Name of the node
   */
  public BinTreeNode(String name) {
    key  = name;
    count = 1;
    leftChild   = null;
    rightChild  = null;
  }

  /**
   * Copy-constructor
   * @param original The BinTreeNode to be copied
   */
  public BinTreeNode (BinTreeNode original) {
    key  = original.getKey();
    count = original.getCount();
    leftChild = original.getLeftChild();
    rightChild = original.getRightChild();
  }

  /**
   * Gets key
   * @return String key of this BinTreeNode
   */
  public String getKey() {
    return key;
  }

  /** Gets count
   * @return int count of this BinTreeNode
   */
  public int getCount() {
    return count;
  }

  /**
   * Sets count
   * @param newCount int The new count of this BinTreeNode
   */
  public void setCount(int newCount) {
    if (count < 1) {
      throw new IllegalArgumentException("newCount must be >1, found "+newCount);
    }
    count = newCount;
  }

  /**
   * Gets left child
   * @return BinTreeNode left child of this node
   */
  public BinTreeNode getLeftChild() {
    return leftChild;
  }

  /**
   * Gets right child
   * @return BinTreeNode right child of this node
   */
  public BinTreeNode getRightChild() {
    return rightChild;
  }

  /**
   * Sets left child
   * @param newChild BinTreeNode the new left child of this node
   */
  public void setLeftChild(BinTreeNode newChild) {
    leftChild = newChild;
  }

  /**
   * Sets right child
   * @param newChild TreeNode the new right child of this node
   */
  public void setRightChild(BinTreeNode newChild) {
    rightChild = newChild;
  }
}
