import java.util.*;
/**
 * RowCompare knows how to compare to rows of a table.
 * This class can be used with Arrays.sort(Obj[],RowCompare).
 * @author Jason Miller
 */
public class RowCompare implements Comparator<Object> {
    int sortColumn = 0;
    /**
     * Constructor.
     * @param col Designate the column on which rows shall be sorted. Start counting at zero.
     */
    public RowCompare (int col) {
        this.sortColumn = col;
    }

    /**
     * compare()
     * This method should be invoked indirectly by calling Arrays.sort().
     * If both parameters seem to be table rows (type Object []), 
     * then extract the nth value of both rows, 
     * where n is the sorting column provided to the constructor of this class.
     * If both values are String or Integer or Double,
     * then invoke first.compareTo(second). 
     * In all other situations, this method returns 0, meaning rows are equal.
     * @param obj1 One row of table stored as Object [] [].
     * @param obj2 Another row of a table stored as Object [] [].
     */
    public int compare (Object obj1, Object obj2) {
        if (obj1 instanceof Object[] && obj2 instanceof Object[]) {
            Object [] row1 = (Object[]) obj1;
            Object [] row2 = (Object[]) obj2;
            Object cmp1 = row1[sortColumn];
            Object cmp2 = row2[sortColumn];
            if ( cmp1 instanceof String && cmp2 instanceof String) {
                String str1 = (String)cmp1;
                String str2 = (String)cmp2;
                return str1.compareTo(str2);
            }
            if ( cmp1 instanceof Integer && cmp2 instanceof Integer) {
                Integer int1 = (Integer)cmp1;                
                Integer int2 = (Integer)cmp2;
                return int1.compareTo(int2);
            }
            if ( cmp1 instanceof Double && cmp2 instanceof Double) {
                Double dbl1 = (Double) cmp1;
                Double dbl2 = (Double) cmp2;
                return dbl1.compareTo(dbl2);
            }
        }
        return 0;
    }

}
