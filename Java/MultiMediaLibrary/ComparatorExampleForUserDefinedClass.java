//import org.apache.commons.collections.comparators.ComparatorChain;

package libraryBase;


import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExampleForUserDefinedClass {
/*  public static void main(String args[]) {
    prepareData();

    ComparatorChain chain = new ComparatorChain();
    chain.addComparator(new NameComparator());
    chain.addComparator(new NumberComparator());

    printArray(dataArray);

    Arrays.sort(dataArray, chain);

    printArray(dataArray);
  }
*/
  private static void prepareData() {
    dataArray[0] = "S4";
    dataArray[1] = "Sa";
    dataArray[2] = "K";
    dataArray[3] = "K4";
    dataArray[4] = "W";
    dataArray[5] = "Sha";
    dataArray[6] = "War";
  }

  private static void printArray(String[] array) {
    System.err.println("---- Elements in Array ---- ");
    for(int i = 0; i < array.length; i++) {
      System.err.print(array[i] + ", ");
    }
    System.err.println("");
  }

  private static String[] dataArray = new String[7];
}


class NameComparator implements Comparator {
  public int compare(Object o1, Object o2) {
    if(o1 instanceof String && o2 instanceof String) {
      String s1 = (String)o1;
      String s2 = (String)o2;
      s1 = s1.substring(0, s1.indexOf("-"));
      s2 = s2.substring(0, s2.indexOf("-"));
      return s1.compareTo(s2);
    }
    return 0;
  }
}

class NumberComparator implements Comparator {
  public int compare(Object o1, Object o2) {
    if(o1 instanceof String && o2 instanceof String) {
      String s1 = (String)o1;
      String s2 = (String)o2;
      Integer i1 = new Integer(s1.substring(s1.indexOf("-"), s1.length()));
      Integer i2 = new Integer(s2.substring(s2.indexOf("-"), s2.length()));
      return i1.compareTo(i2);
    }
    return 0;
  }
}
           
