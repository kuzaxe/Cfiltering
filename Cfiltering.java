
package a0;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param userA The user on the x-axis 
   * @param userB The user on the y-axis
   * @param numberOfMovies int of number of movies in the array
   * @return COMPLETE THIS IF NEEDED
   */
  public void calculateSimilarityScore(int userA, int userB,
      int numberOfMovies) {

    double dist = 0;

    // Using Euclidean Distance Formula
    for (int x = 0; x < numberOfMovies; x++) {
      dist = dist + Math
          .pow(((userMovieMatrix[userA][x] - userMovieMatrix[userB][x])), 2);
    }

    double euclidScore = (1 / (1 + Math.sqrt(dist)));

    // Store similarity score in both upper and lower triangle in Matrix  
    userUserMatrix[userA][userB] = (float) euclidScore;
    userUserMatrix[userB][userA] = (float) euclidScore;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @param numberOfUsers int Number of users in the input file
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void printUserUserMatrix(int numberOfUsers) {
    System.out.println();
    System.out.println();

    System.out.println("userUserMatrix is:");
    List<String> userScores = new ArrayList<String>();
    
    // convert float/double into float/double with four decimal places.
    DecimalFormat dec = new DecimalFormat("0.0000");
    
    // print out a list array of the matrix
    for (int i = 0; i < numberOfUsers; i++) {
      for (int j = 0; j < numberOfUsers; j++) {
        userScores.add(dec.format(userUserMatrix[i][j]));
      }
      System.out.println(userScores);
      userScores.clear();
    }
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @param numberOfUsers int Number of users in the input file
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void findAndprintMostSimilarPairOfUsers(int numberOfUsers) {
    System.out.println();
    System.out.println();

    float highestScore = 0;
    List<String> similarUsers = new ArrayList<String>();

    // reviews matrix and finds users with highest similarity score
    for (int x = 0; x < (numberOfUsers - 1); x++) {
      for (int y = x + 1; y < (numberOfUsers); y++) {
        if (userUserMatrix[x][y] > highestScore) {
          highestScore = userUserMatrix[x][y];
          similarUsers.clear();
          similarUsers.add("User" + (x + 1) + " and User" + (y + 1));
        } else if (userUserMatrix[x][y] == highestScore) {
          similarUsers.add("User" + (x + 1) + " and User" + (y + 1));
        }
      }
    }

    System.out.println(
        "The most similar pairs of users from above userUserMatrix are:");

    int count = similarUsers.size();
    int count2 = 0;
    while (count != 1) {
      System.out.println(similarUsers.get(count2) + ", ");
      count2 = count2 + 1;
      count = count - 1;
    }
    System.out.println(similarUsers.get(count2));

    DecimalFormat dec = new DecimalFormat("0.0000");
    System.out.println("with similarity score of " + dec.format(highestScore));
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
    
  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @param numberOfUsers int Number of users in the input file
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostDissimilarPairOfUsers(int numberOfUsers) {
    System.out.println();
    System.out.println();

    float lowestScore = 1;
    List<String> dissimilarUsers = new ArrayList<String>();

    // reviews matrix and finds users with lowest similarity score
    for (int x = 0; x < (numberOfUsers - 1); x++) {
      for (int y = x + 1; y < (numberOfUsers); y++) {
        if (userUserMatrix[x][y] < lowestScore) {
          lowestScore = userUserMatrix[x][y];
          dissimilarUsers.clear();
          dissimilarUsers.add("User" + (x + 1) + " and User" + (y + 1));
        } else if (userUserMatrix[x][y] == lowestScore) {
          dissimilarUsers.add("User" + (x + 1) + " and User" + (y + 1));
        }
      }
    }

    System.out.println("The most dissimilar pairs of users from above "
        + "userUserMatrix are:");
    int count = dissimilarUsers.size();
    int count2 = 0;
    while (count != 1) {
      System.out.println(dissimilarUsers.get(count2) + ", ");
      count2 = count2 + 1;
      count = count - 1;
    }
    System.out.println(dissimilarUsers.get(count2));

    DecimalFormat dec = new DecimalFormat("0.0000");
    System.out.println("with similarity score of " + dec.format(lowestScore));
  }

}
