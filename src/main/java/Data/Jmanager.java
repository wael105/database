package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Jmanager implements Serializable {
    private JsonCollection currentCollection;
    private final ArrayList<JsonCollection> collections;

    Jmanager(){
        collections = new ArrayList<>();
    }

    public void add(JsonCollection collection) {
        currentCollection = collection;
        collections.add(collection);
    }

    public void setCollection(String name){
        JsonCollection collection = loopCollections(name);
        if (collection != null)
            currentCollection = collection;
        else
            System.out.println("Collection doesn't exist");
    }

    public JsonCollection getCollection(String name) {
        return loopCollections(name);
    }

    private JsonCollection loopCollections(String name){
        for (JsonCollection collection : collections){
            if(collection.getName().equals(name))
                return collection;
        }
        return null;
    }

    public JsonCollection getCollection(){
        return currentCollection;
    }

    public ArrayList<JsonCollection> getCollections(){
        return collections;
    }

}
