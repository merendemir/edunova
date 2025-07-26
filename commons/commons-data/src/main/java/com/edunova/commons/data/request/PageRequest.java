package com.edunova.commons.data.request;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@Data
public class PageRequest {
    
    @Min(value = 0, message = "{page.request.page.min}")
    private int page = 0;
    
    @Min(value = 1, message = "{page.request.size.min}")
    private int size = 10;
    
    private List<String> order;
    
    public Pageable toPageable() {
        Sort sort = Sort.unsorted();
        if (order != null && !order.isEmpty()) {
            Sort.Order[] orders = order.stream()
                .map(this::parseOrder)
                .toArray(Sort.Order[]::new);
            sort = Sort.by(orders);
        }
        return org.springframework.data.domain.PageRequest.of(page, size, sort);
    }
    
    private Sort.Order parseOrder(String orderStr) {
        String[] parts = orderStr.split(",");
        String property = parts[0].trim();
        Sort.Direction direction = parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim()) 
            ? Sort.Direction.DESC 
            : Sort.Direction.ASC;
        return new Sort.Order(direction, property);
    }
}