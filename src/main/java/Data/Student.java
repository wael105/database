package Data;



public class Student {
    private String name;
    private int id;
    Student(String name, int id){
        this.name = name;
        this.id = id;
    }


    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }


    public void setName(String name){
        this.name = name;
    }


    public void setID(int id){
        this.id = id;
    }

    public boolean SameName(String name){
        return this.name.equals(name);
    }
}
