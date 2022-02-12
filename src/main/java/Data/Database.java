package Data;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;


import java.io.*;

public class Database {
    private static final String PATH = "D:\\savedata\\data.json";
    private static final File file = new File(PATH);
    private static final Gson gson = new GsonBuilder().create();

    public void insert(Student student){
    if (exists(student)) System.out.println("Student exists");
    else toFile(student);
    }

    private boolean exists(Student student){
        if(search(student.getId()) != null)
            return true;
        return false;
    }

    private void toFile(Student student){
        try(BufferedWriter write = new BufferedWriter(new FileWriter(file,true))){
            gson.toJson(student, write);
        } catch (IOException e){
            System.out.println(e);
        }
    }


    public Student search(int id) {
        return fromFile(id);
    }

    private Student fromFile(int id) {
        Student s = null;
        try{
            Reader r = new BufferedReader(new FileReader(PATH));
            JsonStreamParser p = new JsonStreamParser(r);
            while (p.hasNext()) {
                JsonElement e = p.next();
                s = gson.fromJson(e, Student.class);
                if(s.getId() == id)
                    return s;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return s;
    }

//TODO MUST CHANGE
    public void update(int id, Student student){
        editFile(id, student);
    }

    //I think i can clean this more
    private void editFile(int id, Student student){
        File oldFile = new File("D:\\savedata\\"+id+".json");

        if(!oldFile.exists()){
            System.out.println("File Does Not Exist!");
            return ;
        }

        File newFile = new File("D:\\savedata\\"+student.getId()+".json");

        if (newFile.exists()){
            System.out.println("A File with the same ID already exists!");
            return ;
        }

        boolean success = oldFile.renameTo(newFile);

        if (!success) {
            System.out.println("File was not successfully renamed");
        }
        //toFile(newFile, student);

    }

    //TODO make it user input
    public void delete(int id){
        deleteFile(id);
    }

    private void deleteFile(int id){
        if(file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }
}
