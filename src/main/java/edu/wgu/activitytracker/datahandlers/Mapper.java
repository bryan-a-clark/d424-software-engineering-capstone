package edu.wgu.activitytracker.datahandlers;

public interface Mapper<T, S> {
    S mapDtoToEntity(T t);
    T mapEntityToDto(S s);
}
