class Solution {
    private TreeNode prev = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Check left subtree
        if (!isValidBST(root.left)) {
            return false;
        }

        // Current value must be greater than previous value
        if (prev != null && root.val <= prev.val) {
            return false;
        }

        prev = root;

        // Check right subtree
        return isValidBST(root.right);
    }
}