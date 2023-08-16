package com.caswen.base.autoservice;

import java.util.ServiceLoader;

public final class CaswenServiceLoader {
    private CaswenServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
