/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
// package definition commented by A. Kawaguchi.
// package org.apache.commons.collections.comparators;
//
// I was forced to alter the name of this class because Eclipse
//   was giving me name collisions.
package libraryBase;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * <p>A CompChain is a Comparator that wraps one or
 * more Comparators in sequence.  The CompChain
 * calls each Comparator in sequence until either 1)
 * any single Comparator returns a non-zero result
 * (and that result is then returned),
 * or 2) the CompChain is exhausted (and zero is
 * returned).  This type of sorting is very similar
 * to multi-column sorting in SQL, and this class
 * allows Java classes to emulate that kind of behaviour
 * when sorting a List.</p>
 * 
 * <p>To further facilitate SQL-like sorting, the order of
 * any single Comparator in the list can be reversed.</p>
 * 
 * <p>Calling a method that adds new Comparators or
 * changes the ascend/descend sort <i>after compare(Object,
 * Object) has been called</i> will result in an
 * UnsupportedOperationException.  However, <i>take care</i>
 * to not alter the underlying List of Comparators
 * or the BitSet that defines the sort order.</p>
 * 
 * <p>Instances of CompChain are not synchronized.
 * The class is not thread-safe at construction time, but
 * it <i>is</i> thread-safe to perform multiple comparisons
 * after all the setup operations are complete.</p>
 * 
 * @since Commons Collections 2.0
 * @author Morgan Delagrange
 * @version $Revision: 646777 $ $Date: 2008-04-10 13:33:15 +0100 (Thu, 10 Apr 2008) $
 */
public class CompChain implements Comparator, Serializable {

    /** Serialization version from Collections 2.0. */
    private static final long serialVersionUID = -721644942746081630L;
    
    /** The list of comparators in the chain. */
    protected List CompChain = null;
    /** Order - false (clear) = ascend; true (set) = descend. */
    protected BitSet orderingBits = null;
   /** Whether the chain has been "locked". */
    protected boolean isLocked = false;

    //-----------------------------------------------------------------------
    /**
     * Construct a CompChain with no Comparators.
     * You must add at least one Comparator before calling
     * the compare(Object,Object) method, or an 
     * UnsupportedOperationException is thrown
     */
    public CompChain() {
        this(new ArrayList(),new BitSet());
    }

    /**
     * Construct a CompChain with a single Comparator,
     * sorting in the forward order
     * 
     * @param comparator First comparator in the Comparator chain
     */
    public CompChain(Comparator comparator) {
        this(comparator,false);
    }

    /**
     * Construct a Comparator chain with a single Comparator,
     * sorting in the given order
     * 
     * @param comparator First Comparator in the CompChain
     * @param reverse    false = forward sort; true = reverse sort
     */
    public CompChain(Comparator comparator, boolean reverse) {
        CompChain = new ArrayList();
        CompChain.add(comparator);
        orderingBits = new BitSet(1);
        if (reverse == true) {
            orderingBits.set(0);
        }
    }

    /**
     * Construct a CompChain from the Comparators in the
     * List.  All Comparators will default to the forward 
     * sort order.
     * 
     * @param list   List of Comparators
     * @see #CompChain(List,BitSet)
     */
    public CompChain(List list) {
        this(list,new BitSet(list.size()));
    }

    /**
     * Construct a CompChain from the Comparators in the
     * given List.  The sort order of each column will be
     * drawn from the given BitSet.  When determining the sort
     * order for Comparator at index <i>i</i> in the List,
     * the CompChain will call BitSet.get(<i>i</i>).
     * If that method returns <i>false</i>, the forward
     * sort order is used; a return value of <i>true</i>
     * indicates reverse sort order.
     * 
     * @param list   List of Comparators.  NOTE: This constructor does not perform a
     *               defensive copy of the list
     * @param bits   Sort order for each Comparator.  Extra bits are ignored,
     *               unless extra Comparators are added by another method.
     */
    public CompChain(List list, BitSet bits) {
        CompChain = list;
        orderingBits = bits;
    }

    //-----------------------------------------------------------------------
    /**
     * Add a Comparator to the end of the chain using the
     * forward sort order
     * 
     * @param comparator Comparator with the forward sort order
     */
    public void addComparator(Comparator comparator) {
        addComparator(comparator,false);
    }

    /**
     * Add a Comparator to the end of the chain using the
     * given sort order
     * 
     * @param comparator Comparator to add to the end of the chain
     * @param reverse    false = forward sort order; true = reverse sort order
     */
    public void addComparator(Comparator comparator, boolean reverse) {
        checkLocked();
        
        CompChain.add(comparator);
        if (reverse == true) {
            orderingBits.set(CompChain.size() - 1);
        }
    }

