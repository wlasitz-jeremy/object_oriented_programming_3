package utilities;

import java.io.Serializable;
import implementations.BSTreeNode;

/**
 * A Binary Search Tree (BST) ADT interface used as the foundation for tree
 * data structures in CPRG 304 at SAIT.
 * <p>
 * This interface defines the structure and expected behaviour of a BST
 * implementation, including standard operations such as insertion, search,
 * removal, and traversal. Implementing classes are responsible for providing
 * full functionality for all declared operations. Elements are ordered using
 * the natural ordering of {@link String} (lexicographic order via
 * {@link String#compareTo(String)}).
 * </p>
 *
 * <p><strong>Design Note – String-Specific Implementation:</strong><br>
 * This interface is intentionally designed to work with {@link String} elements
 * only. A fully reusable, type-safe design would use Java generics (e.g.,
 * {@code BSTreeADT<E extends Comparable<? super E>>}), allowing any
 * {@link Comparable} type to be stored in the tree. Generic programming is
 * outside the scope of CPRG 304 and has therefore been omitted. All element
 * parameters and return types are fixed as {@link String} throughout this
 * interface and its implementations.
 * </p>
 */
public interface BSTreeADT extends Serializable
{
    /**
     * Returns the root node of this binary search tree.
     * <p>
     * The root is the topmost node in the tree from which all other nodes
     * are reachable. If the tree is empty, this method throws a
     * {@link NullPointerException} rather than returning {@code null}, to
     * signal that no root exists.
     * </p>
     *
     * @return the root {@link BSTreeNode} of this tree
     * @throws NullPointerException if the tree is empty and no root exists
     */
    public BSTreeNode getRoot() throws NullPointerException;

    /**
     * Returns the height of this binary search tree.
     * <p>
     * The height is defined as the number of edges on the longest path from
     * the root to a leaf node. An empty tree has a height of {@code 0}.
     * A tree containing only a root node has a height of {@code 1}.
     * </p>
     *
     * @return the height of the tree; {@code 0} if the tree is empty
     */
    public int getHeight();

    /**
     * Returns the number of {@link String} elements currently stored in
     * this tree.
     *
     * @return the total number of nodes in the tree; never negative
     */
    public int size();

    /**
     * Returns {@code true} if this tree contains no elements.
     *
     * @return {@code true} if {@link #size()} is {@code 0};
     *         {@code false} otherwise
     */
    public boolean isEmpty();

    /**
     * Removes all elements from this tree, leaving it empty.
     * After this call, {@link #size()} returns {@code 0} and
     * {@link #isEmpty()} returns {@code true}.
     */
    public void clear();

    /**
     * Returns {@code true} if this tree contains the specified
     * {@link String} element.
     * <p>
     * The search uses lexicographic ordering via
     * {@link String#compareTo(String)} to navigate the tree.
     * </p>
     *
     * @param entry  the {@link String} element to search for;
     *               must not be {@code null}
     * @return {@code true} if the element exists in the tree;
     *         {@code false} otherwise
     * @throws NullPointerException if {@code entry} is {@code null}
     */
    public boolean contains( String entry ) throws NullPointerException;

    /**
     * Searches for and returns the {@link BSTreeNode} containing the
     * specified {@link String} element.
     * <p>
     * The search uses lexicographic ordering via
     * {@link String#compareTo(String)} to navigate the tree. If the element
     * is not found, {@code null} is returned.
     * </p>
     *
     * @param entry  the {@link String} element to search for;
     *               must not be {@code null}
     * @return the {@link BSTreeNode} containing the element, or {@code null}
     *         if no such node exists in the tree
     * @throws NullPointerException if {@code entry} is {@code null}
     */
    public BSTreeNode search( String entry ) throws NullPointerException;

