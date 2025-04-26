package io.github.nagpalankit.creditcardprocessor.repository;

import io.github.nagpalankit.creditcardprocessor.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
