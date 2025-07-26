package com.edunova.commons.data.mapper;

import com.edunova.commons.data.response.PageResponse;
import org.springframework.data.domain.Page;
import java.util.function.Function;

public class PageMappingService {
    
    public static <T, R> PageResponse<R> mapToPageResponse(Page<T> page, Function<T, R> mapper) {
        Page<R> mappedPage = page.map(mapper);
        return PageResponse.of(mappedPage);
    }
}