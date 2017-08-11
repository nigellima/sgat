package service;

public class JSONItem {
    private String value;
    private int index;

    public JSONItem(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index){
        this.index = index;
    }

    public void passIndex() {
        this.index++;
    }
    
    public String getStringValue(String str){
        String attStr = "\"" + str + "\":";
        String val = "";
        char current;
        
        setIndex(getValue().indexOf(attStr) + attStr.length());
        current = getValue().charAt(getIndex());
        
        while(current != ',' && getIndex() < getValue().length()-1){
            if(current!='"'){
                val += current;
            }
            passIndex();
            current = getValue().charAt(getIndex());
        }
        
        return val;
    }
    
    public int getIntValue(String str){
        return Integer.parseInt(getStringValue(str));
    }
    
    public boolean getBoolValue(String str){
        return Boolean.parseBoolean(getStringValue(str));
    }
}
