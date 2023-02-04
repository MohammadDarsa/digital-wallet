package ul.info.digitalwallet.service.dto;

import ul.info.digitalwallet.models.Balance;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Balance} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BalanceDTO implements Serializable {

    private Long id;

    private Double amount;

    private CurrencyDTO currency;

    private WalletDTO wallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    public WalletDTO getWallet() {
        return wallet;
    }

    public void setWallet(WalletDTO wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BalanceDTO)) {
            return false;
        }

        BalanceDTO balanceDTO = (BalanceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, balanceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BalanceDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currency=" + getCurrency() +
            ", wallet=" + getWallet() +
            "}";
    }
}
