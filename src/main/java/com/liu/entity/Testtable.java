package com.liu.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Crazy Ones on 2017/12/29.
 */
@Entity
public class Testtable implements Serializable {
    private String id;
    private int num;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "num")
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Testtable testtable = (Testtable) o;

        if (num != testtable.num) return false;
        return id != null ? id.equals(testtable.id) : testtable.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + num;
        return result;
    }

    @Override
    public String toString() {
        return "Testtable{" +
                "id='" + id + '\'' +
                ", num=" + num +
                '}';
    }
}
