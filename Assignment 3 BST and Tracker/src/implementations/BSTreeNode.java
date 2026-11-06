package implementations;

import java.io.Serializable;

/**
 * A single node in a Binary Search Tree (BST), used as the building block
 * for BST implementations in CPRG 304 at SAIT.
 * <p>
 * Each node stores a {@link String} element and maintains references to its
 * left and right child nodes. The left child holds a value that is
 * lexicographically less than this node's element, and the right child holds
 * a value that is lexicographically greater, consistent with BST ordering
 * via {@link String#compareTo(String)}.
 * </p>
 *
 * <p><strong>Design Note – String-Specific Implementation:</strong><br>
 * This class is intentionally designed to store {@link String} elements only.
 * A fully reusable, type-safe design would use Java generics (e.g.,
 * {@code BSTreeNode<E extends Comparable<? super E>>}), allowing any
 * {@link Comparable} type to be stored in the node. Generic programming is
 * outside the scope of CPRG 304 and has therefore been omitted. All element
 * fields, parameters, and return types are fixed as {@link String} throughout
 * this class and its usages.
 * </p>
 */
public class BSTreeNode implements Serializable
{
   
}