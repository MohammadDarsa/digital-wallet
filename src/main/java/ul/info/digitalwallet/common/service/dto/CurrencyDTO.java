package ul.info.digitalwallet.common.service.dto;

import ul.info.digitalwallet.common.models.Currency;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Currency} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CurrencyDTO implements Serializable {

    private Long id;

    private String isoName;

    private Double exchangeValue;

    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public Double getExchangeValue() {
        return exchangeValue;
    }

    public void setExchangeValue(Double exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyDTO)) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, currencyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", isoName='" + getIsoName() + "'" +
            ", exchangeValue=" + getExchangeValue() +
            ", imagePath='" + getImagePath() + "'" +
            "}";
    }
}
