package ul.info.digitalwallet.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Currency extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iso_name")
    private String isoName;

    @Column(name = "exchange_value")
    private Double exchangeValue;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(mappedBy = "currency")
    @JsonIgnoreProperties(value = { "currency", "wallet" }, allowSetters = true)
    private Set<Balance> balances = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public Currency id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsoName() {
        return this.isoName;
    }

    public Currency isoName(String isoName) {
        this.setIsoName(isoName);
        return this;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public Double getExchangeValue() {
        return this.exchangeValue;
    }

    public Currency exchangeValue(Double exchangeValue) {
        this.setExchangeValue(exchangeValue);
        return this;
    }

    public void setExchangeValue(Double exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Currency imagePath(String imagePath) {
        this.setImagePath(imagePath);
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Set<Balance> getBalances() {
        return this.balances;
    }

    public void setBalances(Set<Balance> balances) {
        if (this.balances != null) {
            this.balances.forEach(i -> i.setCurrency(null));
        }
        if (balances != null) {
            balances.forEach(i -> i.setCurrency(this));
        }
        this.balances = balances;
    }

    public Currency balances(Set<Balance> balances) {
        this.setBalances(balances);
        return this;
    }

    public Currency addBalance(Balance balance) {
        this.balances.add(balance);
        balance.setCurrency(this);
        return this;
    }

    public Currency removeBalance(Balance balance) {
        this.balances.remove(balance);
        balance.setCurrency(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return id != null && id.equals(((Currency) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", isoName='" + getIsoName() + "'" +
            ", exchangeValue=" + getExchangeValue() +
            ", imagePath='" + getImagePath() + "'" +
            "}";
    }
}
