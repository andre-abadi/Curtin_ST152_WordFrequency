package wordfrequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Is able to scan a directory for files and sort the occurrent words by count.
 * MergeSort algorithm adapted from:
 *  Curtin University's Software Technology 152 Lecture 5,
 *  LaFore's Data Structures and Algorithms, 2nd Ed.
 * File input and output adapted from:
 *  Curtin University's Software Technology 152 Lecture 6.
 * Uses a binary search tree, adapted from:
 *  Curtin University's Software Technology 152 Lecture 7.
 * @author sajuuk
 */
public class WordFrequency {

  /**
   * The 'main' method of this program.
   */
  public void harness() {
    introMessage();
    //storage of the words gathered, passed around to various methods
    Word[] words = null;
    //user input variable
    int choice = 0;

    //this while loop keeps the program from exiting until option 5 is selected
    while (choice != 5) {
      //regardless of what happens, we'll spit out the main menu
      printMainMenu();
      //then gather the console input
      String input = parseInput();
      //and use the stringToMenuChoice function to parse the String to an int
      choice = stringToMenuChoice(input);

      if (choice == 1) {
          System.out.println("Please enter directory containing"
                + " .txt files:");
          String directory = parseInput();
          if (directory.equals("") == true) {
            System.out.println("You must supply a directory path.");
          } else {
            System.out.println("");
            words = null;
            words = processDir(directory);
          }        
      }

      if (choice == 2) {
        if (words == null) {
          arrayEmptyMessage();
        } else {
          System.out.println("");
          printWords(words);
        }
      }

      if (choice == 3) {
        if (words == null) {
          arrayEmptyMessage();
        } else {
          System.out.println("");
          printReverse(words);
        }
      }

      if (choice == 4) {
        if (words == null || words.length == 0) {
          arrayEmptyMessage();
        } else {
          System.out.println("Please name a file"
                  + " with full path, to create or"
                  + " overwrite with CSV data:");
          System.out.println("Press ENTER (blank input) to cancel.");
          String file = parseInput();
          if (file.equals("") == true) {
            System.out.println("You must supply a directory path."
                    + " Try again.");
          } else {
            writeWordArray(file,words);
          }
        }
      }
    }

    //will get here if option 5 is selected, exiting the method and program
    if (choice == 5) {
      System.out.println("");
      System.out.println("Exiting program.");
    }
  }

  /**
   * Parses a Word[] object-array into CSV and writes it to a given file
   * @param file String The file to which to write the CSV data
   * @param words Word[] An array of Word objects to be written
   */
  public void writeWordArray(String file, Word[] words) {
    //prelimenary setup of data-writing structures
    FileOutputStream outputStream = null;
    PrintWriter printer;
    //put the whole output method into a try loop, checking for io errors
    try {
      //set up the output stream from the supplied file
      outputStream = new FileOutputStream(file);
      //set up the printing structure with which to print to the file
      printer = new PrintWriter(outputStream);
      //start a counter to keep track of how far along the word-array we are
      int counter = 0;
      //iterate along the word array, spitting out each word to a new line
      while (counter < words.length) {
        //print the word data onto a line in the file, formatted as CSV
        printer.println(
                  words[counter].getCount()
                  + ","
                  + words[counter].getName()
                );
        counter += 1;
      }
      //once we've finished iterating, to finish we'll close the file writer
      printer.close();
    }

    //one possible exception that could occur is a NullPointerException,
    //which would occur if there is no valid word-array supplied to this method
    catch (NullPointerException ec) {
      arrayEmptyMessage();
    }

    //another possible exception is an IOException
    catch ( IOException ea) {
      //if this does happen, we'll check if the outputStream is still active
      if (outputStream != null) {
        try {
          //if it is still active, try closing it
          outputStream.close();
        } catch (IOException eb)
        {
          //which could throw an error of its own
          System.out.println("Problems in closing file. " + eb.getMessage());
        }
      }

      //after trying to close the output stream, tell the user
      //that there were problems with the IO, causing the IOException
      //that led here
      System.out.println("Problems in writing line. " + ea.getMessage());
    }
  }

