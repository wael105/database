package Data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Service {
    public final Database database;
    public Service(){
        database = Database.getDatabase();
        database.loadData();
    }

    public void Insert(){
        System.out.println("json:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String json = bufferedReader.readLine();
            database.insertJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(){
        System.out.println("Property:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String property = bufferedReader.readLine();
            System.out.println("Attribute:");
            String attribute = bufferedReader.readLine();
            System.out.println(database.get(property, attribute));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCollection(){
        System.out.println("Name:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferedReader.readLine();
            database.selectCollection(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeIndex(){
        System.out.println("Property:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String property = bufferedReader.readLine();
            database.makeIndex(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCollection(){
        System.out.println("Name:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferedReader.readLine();
            database.addCollection(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

