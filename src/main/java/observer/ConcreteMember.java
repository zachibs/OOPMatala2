package observer;

public class ConcreteMember implements Member {
    
    // Attributes:
    private String memberName;
    private UndoableStringBuilder str;

    public ConcreteMember(String memberName){
        this.str = null;
        this.memberName = memberName;
    }

    
    /** 
     * Updates the latest UndoableStringBuilder for the member
     * @param usb
     * @return void
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.str = usb;
        
    }

    
    /** returns the member's values
     * @return String
     */
    @Override
    public String toString() {
        return " ConcreteMember [memberName=" + memberName + ", str=" + str + "]";
    }
    
}
