package org.riendra.learnspringboot.student;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
     private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll(){
        return repository.findAll();
    }

    public Student getById(int id){
        return repository.findById(id).orElseThrow(() ->  new StudentNotFoundException(id));

    }
    public Student addStudent(Student student){
        return repository.save(student);
    }

    public Student updateStudent(int id, Student updated) throws StudentNotFoundException {

        Student student = getById(id);

        if (updated.getName() != null && !updated.getName().isEmpty()) {
            student.setName(updated.getName());
        }
        if (updated.getEmail() != null && !updated.getEmail().isEmpty()) {
            student.setEmail(updated.getEmail());
        }

        return repository.save(student);
    }

    public void deleteStudent(int id) {
        getById(id);
        repository.deleteById(id);

    }

}
