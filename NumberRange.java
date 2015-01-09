public class NumberRange {
    private volatile int lower, upper;

    public int getLower() { return lower; }
    public int getUpper() { return upper; }

    public void setLower(int value) throws Exception { 
        if (value > upper) 
            throw new Exception();
        lower = value;
    }

    public void setUpper(int value) throws Exception { 
        if (value < lower) 
            throw new Exception();
        upper = value;
    }
}