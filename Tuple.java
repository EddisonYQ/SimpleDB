package simpledb;

import java.io.Serializable;
import java.util.*;




//this is student comment below
/** here i basically making variables that make the skeleton of the Tuple
 * which a tuple has a tuple descriptor, multiple fields and an id for the record itself
 * @author Abdulaziz Al-Qahtani
 */
public class Tuple implements Serializable {
	private TupleDesc tDesc;
	private Field[] arrOfFields;
	private RecordId rID;
    private static final long serialVersionUID = 1L;

    
    
    /* from what i understood from reading the implementation of the Field class
     * and the TupleDesc class i found out that each TupleDesc has the info 
     * of how many fields the tuple has, and here i basically inherited the numFields method output
     */
    // constructor "Initializer"
    public Tuple(TupleDesc td) {
        this.tDesc = td;
        arrOfFields = new Field[td.numFields()];
    
    }

    // simple getter method
    public TupleDesc getTupleDesc() {
        return tDesc;
    }

    //simple getter method
    public RecordId getRecordId() {
        return rID;
    }

    // simple setter method
    public void setRecordId(RecordId rid) {
    	this.rID = rid;
    }

   // simple setter method
    public void setField(int i, Field f) {
        
    	this.arrOfFields[i] = f;
    }

    // simple getter method
    public Field getField(int i) {
        return this.arrOfFields[i];
    }

    /**
     * here i have to make a loop that goes the whole Tuple
     * and at each iteration we append \t so your program can read it correctly
     * and then we take the appended index and then we use the method getField()
     * so we get the text and then we invoke toString() explicitly to get it to go through itself again
     * and so on.
     */
    public String toString() {
    
        String outputString;
        outputString = "";
        
        for(int i = 1; i <= tDesc.numFields(); i++){
        	if(i > 0){
        				outputString = outputString + "\t";
        	}
        	outputString = outputString + this.getField(i).toString();
        }
    	outputString = outputString + "\n";
        return outputString;
    }

    
    // here i made an iterator and stored the iterations in it so i can return it later on
    public Iterator<Field> fields()
    {
    	Iterator<Field> arrayIterator = Arrays.asList(arrOfFields).iterator();
    	return arrayIterator;

    }

    // here resetting the descriptor just by assigning a null descriptor
    // which means it is the same as giving it a null value
    public void resetTupleDesc(TupleDesc td)
    {
     
    	this.tDesc = td;
    }
}
