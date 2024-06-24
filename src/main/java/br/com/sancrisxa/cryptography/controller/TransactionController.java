package br.com.sancrisxa.cryptography.controller;


import br.com.sancrisxa.cryptography.controller.dto.CreateTransactionRequest;
import br.com.sancrisxa.cryptography.controller.dto.TransactionResponse;
import br.com.sancrisxa.cryptography.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var body = service.listAll(page, pageSize);
        return ResponseEntity.ok(body);
    }
}
