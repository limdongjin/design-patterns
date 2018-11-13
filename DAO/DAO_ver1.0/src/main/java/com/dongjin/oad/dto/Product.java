package com.dongjin.oad.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Product {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private int price;
}