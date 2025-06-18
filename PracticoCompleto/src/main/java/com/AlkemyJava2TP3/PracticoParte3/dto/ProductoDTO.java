package com.AlkemyJava2TP3.PracticoParte3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean enStock;

}
