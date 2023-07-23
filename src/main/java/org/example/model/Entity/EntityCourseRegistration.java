package org.example.model.Entity;


import jakarta.persistence.*;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;

@Entity
@Table(name = TableConstants.TBL_ASSIGNED_COURSES)
public class EntityCourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableColumnConstants.ID)
    private int id;
    @Column(name = TableColumnConstants.STUDENT_ID)
    private int student_id;

    @Column(name = TableColumnConstants.COURSE_ID)
    private int course_id;

    @Column(name = TableColumnConstants.SEMESTER_ID)
    private int semester_id;

    @Column(name = TableColumnConstants.STATUS)
    private String status;

    @Column(name = TableColumnConstants.CREATED_AT)
    private String created_at;

    @Column(name = TableColumnConstants.UPDATED_AT)
    private String updated_at;

    public EntityCourseRegistration() {
    }

    public EntityCourseRegistration(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public EntityCourseRegistration(int id, int student_id, int course_id, int semester_id, String status, String created_at) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.semester_id = semester_id;
        this.status = status;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(int semester_id) {
        this.semester_id = semester_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
