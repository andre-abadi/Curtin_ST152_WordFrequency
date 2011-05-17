package wordfrequency;

/**
 * Main class, handles calls to worker classes that are the program logic.
 * @author sajuuk
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      WordFrequency instance = new WordFrequency();
      instance.harness();
    }

}
