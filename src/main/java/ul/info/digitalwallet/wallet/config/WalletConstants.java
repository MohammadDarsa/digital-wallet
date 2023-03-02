package ul.info.digitalwallet.wallet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "wallet.app")
@Configuration
@Data
public class WalletConstants {
    private String bankPanUsd;
    private String bankPanLbp;
}
