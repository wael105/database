package Data;


import java.io.FileNotFoundException;

public class Service {
    private final StudentDAO s = new StudentDAO();


    public void Insert(Student student){

        s.insert(student);
    }

    public Student Search(int id) throws FileNotFoundException {
        return s.search(id);
    }

    public void Update(int id, Student student) throws FileNotFoundException {
        s.update(id, student);
    }

    public void Delete(int id){
        s.delete(id);
    }
}

