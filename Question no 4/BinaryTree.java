// 4b)
// Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
// return true if the nodes corresponding to the values x and y in the tree are brothers, or false otherwise.
// Two nodes of a binary tree are brothers if they have the same depth with different parents.
// Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTree {
    // Helper method to find a target node and its parent in the tree, along with depth
    private TreeNode[] findNodeAndParent(TreeNode node, int target, TreeNode parent, int depth) {
        // If the current node is null, return null to indicate no match found
        if (node == null) {
            return null;
        }

        // If the current node's value matches the target value
        if (node.val == target) {
            // Return an array containing the target node, its parent, and its depth
            return new TreeNode[] { node, parent, new TreeNode(depth) };
        }

        // Recursively search the left subtree
        TreeNode[] leftResult = findNodeAndParent(node.left, target, node, depth + 1);
        if (leftResult[0] != null) {
            return leftResult; // If the target is found in the left subtree, return the result
        }

        // If target is not found in the left subtree, search the right subtree
        TreeNode[] rightResult = findNodeAndParent(node.right, target, node, depth + 1);
        if (rightResult[0] != null) {
            return rightResult; // If the target is found in the right subtree, return the result
        }

        // If the target is not found in either subtree, return an array indicating no match
        return new TreeNode[] { null, null, new TreeNode(-1) };
    }

    // Method to check if two nodes are brothers (have the same parent)
    public boolean areBrothers(TreeNode root, int x, int y) {
        // Find information about the target nodes x and y
        TreeNode[] xResult = findNodeAndParent(root, x, null, 0);
        TreeNode[] yResult = findNodeAndParent(root, y, null, 0);

        // Compare the depth of the nodes and their parents to determine if they are brothers
        return xResult[2].val == yResult[2].val && xResult[1] != yResult[1];
    }

    // Test case
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int x = 4;
        int y = 3;

        // Check if nodes with values x and y are brothers and print the result
        System.out.println(tree.areBrothers(root, x, y)); // Output: false
    }
}
