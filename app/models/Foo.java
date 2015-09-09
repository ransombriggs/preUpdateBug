package models;

import org.hibernate.annotations.IndexColumn;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Foo extends Model {

    public String txt;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "foo_id", nullable = false)
    @IndexColumn(name = "sequence_number")
    public List<Bar> bars = new ArrayList<Bar>();

    public Long prePersistCalled = 0L;

    @Version
    public Long version;

    @PreUpdate
    public void prePersist() {
        this.prePersistCalled = this.prePersistCalled + 1;
    }

}
