package ul.info.digitalwallet.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Wallet extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference_id")
    private UUID referenceId;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = { "wallet" }, allowSetters = true)
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = { "currency", "wallet" }, allowSetters = true)
    private Set<Balance> balances = new HashSet<>();

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = { "wallet" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public Wallet id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getReferenceId() {
        return this.referenceId;
    }

    public Wallet referenceId(UUID referenceId) {
        this.setReferenceId(referenceId);
        return this;
    }

    public void setReferenceId(UUID referenceId) {
        this.referenceId = referenceId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Card> getCards() {
        return this.cards;
    }

    public void setCards(Set<Card> cards) {
        if (this.cards != null) {
            this.cards.forEach(i -> i.setWallet(null));
        }
        if (cards != null) {
            cards.forEach(i -> i.setWallet(this));
        }
        this.cards = cards;
    }

    public Wallet cards(Set<Card> cards) {
        this.setCards(cards);
        return this;
    }

    public Wallet addCard(Card card) {
        this.cards.add(card);
        card.setWallet(this);
        return this;
    }

    public Wallet removeCard(Card card) {
        this.cards.remove(card);
        card.setWallet(null);
        return this;
    }

    public Set<Balance> getBalances() {
        return this.balances;
    }

    public void setBalances(Set<Balance> balances) {
        if (this.balances != null) {
            this.balances.forEach(i -> i.setWallet(null));
        }
        if (balances != null) {
            balances.forEach(i -> i.setWallet(this));
        }
        this.balances = balances;
    }

    public Wallet balances(Set<Balance> balances) {
        this.setBalances(balances);
        return this;
    }

    public Wallet addBalance(Balance balance) {
        this.balances.add(balance);
        balance.setWallet(this);
        return this;
    }

    public Wallet removeBalance(Balance balance) {
        this.balances.remove(balance);
        balance.setWallet(null);
        return this;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setWallet(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setWallet(this));
        }
        this.transactions = transactions;
    }

    public Wallet transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public Wallet addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setWallet(this);
        return this;
    }

    public Wallet removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setWallet(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wallet)) {
            return false;
        }
        return id != null && id.equals(((Wallet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", referenceId='" + getReferenceId() + "'" +
            "}";
    }
}
