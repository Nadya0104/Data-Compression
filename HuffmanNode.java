/**
 * This class represent Huffman's tree node
 */

import java.io.Serializable;

public class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    int data; // The value represented by this node
    int frequency; // The frequency of the value  in the input data
    HuffmanNode left, right; // References to the left and right child nodes

    // Constructor to initialize a HuffmanNode with given data and frequency
    HuffmanNode(int data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    // Function that compare a node to another based on frequency
    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}