  /**
   * Converts the first character of an input String to an int
   * @param input String The console input to be interpreted.
   * @return An int, converted from the first character 
   */
  public int stringToMenuChoice(String input) {
    //first, check that the String is a single character
    //which it must be if is to be a valid menu entry
    if (input.length() > 1) {
      inputErrorMessage();
      return 0;
    } else {
      //if the input String is longer than one character, we can go forth
      //set up a character to store the first character
      Character firstchar = '?';
      //next, check that the input String is not empty, which would cause
      //an exception
      if (input.equals("") == false) {
        //if the input is not empty, convert the first character
        //of the input String to a Character
        firstchar = input.charAt(0);  
      }
      //set up an int to store the integer-ized menu choice String
      int choice = Character.digit(firstchar, 10);
      if (choice == -1) {
        //digitisation could spit out -1 if the first character
        //is not a number
        inputErrorMessage();
      } else
      if (choice > 5) {
        //if digitisation finds an integer larger than 5
        //then it is an invalid menu entry, and we should inform the user
        inputErrorMessage();
        
      }
      //finally, return what we found choice to be
      return choice;
    }

  }

  /**
   * Prints the supplied array in ascending order of count
   * @param array Word[] An array of words to display
   */
  public void printWords(Word[] array) {
    if (array.length == 0) {
      arrayEmptyMessage();
    } else {
      System.out.println("Displaying counts in sorted order:");
      //start at the first (lowest count) cell index
      counter = 0;
      //then iterate over every cell in the array
      while (counter < array.length) {
        //parse the current word object into an output String
        System.out.println(array[counter].getCount()
              + ", \"" + array[counter].getName()
              + "\"");
        //incrementing the counter at every pass
        counter += 1;
      }
      //finally, tell the user how many unique strings we found
      System.out.println("Found " + array.length + " unique words.");
    }
  }

  /**
   * Prints the supplied array in descending order of count
   * @param array Word[] An array of words to display
   */
  public void printReverse(Word[] array) {
    if (array.length == 0) {
      arrayEmptyMessage();
    } else {
      System.out.println("Displaying counts in reverse order:");
      //start at the last (highest count) cell index
      counter = array.length - 1;
      //then iterate over every cell in the array
      while (counter >= 0) {
        //parse the current word object into an output String
        System.out.println(array[counter].getCount()
              + ", \"" + array[counter].getName()
              + "\"");
        //decrementing the counter at every pass
        counter -= 1;
      }
      //finally, tell the user how many unique strings we found
      System.out.println("Found " + array.length + " unique words.");;
    }
  }

  /**
   * Parses the console input line into a String
   * @return String The console input, converted to a String
   */
  public String parseInput() {
    //initialise the variable to be returned
    String parsed = "";
    //put the whole process inside a try loop, in case there are problems
    //with the io
    try {
      //create an input stream reader, taking input from the console input
      InputStreamReader streamReader = new InputStreamReader(System.in);
      //create a buffered reader to take chunks out of the input stream reader
      BufferedReader bufferedReader = new BufferedReader(streamReader);
      //assign the read data into a String
      parsed = bufferedReader.readLine();
    }
    //it is possible that there could be an io problem between the above
    //code and the user's input
    catch (IOException e) {
      //if so, tell the user what went wrong
      throw new IllegalArgumentException("Error reading console input String: "
              + e.getMessage());
    }
    //finally, return the parsed console input as a String
    //which could be blank if assigning the console input was unsuccessful
    return parsed;
  }

  /**
   * Prints the main program menu.
   */
  public void printMainMenu() {
    System.out.println("");
    System.out.println("#####################################################");
    System.out.println("Please select from an option below using the numbers:");
    System.out.println("(1) - Load document corpus.");
    System.out.println("(2) - Display counts in sorted order (ascending).");
    System.out.println("(3) - Display counts in reverse order (descending).");
    System.out.println("(4) - Save words and counts in CSV file.");
    System.out.println("(5) - Quit.");
    System.out.println("#####################################################");
  }
  
  /**
   * Prints the introduction message, explaining the function of this program.
   */
  public void introMessage() {
    System.out.println("Welcome, this program is designed to iterate");
    System.out.println("through txt files (only) in a given directory");
    System.out.println("and calculate the number of occurrences of each");
    System.out.println("word that is found. Words are assumed to be any");
    System.out.println("sequences of characters separated by spaces. If");
    System.out.println("words have the same frequency, then they are");
    System.out.println("sorted alphabetically.");
  }

