package br.com.sancrisxa.cryptography.controller.dto;

import br.com.sancrisxa.cryptography.entity.TransactionEntity;

public record TransactionResponse(Long id, String userDocument, String creditCardToken, Long value) {

    public static TransactionResponse fromEnity(TransactionEntity entity) {
        return new TransactionResponse(
                entity.getTransactionId(),
                entity.getRawUserDocument(),
                entity.getRawCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}
