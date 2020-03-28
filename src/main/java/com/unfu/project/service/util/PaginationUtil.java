package com.unfu.project.service.util;

import com.unfu.project.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public final class PaginationUtil {

    public static <T> PageDTO<T> valueOf(Page<T> page) {
        PageDTO.PageMetadata pageMetadata = new PageDTO.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements());
        return PageDTO.of(page.getContent(), pageMetadata);
    }

    public static <T> PageDTO<T> of(Collection<T> list, Pageable pageable) {
        Page<T> page = fromList(List.copyOf(list), pageable);
        PageDTO.PageMetadata pageMetadata = new PageDTO.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements());
        return PageDTO.of(page.getContent(), pageMetadata);
    }

    public static <T> Page<T> fromList(List<T> list, Pageable pageable) {
        int page = pageable.getPageNumber();
        int total = list.size() / pageable.getPageSize();
        if (pageable.getPageNumber() > total)
            throw new BadRequestException("Page number: " + page + " can not be more than max: " + total);
        int countPerPage = page * pageable.getPageSize() + pageable.getPageSize() < list.size() ? pageable.getPageSize() : list.size() - page * pageable.getPageSize();
        return new PageImpl<>(list.subList(page * pageable.getPageSize(), page * pageable.getPageSize() + countPerPage), pageable, list.size());
    }
}
