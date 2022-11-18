package sda;

import java.util.*; 
import java.io.*;


public class Tree {
   
   private TreeNode rootOfTree;
  
   private Scanner console; 
   

   public Tree() {
      rootOfTree = new TreeNode("computer"); 
      console = new Scanner(System.in); 
   }
   
   // replaces the current tree available by reading entire
   // line of input to construct a tree based on a file. 
   public void read(Scanner input) {
      while(input.hasNextLine()) {
         rootOfTree = readHelper(input); 
      }
   }
   // helper method that reads entire lines of input to 
   // construct a tree based on a file. 
   private TreeNode readHelper(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      TreeNode root = new TreeNode(data);  
  
      if (type.contains("Q:")) {
         root.yesNode = readHelper(input);
         root.noNode = readHelper(input);   
      }
      return root; 
   }
   

   public void write(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException(); 
      }
      writeTree(rootOfTree, output);
   }
   

   private void writeTree(TreeNode rootOfTree, PrintStream output) {
      if (isAnswerNode(rootOfTree)) {
         output.println("A:"); 
         output.println(rootOfTree.data);
      } else {
         output.println("Q:");
         output.println(rootOfTree.data);
         writeTree(rootOfTree.yesNode, output);
         writeTree(rootOfTree.noNode, output); 
      }   
   }
   
 
   public void askQuestions() {
      rootOfTree = askQuestions(rootOfTree); 
   }

   private TreeNode askQuestions(TreeNode current) {
      if (isAnswerNode(current)) {
         if (yesTo("Would your object happen to be " + current.data +"?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            TreeNode answer = new TreeNode(console.nextLine());
            System.out.println("Please give me a yes/no question "
            		+ "that distinguishes between your object and mine--> ");
            String question = console.nextLine(); 
            if (yesTo("And what is the answer for your object?")) {
               current = new TreeNode(question, answer, current); 
            } else {
               current = new TreeNode(question, current, answer); 
            }   
         }
     
      } else {
         if (yesTo(current.data)) {
            current.yesNode = askQuestions(current.yesNode);
         } else {
            current.noNode = askQuestions(current.noNode); 
         }   
      } 
      return current;
   }
   
   //  asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
          System.out.println("Please answer y or n.");
          System.out.print(prompt + " (y/n)? ");
          response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }   
   // private method that determines whether a specific node 
   // is an answer node (a leaf node)
   private boolean isAnswerNode(TreeNode node) {
      return (node.yesNode == null || node.noNode == null);
   }
}













