package dat.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDAO<T, I> {

    T create (T t);
    T read(I i);
    T update(I i, T t);
    void delete(I i);

}
