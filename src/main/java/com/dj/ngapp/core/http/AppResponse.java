package com.dj.ngapp.core.http;

import java.util.List;

/**
 * All http responses should be in a format Sencha can understand.
 */
public class AppResponse<T> {
    private boolean success;
    private int totalResults;
    private T model;

    public AppResponse() {
    }

    public boolean getSuccess() {
        return success;
    }
    public T getModel() {
        return model;
    }

    public int getTotalResults() {
        return this.totalResults;
    }

    public static class ResponseBuilder<T> {
        private final boolean success;
        private int totalResults;
        private final T model;

        public ResponseBuilder(final boolean success, final T model) {
            this.success = success;
            this.model = model;
        }

        public ResponseBuilder<T> totalResults(final int totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        private void validate() {
            if (this.model instanceof List) {
                this.totalResults = ((List) this.model).size();
            }
        }

        public AppResponse<T> build() {
            this.validate();
            return new AppResponse<T>(this);
        }

    }

    public AppResponse(final ResponseBuilder<T> responseBuilder) {
        this.success = responseBuilder.success;
        this.model = responseBuilder.model;
        this.totalResults = responseBuilder.totalResults;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("WrappedResponse");
        sb.append("{success=").append(success);
        sb.append(", totalResults=").append(totalResults);
        sb.append(", model=").append(model);
        sb.append('}');
        return sb.toString();
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    private void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    private void setModel(T model) {
        this.model = model;
    }
}
