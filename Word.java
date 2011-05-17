package wordfrequency;

/**
 * A model for a word, storing its name and its frequency.
 * @author sajuuk
 */
public class Word {
  private int     count;
  private String  name;

  /**
   * Default constructor, populates all fields with parameters of call.
   * @param cnt int The count (frequency of occurrence) of the word
   * @param nm String The name of the word
   */
  public Word (int cnt, String nm) {
    if (cnt < 1) {
      throw new IllegalArgumentException("Count must be >1"
              + "constructor was given: " + cnt);
    }
    count = cnt;
    name = nm;
  }

  /**
   * Copy-constructor
   * @param input Word The word to clone
   */
  public Word (Word input) {
    if (input == null) {
      throw new IllegalArgumentException("Cannot duplicate a null word-object");
    }
    count   = input.getCount();
    name    = input.getName();
  }

  /**
   * Gets count.
   * @return int count
   */
  public int getCount() {
    return count;
  }

  /**
   * Gets name
   * @return String name
   */
  public String getName() {
    return name;
  }

}
