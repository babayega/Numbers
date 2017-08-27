package Events.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by babayega on 24/08/17.
 */

@Entity
@Table(name = "numbers")
@NamedQueries({
        @NamedQuery(name = "Find all numbers",
                    query = "select n from Numbers n"),
        @NamedQuery(name = "Find by number",
                    query = "select n.number from Numbers n where n.number = :number")
})
public class Numbers {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int id;

    public int getId() {
        return id;
    }

    public Numbers() {
    }

    public void setId(int id) {

        this.id = id;
    }

    public Numbers(long number) {
        this.number = number;
    }

    @Column(name = "number")

    private long number;

    @JsonProperty
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
