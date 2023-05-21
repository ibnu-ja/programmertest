package io.ibnuja.programmertest.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPoint {
    private int id;
    private String name;
    private int point;
}