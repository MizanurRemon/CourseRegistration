package org.example.model.Entity;


import jakarta.persistence.*;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;

@Entity
@Table(name = TableConstants.TBL_SEMESTER)
public class EntitySemester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableColumnConstants.ID)
    private int id;
    @Column(name = TableColumnConstants.TITLE)
    private String title;
    @Column(name = TableColumnConstants.STATUS)
    private String status;

    public EntitySemester() {
    }

    public EntitySemester(int id, String title, String status) {
        this.id = id;
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
