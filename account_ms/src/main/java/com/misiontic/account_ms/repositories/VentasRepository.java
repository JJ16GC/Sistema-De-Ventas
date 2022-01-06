package com.misiontic.account_ms.repositories;

import com.misiontic.account_ms.models.Transaction;
import com.misiontic.account_ms.models.Ventas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VentasRepository extends MongoRepository<Ventas, String> {
    List<Transaction> findByUsernameOrigin (String usernameOrigin);
}

