package implementations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;
import utilities.*;

/**
 * A concrete Binary Search Tree (BST) implementation for use in CPRG 304
 * at SAIT.
 * <p>
 * This class implements {@link BSTreeADT} and provides full functionality
 * for all standard BST operations, including insertion, search, removal of
 * minimum and maximum nodes, and three styles of tree traversal via
 * iterators. Elements are ordered using the natural lexicographic ordering
 * of {@link String} via {@link String#compareTo(String)}.
 * </p>
 *
 * <p><strong>Design Note – String-Specific Implementation:</strong><br>
 * This class is intentionally designed to store {@link String} elements only.
 * A fully reusable, type-safe design would use Java generics (e.g.,
 * {@code BSTree<E extends Comparable<? super E>>}), allowing any
 * {@link Comparable} type to be stored in the tree. Generic programming is
 * outside the scope of CPRG 304 and has therefore been omitted. All element
 * fields, parameters, and return types are fixed as {@link String} throughout
 * this class and its implementations.
 * </p>
 */
public class BSTree implements BSTreeADT, Serializable
{
    
}