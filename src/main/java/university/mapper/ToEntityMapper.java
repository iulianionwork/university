package university.mapper;

public interface ToEntityMapper<D,E> {
    E toEntity(D dto);

}
