package com.unfu.project.service.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public final class PageDTO<T> {

    private final List<T> content;

    private final PageMetadata metadata;

    public static <T> PageDTO<T> of(Collection<T> content, PageMetadata metadata) {
        return new PageDTO<>(content, metadata);
    }

    public PageDTO(Collection<T> content, PageMetadata metadata) {
        this.content = List.copyOf(content);
        this.metadata = metadata;
    }

    @Data
    public static class PageMetadata {

        private long size;

        private long totalElements;

        private long totalPages;

        private long number;

        public PageMetadata(long size, long number, long totalElements, long totalPages) {
            Assert.isTrue(size > -1L, "Size must not be negative!");
            Assert.isTrue(number > -1L, "Number must not be negative!");
            Assert.isTrue(totalElements > -1L, "Total elements must not be negative!");
            Assert.isTrue(totalPages > -1L, "Total pages must not be negative!");
            this.size = size;
            this.number = number;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public PageMetadata(long size, long number, long totalElements) {
            this(size, number, totalElements, size == 0L ? 0L : (long)Math.ceil((double)totalElements / (double)size));
        }

        @Override
        public String toString() {
            return String.format("Metadata { number: %d, total pages: %d, total elements: %d, size: %d }",
                    this.number, this.totalPages, this.totalElements, this.size);
        }
    }
}
