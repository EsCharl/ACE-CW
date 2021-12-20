public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static void main(String[] args) {
        arrayToBST arrayToBST = new arrayToBST();
        TreeNode root;
        int arr[] = new int[]{-10,-5,-3,0,1,4,7};

        root = arrayToBST.arrayToBST(arr);
        System.out.println("Preorder traversal of constructed BST ");
        arrayToBST.preOrder(root);

    }
}

class arrayToBST {

    public TreeNode arrayToBST (int [] arr){
        if(arr.length == 0){
            return null;
        }
        else {
            return treeFromArray(arr,0,arr.length-1);
        }

    }
    public TreeNode treeFromArray(int [] arr, int fIndex, int lIndex) {
        if(fIndex > lIndex){
            return null;
        }
        int midpoint = fIndex + (lIndex-fIndex)/2;
        TreeNode node = new TreeNode(arr[midpoint]);
        node.left = treeFromArray(arr,fIndex,midpoint-1);
        node.right = treeFromArray(arr,midpoint+1,lIndex);
        return node;
    }

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

}


