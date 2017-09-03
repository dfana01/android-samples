package dfb.com.restclientexample;

/**
 * Created by Dante on 8/5/2017.
 */

public class FormResponse {
    public String message;
    public Form form;

    @Override
    public String toString() {
        return "FormResponse{" +
                "message='" + message + '\'' +
                ", form=" + form +
                '}';
    }
}
