package io.iamcyw.tower.common;

import io.iamcyw.ams.domain.common.page.Pagination;
import io.quarkus.panache.common.Page;

public class Pages {
    private Pages() {
    }

    public static Page as(Pagination pagination) {
        if (pagination == null) {
            return null;
        }
        return new Page(pagination.getPage(), pagination.getPageSize());
    }

}
