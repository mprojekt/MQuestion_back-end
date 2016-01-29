package m.mquestion.utility;

public interface EntityToDtoConverter<T, U> {
    
    public U convert(T entity);

}
