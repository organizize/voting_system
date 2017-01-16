package voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String personId;
    private String firstName;
    private String lastName;
    @JsonIgnore
    @ManyToOne
    private District district;
    @JsonIgnore
    @ManyToOne
    private Party party;

    public Candidate() {
    }

    public Candidate(String personId, String firstName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id) &&
                Objects.equals(personId, candidate.personId) &&
                Objects.equals(firstName, candidate.firstName) &&
                Objects.equals(lastName, candidate.lastName) &&
                Objects.equals(district, candidate.district) &&
                Objects.equals(party, candidate.party);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, firstName, lastName, district, party);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", district=" + district +
                ", party=" + party +
                '}';
    }
}
