package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> { // Map implementation; Spring Data JPA implementation

    protected Map<ID, T> map = new HashMap<>(); // Adding and taking properties; this HashMap gets the generics of the ID and then type

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id); // return back that object out of the map
    }

    T save(ID id, T object) {
        map.put(id, object);

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) { // Delete by object
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

}
