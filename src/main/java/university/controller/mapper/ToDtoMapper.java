package university.controller.mapper;

public interface ToDtoMapper<E,D> {
    D toDto(E entity);

}
