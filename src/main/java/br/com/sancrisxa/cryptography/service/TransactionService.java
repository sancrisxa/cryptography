package br.com.sancrisxa.cryptography.service;


import br.com.sancrisxa.cryptography.controller.dto.CreateTransactionRequest;
import br.com.sancrisxa.cryptography.controller.dto.TransactionResponse;
import br.com.sancrisxa.cryptography.controller.dto.UpdateTransactionRequest;
import br.com.sancrisxa.cryptography.entity.TransactionEntity;
import br.com.sancrisxa.cryptography.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(CreateTransactionRequest request) {
        TransactionEntity entity = new TransactionEntity();
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setRawUserDocument(request.userDocument());
        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public TransactionResponse findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEnity(entity);
    }

    public Page<TransactionResponse> listAll(int page, int pagSize) {
        var content = repository.findAll(PageRequest.of(page, pagSize));

        return content.map(TransactionResponse::fromEnity);
    }


    public void update(Long id, UpdateTransactionRequest request) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public void deleteById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.deleteById(entity.getTransactionId());
    }
}
