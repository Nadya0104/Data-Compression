/**
 * This class represent Huffman encoding and decoding algorithm
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Huffman {
    // Hashmap stores each data with their specific code
    public static HashMap<Integer, String> huffmanCodeMap = new HashMap<>();

    /**
     * Function that build the Huffman tree based on the data list.
     * @param data is the data list that need to compress.
     * @return the root node of Huffman's tree.
     * */
    public static HuffmanNode buildHuffmanTree(List<Integer> data) {
        // Count frequency of each value
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : data) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }
        // Create priority queue and add nodes based on frequency
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (int key : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(key, frequencyMap.get(key)));
        }
        // Build the Huffman tree by combining the two least frequent nodes
        while (priorityQueue.size() > 1) {
            // Extract the two nodes with the lowest frequency
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode node = new HuffmanNode(-1, left.frequency + right.frequency); // Create a new internal node with these two nodes as children
            node.left = left;
            node.right = right;
            priorityQueue.add(node);  // Add the new node back into the priority queue
        }
        HuffmanNode root = priorityQueue.peek();// The remaining node is the root of the Huffman Tree
        generateHuffmanCodes(root, ""); // Generate Huffman codes based on the tree
        return root;
    }

    /**
     * Auxiliary function that generates the Huffman codes for each leaf node.
     * @param node represents a node in the Huffman tree, starting from the root.
     * @param code is the Huffman code being built as we traverse the tree.
     * */
    private static void generateHuffmanCodes(HuffmanNode node, String code) {
        if (node.left == null && node.right == null) {
            huffmanCodeMap.put(node.data, code);
            return;
        }
        generateHuffmanCodes(node.left, code + "0");
        generateHuffmanCodes(node.right, code + "1");
    }

    /**
     * Function that converts the data list to Huffman code (data compression)
     * @param data is the data list that need to compress
     * @return the compressed data as a String
     * */
    public static String compress(List<Integer> data) {
        StringBuilder compressedData = new StringBuilder(); // Store the compressed data as a binary string
        // Iterate through the data list and convert each value to its Huffman code
        for (int value : data) {
            compressedData.append(huffmanCodeMap.get(value));
        }
        return compressedData.toString(); // Return the compressed data as a String
    }

    /**
     * Function that converts compressed data to original data (decompression)
     * @param compressedData is the compressed data as a String
     * @param root is the Huffman's tree root node
     * @return the decompressed data as List
     * */
    public static List<Integer> decompress(String compressedData, HuffmanNode root) {
        List<Integer> decompressedData = new ArrayList<>(); // Create a list to store the decompressed data
        HuffmanNode currentNode = root;
        // Iterate through each bit in the compressed data string
        for (char bit : compressedData.toCharArray()) {
            currentNode = (bit == '0') ? currentNode.left : currentNode.right; // Traverse the Huffman tree: move left for '0' and right for '1'
            if (currentNode.left == null && currentNode.right == null) { // If it's a leaf node
                decompressedData.add(currentNode.data); // Add the data value at the leaf node to the decompressed data list
                currentNode = root; // Reset the current node back to the root
            }
        }
        return decompressedData;
    }
}
