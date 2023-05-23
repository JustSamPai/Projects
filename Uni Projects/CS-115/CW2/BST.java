/**
 * The type Bst.
 */
public class BST {

    /**
     * The Root.
     */
    public BSTNode root;

    /**
     * Instantiates a new Bst.
     */
    BST(){
        this.root = null;
    }


    /**
     * Add profile.
     *
     * @param p the p
     */
    public void addProfile(Profile p){
        if(root == null){
            root = new BSTNode(p);
        } else {
            addNode(root, new BSTNode(p));
        }
    }
    /**
     * Add node.
     *
     * @param currentNode,newNode
     */
    private BSTNode addNode(BSTNode currentNode, BSTNode newNode){
        //creates a string for current node and next node, these get the first name
        String currentName = currentNode.getProfile().getFirstName();
        String nextName =   newNode.getProfile().getFirstName();
        //allows the program to compare target node to current
        int comp = nextName.compareTo(currentName);

        //this makes it so that children are placed blow parent and root nodes
        // (in the correct place)
        if (comp<0){
            if(currentNode.getLeft() == null){
                currentNode.setLeft(newNode);
            } else {
                currentNode.setLeft(addNode(currentNode.getLeft(), newNode));
            }
        } else if (comp>0){
            if(currentNode.getRight() == null){
                currentNode.setRight(newNode);
            } else {
                currentNode.setRight(addNode(currentNode.getRight(), newNode));
            }
        } else if (comp==0){
            if(currentNode.getLeft() == null){
                currentNode.setLeft(newNode);
            } else {
                currentNode.setLeft(addNode(currentNode.getLeft(), newNode));
            }
        }

        return currentNode;
    }

    /**
     * Print alphabetical asc.
     * this prints information from node
     */
    public void printAlphabeticalASC(){

        if(root != null)
        {
            BSTNode traverseNode = root;
            if(traverseNode.getLeft() == null && traverseNode.getRight() == null)
            {
                System.out.println(traverseNode.getProfile().getFirstName());
            }
            else
            {
                if(traverseNode.getLeft() != null)
                {
                    inOrderTraversal(traverseNode.getLeft());
                }
                System.out.println(traverseNode.getProfile().getFirstName());
                if(traverseNode.getRight() != null)
                {
                    inOrderTraversal(traverseNode.getRight());
                }
            }
        }




    }

    /**
     * In order traversal.
     *
     * the way the information is handled/sorted
     *
     * @param node the node
     */
    public void inOrderTraversal(BSTNode node)
    {
        if(node.getLeft() != null)
        {
            inOrderTraversal(node.getLeft());
        }
        System.out.println(node.getProfile().getFirstName());
        if(node.getRight() != null)
        {
            inOrderTraversal(node.getRight());
        }



    }








}
