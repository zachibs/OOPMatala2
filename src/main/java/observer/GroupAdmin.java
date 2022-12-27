package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{
    
    // Attributes:
    private UndoableStringBuilder situationsGroup = new UndoableStringBuilder();
    private ArrayList<Member> membersList = new ArrayList<>();

    
    /** Register a member to the members list
     * @param obj
     * @return void
     */
    @Override
    public void register(Member obj) {
        if(!this.membersList.contains(obj)){
            this.membersList.add(obj);
        } else{
            System.out.println("Member already existed in the list");
        }
        
    }

    
    /** Delete a member from the members list
     * @param obj
     * @return void
     */
    @Override
    public void unregister(Member obj) {
        try{
            this.membersList.remove(obj);
        } catch (Exception e){
            System.out.println("Unsuccessful to remove member, exception: " + e);
        }
        
    }

    
    /** Insert the given string into the char sequence
     * @param offset
     * @param obj
     * @return void
     */
    @Override
    public void insert(int offset, String obj) {
        this.situationsGroup.insert(offset, obj);
        this.Notify();
        
    }

    
    /** Appends the given string to the char sequence
     * @param obj
     * @return void
     */
    @Override
    public void append(String obj) {
        this.situationsGroup.append(obj);
        this.Notify();        
    }

    
    /** Deletes from the given start index to the end index from the char sequence
     * @param start
     * @param end
     * @return void
     */
    @Override
    public void delete(int start, int end) {
        this.situationsGroup.delete(start, end);       
        this.Notify(); 
    }

    
    /** Undo's the last change that happened to the char sequence
     * @return void
     */
    @Override
    public void undo() {
        this.situationsGroup.undo();
        this.Notify();        
    }

    
    /** A function that updates the latest UndoableStringBuilder to all the members
     * @return void
     */
    private void Notify(){
        for(Member memb: this.membersList){
            memb.update(this.situationsGroup);
        }

    }

    
    /** To print the value of the group admin in a readable way
     * @return void
     */
    @Override
    public String toString() {
        String str = "GroupAdmin [situationsGroup= " + situationsGroup.toString();
        str += "], [membersList=";
        for (Member memb: this.membersList){
            str += memb.toString();
        }
        return str;
    }

}