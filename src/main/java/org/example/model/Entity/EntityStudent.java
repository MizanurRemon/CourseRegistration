package org.example.model.Entity;


import jakarta.persistence.*;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;

@Entity
@Table(name = TableConstants.TBL_STUDENT)
public class EntityStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableColumnConstants.ID)
    private int id;
    @Column(name = TableColumnConstants.NAME)
    private String name;
    @Column(name = TableColumnConstants.PHONE)
    private String phone;

    @Column(name = TableColumnConstants.IMAGE)
    private byte[] image;
    @Column(name = TableColumnConstants.ROLL_NO)
    private String roll_no;

    @Column(name = TableColumnConstants.STATUS)
    private String status;
    @Column(name = TableColumnConstants.CREATED_AT)
    private String created_at;
    @Column(name = TableColumnConstants.UPDATED_AT)
    private String updated_at;

    public EntityStudent() {
    }

    public EntityStudent(int id, String name, String phone, byte[] image, String roll_no, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.roll_no = roll_no;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public EntityStudent(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
