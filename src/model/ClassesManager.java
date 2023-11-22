package model;

import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConnection;
import controller.Admin.LoginController;

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
    public Classroom findClassroomByCodeRegister(String classCodeRegister) {
        for (Classroom classroom : classroomList) {
            if (classroom.getClass_registration_code().equals(classCodeRegister)) {
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
	public Classroom getClassroom(int index) {
		return this.getClassroomList().get(index);
	}
	public boolean CheckClass(Classroom lop) {
		for(Classroom classroom: classroomList)
			if(classroom.getClassCode()==lop.getClassCode())
				return true;
		return false;
	}

	public void update(Classroom lop) {
		this.remove(lop);
		this.addClassroom(lop);
		
	}

	public void updateClassroom(Classroom updatedClassroom) {
	    for (int i = 0; i < classroomList.size(); i++) {
	        Classroom classroom = classroomList.get(i);
	        if (classroom.getClassCode().equals(updatedClassroom.getClassCode())) {
	            classroomList.set(i, updatedClassroom);
	            break;
	        }
	    }
	}

	
	
}
