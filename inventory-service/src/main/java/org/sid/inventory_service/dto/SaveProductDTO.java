package org.sid.inventory_service.dto;

import lombok.Data;

@Data
public class SaveProductDTO {
    private String name;

    private double price;

    private int quantity;
}
