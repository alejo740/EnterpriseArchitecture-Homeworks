package cs544.aop1;

public abstract class ClassA {
    protected String text;
    public ClassA() {
        System.out.println("ClassA Constructor");
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