    /**
     * Replace the Comparator at the given index, maintaining
     * the existing sort order.
     * 
     * @param index      index of the Comparator to replace
     * @param comparator Comparator to place at the given index
     * @exception IndexOutOfBoundsException
     *                   if index &lt; 0 or index &gt;= size()
     */
    public void setComparator(int index, Comparator comparator) 
    throws IndexOutOfBoundsException {
        setComparator(index,comparator,false);
    }

    /**
     * Replace the Comparator at the given index in the
     * CompChain, using the given sort order
     * 
     * @param index      index of the Comparator to replace
     * @param comparator Comparator to set
     * @param reverse    false = forward sort order; true = reverse sort order
     */
    public void setComparator(int index, Comparator comparator, boolean reverse) {
        checkLocked();

        CompChain.set(index,comparator);
        if (reverse == true) {
            orderingBits.set(index);
        } else {
            orderingBits.clear(index);
        }
    }


    /**
     * Change the sort order at the given index in the
     * CompChain to a forward sort.
     * 
     * @param index  Index of the CompChain
     */
    public void setForwardSort(int index) {
        checkLocked();
        orderingBits.clear(index);
    }

    /**
     * Change the sort order at the given index in the
     * CompChain to a reverse sort.
     * 
     * @param index  Index of the CompChain
     */
    public void setReverseSort(int index) {
        checkLocked();
        orderingBits.set(index);
    }

    /**
     * Number of Comparators in the current CompChain.
     * 
     * @return Comparator count
     */
    public int size() {
        return CompChain.size();
    }

    /**
     * Determine if modifications can still be made to the
     * CompChain.  CompChains cannot be modified
     * once they have performed a comparison.
     * 
     * @return true = CompChain cannot be modified; false = 
     *         CompChain can still be modified.
     */
    public boolean isLocked() {
        return isLocked;
    }

    // throw an exception if the CompChain is locked
    private void checkLocked() {
        if (isLocked == true) {
            throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
        }
    }

    private void checkChainIntegrity() {
        if (CompChain.size() == 0) {
            throw new UnsupportedOperationException("CompChains must contain at least one Comparator");
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Perform comparisons on the Objects as per
     * Comparator.compare(o1,o2).
     * 
     * @param o1  the first object to compare
     * @param o2  the second object to compare
     * @return -1, 0, or 1
     * @exception UnsupportedOperationException
     *                   if the CompChain does not contain at least one
     *                   Comparator
     */
    public int compare(Object o1, Object o2) throws UnsupportedOperationException {
        if (isLocked == false) {
            checkChainIntegrity();
            isLocked = true;
        }

        // iterate over all comparators in the chain
        Iterator comparators = CompChain.iterator();
        for (int comparatorIndex = 0; comparators.hasNext(); ++comparatorIndex) {

            Comparator comparator = (Comparator) comparators.next();
            int retval = comparator.compare(o1,o2);
            if (retval != 0) {
                // invert the order if it is a reverse sort
                if (orderingBits.get(comparatorIndex) == true) {
                    if(Integer.MIN_VALUE == retval) {
                        retval = Integer.MAX_VALUE;
                    } else {                        
                        retval *= -1;
                    }
                }

                return retval;
            }

        }

        // if comparators are exhausted, return 0
        return 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Implement a hash code for this comparator that is consistent with
     * {@link #equals(Object) equals}.
     * 
     * @return a suitable hash code
     * @since Commons Collections 3.0
     */
    public int hashCode() {
        int hash = 0;
        if(null != CompChain) {
            hash ^= CompChain.hashCode();
        }
        if(null != orderingBits) {
            hash ^= orderingBits.hashCode();
        }
        return hash;
    }

    /**
     * Returns <code>true</code> iff <i>that</i> Object is 
     * is a {@link Comparator} whose ordering is known to be 
     * equivalent to mine.
     * <p>
     * This implementation returns <code>true</code>
     * iff <code><i>object</i>.{@link Object#getClass() getClass()}</code>
     * equals <code>this.getClass()</code>, and the underlying 
     * comparators and order bits are equal.
     * Subclasses may want to override this behavior to remain consistent
     * with the {@link Comparator#equals(Object)} contract.
     * 
     * @param object  the object to compare with
     * @return true if equal
     * @since Commons Collections 3.0
     */
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        } else if(null == object) {
            return false;
        } else if(object.getClass().equals(this.getClass())) {
            CompChain chain = (CompChain)object;
            return ( (null == orderingBits ? null == chain.orderingBits : orderingBits.equals(chain.orderingBits))
                   && (null == CompChain ? null == chain.CompChain : CompChain.equals(chain.CompChain)) );
        } else {
            return false;
        }
    }

}
