package ul.info.digitalwallet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Card.
 */
@Entity
@Table(name = "card")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Card extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "masked_pan")
    private String maskedPan;

    @Column(name = "expiry")
    private LocalDate expiry;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "expired")
    private Boolean expired;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "cards", "balances", "transactions" }, allowSetters = true)
    private Wallet wallet;


    public Long getId() {
        return this.id;
    }

    public Card id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public Card token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMaskedPan() {
        return this.maskedPan;
    }

    public Card maskedPan(String maskedPan) {
        this.setMaskedPan(maskedPan);
        return this;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public LocalDate getExpiry() {
        return this.expiry;
    }

    public Card expiry(LocalDate expiry) {
        this.setExpiry(expiry);
        return this;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Card displayName(String displayName) {
        this.setDisplayName(displayName);
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getExpired() {
        return this.expired;
    }

    public Card expired(Boolean expired) {
        this.setExpired(expired);
        return this;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Card wallet(Wallet wallet) {
        this.setWallet(wallet);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        return id != null && id.equals(((Card) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Card{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", maskedPan='" + getMaskedPan() + "'" +
            ", expiry='" + getExpiry() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", expired='" + getExpired() + "'" +
            "}";
    }
}
