
/******************************************************************
 *
 *   SYDNEY AMAYA / SECTION 001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {
    /*
     * 1. create a priority queue contianing all elements from boulders that 
     * prioritizes the max element
     * 2. use a while loop that ends when size of the PQ is less than or equal to 1
     * 3. get max element by getting the first element and then get the second 
     * greatest element 
     * 4. test if x == y and if x != y delete x and make y = y - x
     * 5. repeat until size > 2, return value of last stone which will be given priority
     */
    //create a max priority queue 
    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(boulders.length, Collections.reverseOrder());
    //add all elements from boulders to the PQ
    for (int i = 0; i < boulders.length; i++){
      pQueue.add(boulders[i]);
    }
    int x = 0; 
    int y = 0;
    //ends when there are less than two boulders 
    while (pQueue.size() >= 2){
      //removes and returns the max element
      y = pQueue.poll();
      //returns but does not return the new max element
      x = pQueue.peek();
      //if x == y remove only x since y has already been removed
      if (x == y){
        pQueue.remove(x);
      }
      //if x != y then y = y - x remove x and add y 
      else if (x != y){
        y = y - x;
        pQueue.remove(x);
        pQueue.add(y);
      }
    }
    //check to see if the queuue is empty and return 0 to prevent null pointer
    if (pQueue.isEmpty()){
      return 0;
    }
    //return the last element remaining using peek 
    return pQueue.peek();
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {
      /*
      1. use a for loop to traverse the arraylist and compare each element to 
      the rest of the elements
      2. use a second for loop to traverse the arraylist comparing element i 
      from outer loop to all elements j in the inner loop
      3. if a duplicate array is found, add it it the arraylist of duplicates
      4. sort the arraylist before returning it 
      */ 
      ArrayList<String> duplicates = new ArrayList<>(input.size());
      for (int i = 0; i < input.size(); i++){
        for (int j = i + 1; j < input.size() - 1; j++){
          if (input.get(i).equals(input.get(j))){
            duplicates.add(input.get(i));
          }
        }
      }
      Collections.sort(duplicates);
      return duplicates;  // Make sure result is sorted in ascending order
    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {
      /**
       * 1. use two for loops, the outer loop traverses through the array
       * 2. the inner loop adds i the element from the outer loop to every other element 
       * and checks if the sum = k 
       * 3. if i + j = k add the i and j values to a string and add to the arraylist
       * 4. check if the pair is already present in the arraylist, do not add if already 
       * in the arraylist 
       * 5. the pairs in the arraylist need to be in the order of lowest to smallest i value
       */
      ArrayList<String> pairs = new ArrayList<String> (input.length);
      return pairs;  // Make sure returned lists is sorted as indicated above
    }
}