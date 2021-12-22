import java.util.Scanner;

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
        TreeNode key;
        int[] arr = new int[]{-10,-5,-3,0,1,4,7};

        root = arrayToBST.arrayToBST(arr);
        System.out.println("Preorder traversal of constructed BST ");
        arrayToBST.preOrder(root);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose a number to search it in the tree ");
        int num = scanner.nextInt();
        key = arrayToBST.search(root,num);
        if(key == null){
            System.out.println("Number not found in the tree!");

        }else{
            System.out.println("Number is found! " + key.val);
        }

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

    // A utility function to search a given key in BST
    public TreeNode search(TreeNode root, int number)
    {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == number) {
            return root;
        }
        // Key is greater than root's key
        if (root.val < number) {
            return search(root.right, number);
        }
        // Key is smaller than root's key
        return search(root.left, number);
    }

}
