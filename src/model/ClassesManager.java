package model;

import java.util.ArrayList;
import java.util.List;

public class ClassesManager {
	private List<Classroom> classroomList;
    private String luachon;

	public ClassesManager() {
    	
        classroomList = new ArrayList();
        luachon="";
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
    
    public void remove(Classroom lop) {
    	classroomList.remove(lop);
	}
    public void remove(int index) {
    	classroomList.remove(index);
	}
     public String getLuachon() {
		return luachon;
	}

	public void setLuachon(String luachon) {
		this.luachon = luachon;
	}

	public List<Classroom> getClassroomList() {
		return classroomList;
	}

	public void setClassroomList(List<Classroom> classroomList) {
		this.classroomList = classroomList;
	}

	public boolean kiemTraTonTai(Classroom lop) {
		for(Classroom classroom: classroomList)
			if(classroom.getClassCode()==lop.getClassCode())
				return true;
		return false;
	}

	public void update(Classroom lop) {
		this.remove(lop);
		this.addClassroom(lop);
		
	}

	
	
}
