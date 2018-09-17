package cs544.aop1;

public class ClassC extends ClassA {
    public ClassC(String text) {
        System.out.println("ClassC Constructor text: " + getText());
        setText(text);
    }
}
