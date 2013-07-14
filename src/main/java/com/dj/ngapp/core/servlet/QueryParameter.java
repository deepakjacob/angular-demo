package com.dj.ngapp.core.servlet;

public class QueryParameter {

    private int limit = 99;
    private int offset = 0;

    public QueryParameter() {
    }

    public QueryParameter(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

}

