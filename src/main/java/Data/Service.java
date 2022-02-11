package Data;


public class Service {
    private final StudentDAO s = new StudentDAO();


    public void Insert(Student student){

        s.Insert(student);
    }

    public Student Search(int id){
        return s.Search(id);
    }

    public void Update(int id, Student student){
        s.Update(id, student);
    }

    public void Delete(int id){
        s.Delete(id);
    }
}

