package com.unfu.project.service.users.payload.request;

public final class SearchRequest {

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "search='" + search + '\'' +
                '}';
    }
}
