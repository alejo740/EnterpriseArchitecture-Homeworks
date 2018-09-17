package cs544.aop1;

import java.util.List;

public class ClassB {
    private List<ClassA> items;
    public ClassB() { System.out.println("ClassB Constructor"); }
    public void setItems(List<ClassA> items) { this.items = items; }
    public void printItems() {
        System.out.println("Items: " + items.size());
        for (ClassA a : items) {
            System.out.println(a.getText());
        }
    }
}
