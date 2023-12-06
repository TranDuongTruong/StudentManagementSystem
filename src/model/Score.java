package model;

public class Score {
    private int studentID;
    private String name;
    private float attendanceScore;
    private float regularScore;
    private float midtermScore;
    private float finalScore;
    private float totalScore;

    public Score(int studentID, String name, float attendanceScore, float regularScore, float midtermScore, float finalScore, float totalScore) {
        this.studentID = studentID;
        this.name = name;
        this.attendanceScore = attendanceScore;
        this.regularScore = regularScore;
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.totalScore = totalScore;
    }

    // Các phương thức getter cho các trường dữ liệu

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public float getAttendanceScore() {
        return attendanceScore;
    }

    public float getRegularScore() {
        return regularScore;
    }

    public float getMidtermScore() {
        return midtermScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public float getTotalScore() {
        return totalScore;
    }
}
