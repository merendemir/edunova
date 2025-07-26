package com.edunova.commons.data.request;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@Data
public class PageableRequest {

    @Min(value = 0, message = "{page.request.page.min}")
    private int page = 0;

    @Min(value = 1, message = "{page.request.size.min}")
    private int size = 10;
    
    private List<String[]> sort;
    
    public Pageable toPageable() {
        Sort sort = Sort.unsorted();
        if (this.sort != null && !this.sort.isEmpty()) {
            Sort.Order[] orders = this.sort.stream()
                .map(this::parseSort)
                .toArray(Sort.Order[]::new);
            sort = Sort.by(orders);
        }
        return PageRequest.of(page, size, sort);
    }
    
    private Sort.Order parseSort(String[] sortArray) {
        String property = sortArray[0].trim();
        Sort.Direction direction = sortArray.length > 1 && "desc".equalsIgnoreCase(sortArray[1].trim())
            ? Sort.Direction.DESC
            : Sort.Direction.ASC;
        return new Sort.Order(direction, property);
    }
}