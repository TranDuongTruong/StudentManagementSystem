package controller;

import java.util.List;

import model.Student;
import view.StudentView;

public class ListStudentController {
    private final StudentView studentView;

    public ListStudentController(StudentView studentView) {
        this.studentView = studentView;
    }

    public void displayListOfStudent(List<Student> studentList) {
        studentView.displayStudentList(studentList);
    }
}