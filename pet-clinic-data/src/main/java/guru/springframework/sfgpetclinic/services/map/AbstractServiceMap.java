package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractServiceMap<T extends BaseEntity, ID extends Long> { // Map implementation; Spring Data JPA implementation

    protected Map<Long, T> map = new HashMap<>(); // Adding and taking properties; this HashMap gets the generics of the ID and then type

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id); // return back that object out of the map
    }

    T save(T object) {

        if(object != null){
            if(object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }

    void deleteById(ID id) {

        map.remove(id);
    }

    void delete(T object) { // Delete by object
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){ // change: ID !extends Long!
        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}
