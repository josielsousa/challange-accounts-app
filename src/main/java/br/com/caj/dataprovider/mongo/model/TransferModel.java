package br.com.caj.dataprovider.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.caj.domain.entity.Transfer;
import lombok.Builder;
import lombok.Data;

/**
 * Mapping data to persist transfer model info.
 */
@Data
@Builder
@Document("transfers")
public class TransferModel implements Serializable {
  
  /**
   *
   */
  private static final long serialVersionUID = 73798522278695720L;

  @Id
  private String uuid;

  private BigDecimal amount;
  private String accountOriginUuid;
  private String accountDestinationUuid;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
  private Instant updatedAt;

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
    return Transfer.builder()
      .uuid(transferModel.getUuid())
      .amount(transferModel.getAmount())
      .accountOriginUuid(transferModel.getAccountOriginUuid())
      .accountDestinationUuid(transferModel.getAccountDestinationUuid())
      .createdAt(transferModel.getCreatedAt())
      .updatedAt(transferModel.getUpdatedAt())
      .build();
  }
}
