package br.com.caj.entrypoint.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.caj.domain.entity.Transfer;
import lombok.Builder;
import lombok.Data;

/**
 * Account model used on transfer account data to account domain.
 */
@Builder
@Data
public final class TransferModel implements Serializable {

  private static final long serialVersionUID = 6115832672292125749L;

  private String uuid;

  @DecimalMin(value = "0.01", message = "Balance not be empty")
  private BigDecimal amount;

  @JsonProperty("account_origin_id")
  @NotBlank(message = "Account Origin not be empty")
  private String accountOriginUuid;

  @JsonProperty("account_destination_id")
  @NotBlank(message = "Account destination not be empty")
  private String accountDestinationUuid;

  private Instant createdAt;
  private Instant updatedAt;

  /**
   * Checks that uuid is empty.
   */
  private boolean uuidIsEmpty() {
    return uuid == null || uuid.isBlank();
  }

  /**
   * Transform transfer domain into transfer app model.
   * 
   * @param transfer
   * @return
   */
  public static TransferModel fromDomain(final Transfer transfer) {
    return TransferModel.builder()
      .uuid(transfer.getUuid())
      .amount(transfer.getAmount())
      .accountOriginUuid(transfer.getAccountOriginUuid())
      .accountDestinationUuid(transfer.getAccountDestinationUuid())
      .createdAt(transfer.getCreatedAt())
      .updatedAt(transfer.getUpdatedAt())
      .build();
  }

  /**
   * Transform transfer app into transfer domain.
   * 
   * @param transfer
   * @return
   */
  public static Transfer toDomain(final TransferModel transferModel) {
    final String uuid = transferModel.uuidIsEmpty() ? UUID.randomUUID().toString() : transferModel.getUuid();

    return Transfer.builder()
      .uuid(uuid)
      .amount(transferModel.getAmount())
      .accountOriginUuid(transferModel.getAccountOriginUuid())
      .accountDestinationUuid(transferModel.getAccountDestinationUuid())
      .createdAt(transferModel.getCreatedAt())
      .updatedAt(transferModel.getUpdatedAt())
      .build();
  }
}
