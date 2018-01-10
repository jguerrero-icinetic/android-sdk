package com.aplazame.sdk.network.mapper;

public interface Mapper<T, S> {

    S transformDomainToDto(T t);
}
