package br.com.caj.entrypoint;

import java.io.Serializable;
import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.caj.domain.entity.Transfer;
import br.com.caj.domain.usecase.TransferUseCase;
import br.com.caj.entrypoint.model.TransferModel;

/**
 * Transfers endpoint controller.
 */
@RestController
public final class TransferController implements Serializable {

  private static final long serialVersionUID = 1294052063724022826L;

  private TransferUseCase transferUseCase;

  /**
   * Default constructor.
   * 
   * @param transferUseCase
   */
  public TransferController(final TransferUseCase transferUseCase) {
    this.transferUseCase = transferUseCase;
  }

  /**
   * Returns an set of all transfers avaliable.
   * 
   * @param uuid
   * @return
   */
  @GetMapping("/transfers/{uuid}")
  public ResponseEntity<?> listAccounts(
      @PathVariable("uuid") @Valid @NotBlank(message = "UUID not be empty") final String uuid) {
    final Set<TransferModel> transfers = transferUseCase.getAllTransfers(uuid).stream().map(TransferModel::fromDomain)
        .collect(Collectors.toSet());
    return ResponseEntity.ok(transfers);
  }

  /**
   * Create a new transfer from data received.
   * 
   * @param transferModel
   * @return
   */
  @PostMapping("/transfer")
  public ResponseEntity<?> create(@Valid @RequestBody final TransferModel transferModel) {
    final Transfer accountCreated = transferUseCase.create(TransferModel.toDomain(transferModel));
    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}")
        .buildAndExpand(accountCreated.getUuid()).toUri();
    return ResponseEntity.created(location).build();
  }
}