  /**
   * Prints the error message if the word-array is empty
   */
  public void arrayEmptyMessage() {
    System.out.println("No list of words found, try loading "
            + "a directory using: (1) - Load document corpus.");
  }

  /**
   * Prints the error message if invalid input was given
   */
  public void inputErrorMessage() {
    System.out.println("Could not parse input, "
            + "it must be a single digit between 1 and 5.");
  }

  /**
   * Processes a directory into an array of words, ordered ascending.
   * @param directory String A directory path
   * @return An array of words, ascending by count
   */
  public Word[] processDir(String directory) {
    //convert the parameter String into a File object
    File dir = new File(directory);
    //set up a stack of words into which to dump read words from files
    LinkedStack<String> words = new LinkedStack<String>();
    //queue up the words from the directory into the stack
    queueDir(words,dir);
    //create a binary search tree to store words and their counts
    BinSearchTree tree = new BinSearchTree();
    //process the stack of words into a binary tree
    growWordTree(tree, words);
    //set up an array of words to store words and their frequencies
    //assignment parameters state no more than 10,000 unique words
    //would be found, but 100,000 is as much as the program can take
    //before it starts to lag
    Word[] occurrences = new Word[100000];
    //traverse the tree to grab words
    counter = 0;
    //and keep a counter of how many entries in the word-array
    //will be populated
    entries = 0;
    int entries = inOrderTraversal(tree.getRoot(), occurrences);
    Word[] trimmedOccurrences = new Word[entries];
    int counter = 0;
    while (counter < entries) {
      trimmedOccurrences[counter] = new Word(occurrences[counter]);
      counter += 1;
    }
    //mergesort the array words
    mergeSort(trimmedOccurrences);
    //finally returning the trimmed array of words in alphabetic order
    return trimmedOccurrences;
  }

  /**
   * Wrapper method for the merging algorithm, sorts in-order.
   * @param array An array to be sorted in-order.
   */
  public void mergeSort(Word[] array) {
    //we can assume that we will want to mergeSort the entire array
    //meaning that the first element must be 0
    int aStart = 0;
    //and the last element to be sorted will be the last cell-index
    int bFinish = array.length - 1;
    //int counter = aStart;
    //once setting up the variables, begin the actual algorithm
    mergeSortRecurse(array, aStart, bFinish);
  }

  /**
   * The recursive portion of the mergeSort algorithm.
   * @param array The array inside which to sort a contiguous portion.
   * @param aStart The cell index of the start of the sub-array.
   * @param bFinish The cell index of the end of the sub-array.
   */
  public void mergeSortRecurse(Word[] array, int aStart, int bFinish) {
    //keep going as long as the sub-array has length
    if (aStart < bFinish) {
      //the end of the left sub-array will be half way along the
      //parameter array
      int aFinish = (((bFinish-aStart)/2) + aStart);
      //the start of the right sub-array will be the next cell after
      //the end of the left sub-array
      int bStart = aFinish + 1;
      //if the above arithmetic manages to go beyond the scope of the
      //parameter array, pull it back to last element
      //to avoid getting an arrayIndexOutOfBounds exception
      if (bStart >= bFinish) {
        bStart = bFinish;
      }
      //recurse over the left half of the parameter array
      mergeSortRecurse(array,aStart,aFinish);
      //recurse over the right half of the paramter array
      mergeSortRecurse(array,bStart,bFinish);
      //then merge the two sub-arrays in-place
      merge(array,aStart,aFinish,bStart,bFinish);
    }
  }

