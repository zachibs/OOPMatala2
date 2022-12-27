package observer;

import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * This class is implementing the undo feature for StringBuilder
 * 
 * @author zachi ben shitrit, ori cohen
 * @version 1
 */

public class UndoableStringBuilder {

    StringBuilder string;
    ArrayList<StringBuilder> array;

    public UndoableStringBuilder() {
        this.string = new StringBuilder();
        array = new ArrayList<StringBuilder>();
    }

    /**
     * this function appends str to the StringBuilder
     * 
     * @param str - the string to be added
     * @return the UndoableStringBuilder object
     */
    public UndoableStringBuilder append(String str) {
        StringBuilder temp = new StringBuilder(this.string);
        this.array.add(temp);
        try {
            this.string.append(str);
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }

        return this;
    }

    /**
     * this function delete from start to end
     * 
     * @param start - the first index to start to remove from
     * @param end   - the last index to start to remove up to
     * @return the UndoableStringBuilder object
     */
    public UndoableStringBuilder delete(int start, int end) {
        StringBuilder temp = new StringBuilder(this.string);
        this.array.add(temp);

        try {
            this.string.delete(start, end);
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }

        return this;
    }

    /**
     * this function insert str at the offset index
     * 
     * @param offset - the index to insert at
     * @param str    - the string to be inserted
     * @return the UndoableStringBuilder object
     */
    public UndoableStringBuilder insert(int offset, String str) {
        StringBuilder temp = new StringBuilder(this.string);
        this.array.add(temp);

        try {
            this.string.insert(offset, str);
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }

        return this;
    }

    /**
     * this function replaces a string with the new str
     * 
     * @param start - starting index to replace from
     * @param end   - last index to replace up to
     * @param str   - the string to be put instead
     * @return the UndoableStringBuilder object
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        StringBuilder temp = new StringBuilder(this.string);
        this.array.add(temp);

        try {
            this.string.replace(start, end, str);
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }

        return this;
    }

    /**
     * this function reverses the UndoableStringBuilder
     * 
     * @return the UndoableStringBuilder object
     */
    public UndoableStringBuilder reverse() {
        StringBuilder temp = new StringBuilder(this.string);
        this.array.add(temp);

        try {
            this.string.reverse();
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }

        return this;
    }

    /**
     * @return void
     */
    public void undo() {
        try {
            StringBuilder latest = this.array.remove(this.array.size() - 1);
            this.string = latest;
        } catch (Exception e) {
            System.out.println("The Exception is: ");
            System.out.print(e);
        }
    }

    /**
     * @return
     */
    public String toString() {
        return this.string.toString();
    }

}