    /**
     * Inserts the specified {@link String} element into this binary search
     * tree in its correct lexicographic position.
     * <p>
     * The insertion position is determined by comparing the new element to
     * existing nodes using {@link String#compareTo(String)}. Elements less
     * than a node are placed in the left subtree; elements greater are placed
     * in the right subtree. Duplicate elements are not permitted.
     * </p>
     *
     * @param newEntry  the {@link String} element to insert;
     *                  must not be {@code null}
     * @return {@code true} if the element was successfully inserted;
     *         {@code false} if the element already exists in the tree
     * @throws NullPointerException if {@code newEntry} is {@code null}
     */
    public boolean add( String newEntry ) throws NullPointerException;

    /**
     * Returns, without removing, the {@link BSTreeNode} containing the
     * lexicographically smallest {@link String} element in this tree.
     * <p>
     * The minimum element is located at the leftmost node in the tree, as
     * determined by lexicographic ordering via {@link String#compareTo(String)}.
     * Unlike {@link #removeMin()}, this method leaves the tree structure
     * completely unchanged.
     * </p>
     *
     * @return the {@link BSTreeNode} containing the lexicographically
     *         smallest element, or {@code null} if the tree is empty
     */
    public BSTreeNode findMin();

    /**
     * Returns, without removing, the {@link BSTreeNode} containing the
     * lexicographically largest {@link String} element in this tree.
     * <p>
     * The maximum element is located at the rightmost node in the tree, as
     * determined by lexicographic ordering via {@link String#compareTo(String)}.
     * Unlike {@link #removeMax()}, this method leaves the tree structure
     * completely unchanged.
     * </p>
     *
     * @return the {@link BSTreeNode} containing the lexicographically
     *         largest element, or {@code null} if the tree is empty
     */
    public BSTreeNode findMax();

    /**
     * Removes and returns the {@link BSTreeNode} containing the smallest
     * {@link String} element in this tree.
     * <p>
     * The smallest element is the leftmost node in the tree, as determined
     * by lexicographic ordering. If the tree is empty, {@code null} is
     * returned.
     * </p>
     *
     * @return the {@link BSTreeNode} containing the lexicographically
     *         smallest element, or {@code null} if the tree is empty
     */
    public BSTreeNode removeMin();

    /**
     * Removes and returns the {@link BSTreeNode} containing the largest
     * {@link String} element in this tree.
     * <p>
     * The largest element is the rightmost node in the tree, as determined
     * by lexicographic ordering. If the tree is empty, {@code null} is
     * returned.
     * </p>
     *
     * @return the {@link BSTreeNode} containing the lexicographically
     *         largest element, or {@code null} if the tree is empty
     */
    public BSTreeNode removeMax();

    /**
     * Returns an {@link Iterator} that traverses the {@link String} elements
     * of this tree in <strong>in-order</strong> sequence (left, root, right).
     * <p>
     * In-order traversal visits nodes in ascending lexicographic order,
     * producing a sorted sequence of all elements in the tree.
     * </p>
     *
     * @return an {@link Iterator} over the {@link String} elements of this
     *         tree in ascending lexicographic order
     */
    public Iterator inorderIterator();

    /**
     * Returns an {@link Iterator} that traverses the {@link String} elements
     * of this tree in <strong>pre-order</strong> sequence (root, left, right).
     * <p>
     * Pre-order traversal visits the root node before its subtrees, which is
     * useful for producing a copy or serialized representation of the tree.
     * </p>
     *
     * @return an {@link Iterator} over the {@link String} elements of this
     *         tree in pre-order sequence
     */
    public Iterator preorderIterator();

    /**
     * Returns an {@link Iterator} that traverses the {@link String} elements
     * of this tree in <strong>post-order</strong> sequence (left, right, root).
     * <p>
     * Post-order traversal visits the root node after its subtrees, which is
     * useful for safely deleting or evaluating the tree bottom-up.
     * </p>
     *
     * @return an {@link Iterator} over the {@link String} elements of this
     *         tree in post-order sequence
     */
    public Iterator postorderIterator();
}