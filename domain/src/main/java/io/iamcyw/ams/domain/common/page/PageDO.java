package io.iamcyw.ams.domain.common.page;

import java.util.List;

public class PageDO<T> {
    List<T> payload;

    Pagination pagination;

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
