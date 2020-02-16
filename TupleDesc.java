package simpledb;

import java.io.Serializable;
import java.util.*;

public class TupleDesc implements Serializable {

	private ArrayList<TDItem> arrayOfItems = new ArrayList<TDItem>();

	public static class TDItem implements Serializable {

		private static final long serialVersionUID = 1L;

		public final Type fieldType;

		public final String fieldName;

		public TDItem(Type t, String n) {
			this.fieldName = n;
			this.fieldType = t;
		}

		public String toString() {
			return fieldName + "(" + fieldType + ")";
		}
	}

	public Iterator<TDItem> iterator() {

		Iterator<TDItem> itemIterator = arrayOfItems.iterator();
		return itemIterator;

	}

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new TupleDesc with typeAr.length fields with fields of the specified
	 * types, with associated named fields.
	 * 
	 * @param typeAr  array specifying the number of and types of fields in this
	 *                TupleDesc. It must contain at least one entry.
	 * @param fieldAr array specifying the names of the fields. Note that names may
	 *                be null.
	 */
	public TupleDesc(Type[] typeAr, String[] fieldAr) {
		// some code goes here
		// 2- populate the list of TDItems
		// for (int i = 0; i < typeAr.length; i++) {
		int i = 0;
		while (i < typeAr.length) {
			// Note that names may be null fieldAr.
			if (fieldAr == null) {
				arrayOfItems.add(new TDItem(typeAr[i], null));
				// if the name not null fieldAr
			} else {
				arrayOfItems.add(new TDItem(typeAr[i], fieldAr[i]));
			}
			i++;
		}
	}

	/**
	 * Constructor. Create a new tuple desc with typeAr.length fields with fields of
	 * the specified types, with anonymous (unnamed) fields.
	 * 
	 * @param typeAr array specifying the number of and types of fields in this
	 *               TupleDesc. It must contain at least one entry.
	 */
	public TupleDesc(Type[] typeAr) {
		// some code goes here
		// 3- populate the list of TDItems

		this(typeAr, null);
	}

	/**
	 * @return the number of fields in this TupleDesc
	 */
	public int numFields() {

		return arrayOfItems.size();
	}

	/**
	 * Gets the (possibly null) field name of the ith field of this TupleDesc.
	 * 
	 * @param i index of the field name to return. It must be a valid index.
	 * @return the name of the ith field
	 * @throws NoSuchElementException if i is not a valid field reference.
	 */
	public String getFieldName(int i) throws NoSuchElementException {
		// some code goes here
		// 4- return field name of item at index i
		// if i is not valid, throw NoSuchElementException
		if (i > numFields())
			throw new NoSuchElementException();
		else
			return arrayOfItems.get(i).fieldName;
	}

	/**
	 * Gets the type of the ith field of this TupleDesc.
	 * 
	 * @param i The index of the field to get the type of. It must be a valid index.
	 * @return the type of the ith field
	 * @throws NoSuchElementException if i is not a valid field reference.
	 */
	public Type getFieldType(int i) throws NoSuchElementException {
		// some code goes here
		// 5- return field type of item at index i
		// if i is not valid, throw NoSuchElementException
		if (i > numFields())
			throw new NoSuchElementException();
		else
			return arrayOfItems.get(i).fieldType;
	}

	/**
	 * Find the index of the field with a given name.
	 * 
	 * @param name name of the field.
	 * @return the index of the field that is first to have the given name.
	 * @throws NoSuchElementException if no field with a matching name is found.
	 */

	public int fieldNameToIndex(String name) throws NoSuchElementException {
		// some code goes here
		// 6- return the index of item with name
		// throw NoSuchElementException if not exist

		int j = 0;
		// if the name entered is null
		if (name == null)
			throw new NoSuchElementException();
		else

			while (j < arrayOfItems.size()) {
				if (name.equals(this.arrayOfItems.get(j).fieldName)) {
					return j;
				}
				j++;
			}
		throw new NoSuchElementException();
	}

	/**
	 * @return The size (in bytes) of tuples corresponding to this TupleDesc. Note
	 *         that tuples from a given TupleDesc are of a fixed size.
	 */
	public int getSize() {
		int tupleSize = 0;
		int j = 0;
		while (j < arrayOfItems.size()) {
			tupleSize = tupleSize + arrayOfItems.get(j).fieldType.getLen();
			j++;
		}
		return tupleSize;
	}

	/**
	 * Merge two TupleDescs into one, with td1.numFields + td2.numFields fields,
	 * with the first td1.numFields coming from td1 and the remaining from td2.
	 * 
	 * @param td1 The TupleDesc with the first fields of the new TupleDesc
	 * @param td2 The TupleDesc with the last fields of the TupleDesc
	 * @return the new TupleDesc
	 */
	public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
		// some code goes here
		// 8- Create new TupleDesc that merge both td1 and td2

		Type[] typeAr = new Type[td1.numFields() + td2.numFields()];
		String[] fieldAr = new String[td1.numFields() + td2.numFields()];

		int j = 0, i = 0;
		while (i < td1.numFields()) {
			typeAr[i] = td1.getFieldType(i);
			fieldAr[i] = td1.getFieldName(i);
			i++;
		}

		while (j < td2.numFields()) {
			typeAr[td1.numFields() + j] = td2.getFieldType(j);
			fieldAr[td1.numFields() + j] = td2.getFieldName(j);
			j++;
		}
		// @return the new TupleDesc
		return new TupleDesc(typeAr, fieldAr);

	}

	/**
	 * Compares the specified object with this TupleDesc for equality. Two
	 * TupleDescs are considered equal if they have the same number of items and if
	 * the i-th type in this TupleDesc is equal to the i-th type in o for every i.
	 * 
	 * @param o the Object to be compared for equality with this TupleDesc.
	 * @return true if the object is equal to this TupleDesc.
	 */

	public boolean equals(Object o) {
		// some code goes here
		// 9- check type of o
		int i = 0;
		if (o instanceof TupleDesc)
			if (this.numFields() == ((TupleDesc) o).numFields())
				while (i < this.numFields())
					if (this.getFieldType(i).equals(((TupleDesc) o).getFieldType(i))) {
						return true;
					}
		return false;
	}

	public int hashCode() {
		// not required for lab1
		// If you want to use TupleDesc as keys for HashMap, implement this so
		// that equal objects have equals hashCode() results
		throw new UnsupportedOperationException("unimplemented");
	}

	/**
	 * Returns a String describing this descriptor. It should be of the form
	 * "fieldType[0](fieldName[0]), ..., fieldType[M](fieldName[M])", although the
	 * exact format does not matter.
	 * 
	 * @return String describing this descriptor.
	 */
	public String toString() {
		// some code goes here

		String s = " ";
		for (int i = 0; i < arrayOfItems.size(); i++) {
			s = s + (arrayOfItems.get(i).fieldType + "(" + arrayOfItems.get(i).fieldName + ")" + ", ...,");
		}

		return s.toString();

	}
}
