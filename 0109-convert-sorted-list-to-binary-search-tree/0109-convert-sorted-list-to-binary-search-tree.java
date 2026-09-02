class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // Find the middle node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect left half
        if (prev != null) {
            prev.next = null;
        } else {
            // Only one node
            return new TreeNode(slow.val);
        }

        // Middle node becomes root
        TreeNode root = new TreeNode(slow.val);

        // Build left and right subtrees
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }
}