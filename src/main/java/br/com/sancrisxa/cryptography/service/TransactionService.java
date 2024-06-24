package br.com.sancrisxa.cryptography.service;


import br.com.sancrisxa.cryptography.controller.dto.CreateTransactionRequest;
import br.com.sancrisxa.cryptography.controller.dto.TransactionResponse;
import br.com.sancrisxa.cryptography.entity.TransactionEntity;
import br.com.sancrisxa.cryptography.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<TransactionResponse> listAll(int page, int pagSize) {
        var content = repository.findAll(PageRequest.of(page, pagSize));

        return content.map(TransactionResponse::fromEnity);

    }


}
