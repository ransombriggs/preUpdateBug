package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bar extends Model {

    @ManyToOne
    @JoinColumn(name = "foo_id", insertable = false, updatable = false)
    public Foo foo;

    public String txt;

}
