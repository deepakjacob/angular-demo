package com.dj.ngapp.core.http;


/**
 * Factory class for generating WrappedResponse instances.
 *
 * @author Deepak Jacob
 */
public class AppResponseGenerator {
    /**
     * Generate and return wrapped response according to the types passed.
     * <p/>
     * Note that if a <quote>java.util.List</quote> is passed, the totalResults
     * property will be automatically set.
     *
     * @param t   the model entity that needs to be converted to the
     *            <quote>WrappedResponse</quote> instance.
     * @param <T> the type, the WrappedEntity holds.
     * @return WrappedResponse instance for the passed model    .
     */
    public static <T> AppResponse<T> getResponse(final T t) {
        return null;
    }


}
