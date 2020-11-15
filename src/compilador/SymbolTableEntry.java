package compilador;

public class SymbolTableEntry {
    public SymbolTableEntry(String token, String type){
        setToken(token);
        setType(type);
    }
    public SymbolTableEntry(String token, String type, Object value, Integer length){
        setToken(token);
        setType(type);
        setVal(value);
        setLen(length);
    }
    private String token;
    private String type;
    private Object val;
    private Integer len;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }
}
