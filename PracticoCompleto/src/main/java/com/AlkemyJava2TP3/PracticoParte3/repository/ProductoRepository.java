package com.AlkemyJava2TP3.PracticoParte3.repository;

import com.AlkemyJava2TP3.PracticoParte3.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends MongoRepository<Producto, String> {
}
