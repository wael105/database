package Data;


import com.google.gson.Gson;


import java.io.*;

public class Database {
    private String PATH = "D:\\savedata\\";


    public void Insert(Student student){
        createFile(student);
    }

    private void createFile(Student student){
        File file = new File("D:\\savedata\\"+student.getId()+".json");

        if(file.exists()){
            System.out.println("File Exists!");
            return;
        }
        toFile(file, student);
    }

    private void toFile(File file, Student student){
        Gson gson = new Gson();
        try(BufferedWriter write = new BufferedWriter(new FileWriter(file))){
            gson.toJson(student, write);
        } catch (IOException e){
            System.out.println(e);
        }
    }


    public Student Search(int id){
        return fromFile(id);
    }

    private Student fromFile(int id){
        Gson gson = new Gson();
        Student student = null;
        try(BufferedReader read = new BufferedReader(new FileReader("D:\\savedata\\"+id+".json"))){
            student = gson.fromJson(read, Student.class);
        } catch (IOException e){
            System.out.println(e);
        }

        return student;
    }

//TODO MUST CHANGE
    public void Update(int id, Student student){
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
        toFile(newFile, student);

    }

    //TODO make it user input
    public void Delete(int id){
        DeleteFile(id);
    }

    private void DeleteFile(int id){
        File file = new File("D:\\savedata\\"+id+".json");
        if(file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }
}
