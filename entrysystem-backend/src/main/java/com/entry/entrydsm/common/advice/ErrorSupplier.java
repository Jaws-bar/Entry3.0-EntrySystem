package com.entry.entrydsm.common.advice;

import com.entry.entrydsm.common.response.RestResponse;

@FunctionalInterface
public interface ErrorSupplier<T> {
    RestResponse.Error apply(T obj);
}
