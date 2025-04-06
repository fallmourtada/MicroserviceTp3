package org.sid.order_service.modele;

import lombok.Data;

@Data
public class Customer {
    private Long id;

    private String name;

    private String email;

}