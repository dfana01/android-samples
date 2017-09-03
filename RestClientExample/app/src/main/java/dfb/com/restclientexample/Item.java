package dfb.com.restclientexample;

/**
 * Created by Dante on 8/5/2017.
 */

public class Item {
    public String label;
    public String description;
    public int priority;

    @Override
    public String toString() {
        return "Item{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
