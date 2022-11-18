package sda;


public class TreeNode {
public String data;
public TreeNode yesNode;

public TreeNode noNode;

public TreeNode(String data) {
   this(data,null, null); 
}

public TreeNode(String data, TreeNode yesNode,
                   TreeNode noNode) {
   this.data = data;
   this.yesNode = yesNode;
   this.noNode = noNode; 
}                      
                   











}