  /**
   * Merges an array assuming it consists of two halves, each ordered.
   * @param array Word[] The array to merge
   * @param aStart First cell of the left sub-array
   * @param aFinish Last cell of the left sub-array
   * @param bStart First cell of the right sub-array
   * @param bFinish Last cell of the right sub-array
   */
  public void merge(Word[] array,
          int aStart, int aFinish,
          int bStart, int bFinish) {
    //first calculate how big an array we'll need to store the data temporarily
    int sortedLength = ((aFinish-aStart)+1) + ((bFinish-bStart)+1);
    //then create a word-array to store our data as we sort it
    Word[] sorted = new Word[sortedLength];
    //set up some index counters to keep track of the left and right sub-arrays
    int aDex = aStart;
    int bDex = bStart;
    //and a third counter to keep track of how far along the temporary,
    //sorted array we are
    int cDex = 0;
    //keep iterating comparisons between the sub-arrays while
    //the counters indicate that there are unexamined elements left in each
    while ( (aDex <= aFinish) && (bDex <= bFinish) ) {
      //if the count of the item at the left sub-array's current cell is smaller
      if ( (array[aDex].getCount()) < (array[bDex].getCount()) ) {
        //add it to the sorted array
        sorted[cDex] = new Word(array[aDex]);
        //increment count1 since we have now used this element
        aDex += 1;
        //move count3 onto the next index
        cDex += 1;
      }
      //otherwise, if the count of the item at the left sub-array's current
      //cell is larger
      //i.e. the right sub-array has the smaller value
      else if ( (array[aDex].getCount()) > (array[bDex].getCount()) ) {
        sorted[cDex] = new Word(array[bDex]);
        //increment the sub-array counter to look at the next item
        bDex += 1;
        //increment the sorted counter so that the next inserted item goes to
        //an empty cell
        cDex += 1;
      }
      //if neither count is larger than the other then they must be equal
      else if ( (array[aDex].getCount()) == (array[bDex].getCount()) ) {
        //then we'll need to sort them alphabetically
        //if the String at array[count1] comes alphabetically BEFORE
        //the String at array[count2]
        if (array[aDex].getName().compareToIgnoreCase
                (array[bDex].getName()) < 0) {
          //then insert the cell at count1 into the sorted array
          sorted[cDex] = new Word(array[aDex]);
          //increment count1 since we have now used this element
          aDex += 1;
          //move count3 onto the next index
          cDex += 1;
        }
        //otherwise, if the cell at count1 comes alphabetically AFTER
        //that of count2
        else if (array[aDex].getName().compareToIgnoreCase
                (array[bDex].getName()) > 0) {
          //then insert the cell at count2 into the sorted array
          sorted[cDex] = new Word(array[bDex]);
          //increment the sub-array counter to look at the next item
          bDex += 1;
          //increment the sorted counter so that the next inserted item goes to
          //an empty cell
          cDex += 1;
        }
        //if the words were neither before nor after alphabetically
        //then they must be the same word
        //in which case our binSearchTree must have gone wrong somewhere
        //in allowing two identical words to exist
        else {
          throw new IllegalArgumentException("Found duplicate words: "
                  + array[aDex].getName() + " and "
                  + array[bDex].getName() + ", indicating a problem"
                  + " with the BinSearchTree.");
        }
      }
      //if neither cell was larger nor smaller, nor were they equal,
      //then something went wrong
      else {
        throw new IllegalArgumentException("Could not compute comparison of"
                + "current cells: " + aDex + " and " + bDex);
      }
    }
    //to get to here, one of the sub-arrays must have been consumed
    //and the other one then simply needs to be dumped (as it is already
    //in order) into the sorted array

    //the remaining array could be the left:
    while (aDex <= aFinish) {
      sorted[cDex] = new Word(array[aDex]);
      aDex += 1;
      cDex += 1;
    }

    //or the remaining sub-array could be the right:
    while (bDex <= bFinish) {
      sorted[cDex] = new Word(array[bDex]);
      bDex += 1;
      cDex += 1;
    }

    //and so both sub-arrays have been consumed and the sorted array is
    //now full, so let's copy our sorted array values back into
    //the parameter array, making the merge an in-place merge
    int arrayCounter = aStart;
    //set up a counter for the sorted array that we have previously populated
    int sortedCounter = 0;
    //and iterate over the sorted array
    while (sortedCounter <= (sorted.length-1)) {
      //assigning each sorted cell to the parameter array
      array[arrayCounter] = new Word(sorted[sortedCounter]);
      //and incrementing both counters
      arrayCounter += 1;
      sortedCounter += 1;
    }
  }

  //counter to hold reference to current index of word array
  private int counter = 0;
  //counter to hold the number of occupied entries
  private int entries = 0;
  /**
  * Recursively traverses the tree in-order and dumps nodes into an array
  * @param node BinTreeNode The node at which to begin the recursive algorithm
   * @param words Word[] The array of words to which to add the node
   * @return int The number of entries (words) found in the tree
  */
  public int inOrderTraversal(BinTreeNode node, Word[] words) {
    //keep recursing until you hit a null-node
    if (node != null) {
      //recurse over the left child of this node
      inOrderTraversal(node.getLeftChild(), words);
      //add the current node to the array of nodes
      words[counter] = new Word(node.getCount(), node.getKey());
      //increment the number of entries
      entries += 1;
      //then increment the counter for the current array index
      counter += 1;
      //and recurse over the right child of this node
      inOrderTraversal(node.getRightChild(), words);
    }
    //finally, return the number of entries processed
    return entries;
  }

