package com.digitalojt.api.dto;

import lombok.Data;

@Data
public class StockInfoSearchCriteriaDTO {
    private String categoryId;
    private String name;
    private Integer amountMin; // 以上の場合に使用
    private Integer amountMax; // 以下の場合に使用
}
