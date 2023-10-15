package model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
	private List<Classroom> classroomList;

    

    public StudentManager() {
        classroomList = new ArrayList();
    }

    // Other methods
    public void addClassroom(Classroom classroom) {
        classroomList.add(classroom);
    }

    public void displayClassroomList() {
       //display
    }
    public Classroom findClassroomByCode(String classCode) {
        for (Classroom classroom : classroomList) {
            if (classroom.getClassCode().equals(classCode)) {
                return classroom;
            }
        }
        return null;
    }

    public Student findStudentById(int studentId) {
        for (Classroom classroom : classroomList) {
            for (Student student : classroom.getStudentList()) {
                if (student.getStudentID()==(studentId)) {
                    return student;
                }
            }
        }
        return null;
    }
}