  /**
   * Creates a tree of words and their occurrences from a stack of strings
   * @param wordtree BinSearchTree The tree to be populated with words
   * @param words LinkedStack<String> A stack of words to be counted
   */
  public void growWordTree(BinSearchTree wordtree, LinkedStack<String> words) {
    //iterate over the parameter Word-array
    while (words.isEmpty() == false) {
      //popping the stack onto a temporary variable
      //this also increments the top of the stack
      String current = words.pop().getValue();
      //and then inserting the temprary variable onto the BinSearchTree
      wordtree.insert(current);
    }
  }

  /**
   * Adds all the words in all the files of a directory to a stack of words
   * @param words LinkedStack<String> a stack in which to store words
   * @param dir String The directory inside which the files are to be found
   */
  public void queueDir(LinkedStack<String> words, File dir) {
    //we will create a list of all the files we need to process
    File[] files = dir.listFiles();
    //needs to iterate over available documents
    int current = 0;
    //and add all of their words to a giant stack of words
    try {
      while (current<files.length) {
        File currentFile = files[current];
        //parse the name of the file in lower case
        String lowerCaseName = files[current].getName().toLowerCase();
        //check that it has the txt extension
        if (lowerCaseName.endsWith(".txt")) {
          //if so, queue it
          queueFile(words,currentFile);
          System.out.println("Processed: " + currentFile.getName());
        } else {
          //otherwise inform the user that we will ignore it
          System.out.println("Ignored: "
                  + files[current].getName()
                  + " (it does not appear to end in .txt).");
        }
        //and increment our counter regardless
        current += 1;
      }
    }
    //it is possible that we could get a NullPointerException
    //if a bad dir File was passed to this method, in which
    //case we'll inform the user that a bad file was passed
    catch (NullPointerException e) {
      System.out.println("Could not recognise input directory. Try again.");
    }
  }

  /**
   * Adds the words inside a file onto a stack of words.
   * @param words LinkedStack<String> a stack in which to store words
   * @param filename File The file inside which to gather words
   */
  public void queueFile(LinkedStack<String> words, File filename) {
    //set up some structures to process the file
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;
    //and start a counter for the current line
    int lineNum;
    //also create a temprary variable for holding the current line contents
    String line;
    //wrap the IO in a try clause, to catch any IOExceptions that may occur
    try {
      //initialise the input-reading structures
      fileStrm = new FileInputStream(filename);
      rdr = new InputStreamReader(fileStrm);
      bufRdr = new BufferedReader(rdr);
      //initialise the current line counter
      lineNum = 0;
      //and read the first line of the file to the current line variable
      line = bufRdr.readLine();

      //iterate as long as line has data in it
      while (line != null) {
        //increment the current line
        lineNum += 1;
        //queue the line of text onto the stack
        queueLine(words, line);
        //and increment the line-readiing variable
        line = bufRdr.readLine();
      }

      //when done reading lines, close the file
      fileStrm.close();
    }

    //it is possible that something could go wrong with the io
    catch (IOException e) {
      //if so, check whether or not the file was closed
      if (fileStrm != null) {
        //if it wasn't, try closing it to release it to the OS again
        try {
          fileStrm.close();
        }
        //and catch any errors that that action could create
        catch (IOException ex2) {}
        }
          System.out.println("Error in file processing: " + e.getMessage());
    }
  }

  /**
   * Adds the words inside a line of text onto a stack of words.
   * @param words LinkedStack<String> a stack in which to store words
   * @param line String the line of text to be searched for words
   */
  public void queueLine(LinkedStack<String> words, String line) {
    //variable for holding current token being looked at
    String thisToken = null;
    //string tokenizer
    StringTokenizer strTok;

    //initializer string tokenizer, seperators are spaces
    strTok = new StringTokenizer(line," ",false);
    //keep going as long as there are more tokens
    while (strTok.hasMoreTokens() == true) {
      //increment the token
      thisToken = strTok.nextToken();
      //enqueue the
      words.push(thisToken);
    }
  }
}