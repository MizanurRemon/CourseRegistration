package org.example.model.response;

public class CourseRegistrationResponse {
    public int id;

    public String status;

    public SemesterResponse semester;
    public CourseResponse course;

    public StudentResponse student;
    public String created_at;
    public String updated_at;
}
