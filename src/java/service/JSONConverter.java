package service;

public abstract class JSONConverter {
    private String source;
    private int index;
    private JSONItem item;
    
    public JSONConverter(String source){
        this.source = source;
        this.index = 0;
        this.item = new JSONItem(null, 0);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public void passIndex(){
        this.index++;
    }

    public JSONItem getItem() {
        return item;
    }

    public void setItem(JSONItem item) {
        this.item = item;
    }
            
    public JSONItem getNextItem(){
        String itemValue = "";
        char current = getSource().charAt(getIndex());
        
        while(current!='}'&&current!=']'){
            if(current != '[' && current != '{' && current != ']'){
                itemValue += current;
            }
            passIndex();
            current = getSource().charAt(getIndex());
        }
        
        if(current == '\t' || current == '\n' || current == '}' || current == ',' || current == ' '){
            passIndex(); //Jump char , or space char
        }
        else {
            return null;
        }
        
        getItem().setValue(itemValue);
        getItem().setIndex(0);
        
        return getItem();
    }
}
