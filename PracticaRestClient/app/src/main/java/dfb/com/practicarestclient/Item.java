package dfb.com.practicarestclient;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dante on 8/5/2017.
 */

public class Item implements Comparable<Item>{
    public int id = 0;
    public String name = "Sample";
    public String description = "Sample";
    @SerializedName("image_url")
    public String imageUrl = "http://vinrosa.com/example/pear.jpg";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        if (id != item.id) return false;
        return imageUrl.equals(item.imageUrl);

    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }


    @Override
    public int compareTo(@NonNull Item item) {

        return 0;
    }
}
