package simpledb;

/** Unique identifier for HeapPage objects. */
public class HeapPageId implements PageId {

	// look at the constructor to identify class attributes

	/**
	 * Constructor. Create a page id structure for a specific page of a specific
	 * table.
	 *
	 * @param tableId The table that is being referenced
	 * @param pgNo    The page number in that table.
	 */
	private int pageNumber;
	private int tableID;
	
	public HeapPageId(int tableId, int pgNo) {
		// some code goes here
		this.tableID = tableId;
		this.pageNumber = pgNo;
	}

	/** @return the table associated with this PageId */
	public int getTableId() {
		// some code goes here
		return this.tableID;
	}

	/**
	 * @return the page number in the table getTableId() associated with this PageId
	 */
	public int getPageNumber() {
		// some code goes here
		return this.pageNumber;
	}

	/**
	 * @return a hash code for this page, represented by the concatenation of the
	 *         table number and the page number (needed if a PageId is used as a key
	 *         in a hash table in the BufferPool, for example.)
	 * @see BufferPool
	 */
	/* here we are dealing with integer instance variables so it doesn't make sense to 
	   use Long Data type, and i've found that we get the results in the minus if i only use 
	   integer data types, so i decided to use Long because it holds larger number space 
	*/ 
	public int hashCode() {
		// some code goes here
		int intHashCode = (int) Long.parseLong(String.valueOf(this.tableID) + 
					String.valueOf(this.pageNumber));
		 return intHashCode;
	}

	/**
	 * Compares one PageId to another.
	 *
	 * @param o The object to compare against (must be a PageId)
	 * @return true if the objects are equal (e.g., page numbers and table ids are
	 *         the same)
	 */
	/*Object class is the superclass of all java classes, so i have to use the keyword 
	 * "instanceof" to check if it belongs to the subclass of HeapPageID
	 */
	public boolean equals(Object o) {
		// some code goes here
		// check type of o first
		 
		if(!(o instanceof HeapPageId))
			return false;
		else{
			HeapPageId instanceOfClass = (HeapPageId) o;
			boolean tablesAreEqual = instanceOfClass.getTableId() == this.getTableId();
			boolean pagesAreEqual = instanceOfClass.getPageNumber() == this.getPageNumber();
			if((tablesAreEqual) && (pagesAreEqual))
					return true;
		}
		return false;
	}

	/**
	 * Return a representation of this object as an array of integers, for writing
	 * to disk. Size of returned array must contain number of integers that
	 * corresponds to number of args to one of the constructors.
	 */
	public int[] serialize() {
		int data[] = new int[2];

		data[0] = getTableId();
		data[1] = getPageNumber();

		return data;
	}

}
