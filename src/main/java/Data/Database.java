package Data;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Database {
    private static final Database database = new Database();
    private Jmanager jmanager;
    private JsonCollection currentCollection;

    private Database() {
        jmanager = new Jmanager();
    }

    public static Database getDatabase() {
        return database;
    }

    public void addCollection(String name) throws IOException {
        JsonCollection collection = new JsonCollection(name);
        currentCollection = collection;
        jmanager.add(collection);

        commit();
    }

    public void selectCollection(String name){
        jmanager.setCollection(name);
        currentCollection = jmanager.getCollection();
    }

    public void insertJson(String json) throws IOException {
        currentCollection.insert(json);
        commit();
    }

    public List<JsonNode> get(String property, String attribute) {
        return currentCollection.get(property, attribute);
    }

    public void makeIndex(String property) throws IOException {
        currentCollection.makeIndex(property);
        commit();
    }

    private void commit() throws IOException {
        Disk.save(jmanager, "D:\\savedata\\data.json");
    }

    protected void loadData(){
        File file = new File("D:\\savedata\\data.json");
        try{
            if(file.exists())
                jmanager = Disk.load(file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
