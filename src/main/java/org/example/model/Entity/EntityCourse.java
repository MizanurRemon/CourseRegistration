package org.example.model.Entity;

import jakarta.persistence.*;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;

@Entity
@Table(name = TableConstants.TBL_COURSE)
public class EntityCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableColumnConstants.ID)
    private int id;

    @Column(name = TableColumnConstants.TITLE)
    private String title;
    @Column(name = TableColumnConstants.CREDITS)
    private int credits;
    @Column(name = TableColumnConstants.STATUS)
    private String status;

    public EntityCourse() {
    }

    public EntityCourse(int id, String title, int credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public EntityCourse(int id, String title, int credits, String status) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
