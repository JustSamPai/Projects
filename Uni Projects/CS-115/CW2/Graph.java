import java.util.ArrayList;

/**
 * The class Graph.
 */
public class Graph {
    private BST tree;
    private String file;
    /**
     * The Graph.
     */
    // Creates a new graph.
    Graph graph = new Graph(tree, "graph.txt");

    /**
     * Instantiates a new Graph.
     *
     * @param tree the tree
     * @param file the file
     */
    // Constructor for Graph.
    public Graph(BST tree, String file) {
        this.tree = tree;
        this.file = file;


    }

    /**
     * Create graph.
     */
    public void createGraph() {


    }

    /**
     * Get profiles array list.
     *
     * @param profiles the profiles
     * @return the array list
     */
    public ArrayList<Profile> getProfiles(ArrayList<Profile> profiles) {
        return null;

    }

    /**
     * Friend recommendations array list.
     *
     * @param profiles the profiles
     * @return the array list
     */
    public ArrayList<BST> friendRecommendations(ArrayList<Profile> profiles) {
        return null;
    }
}