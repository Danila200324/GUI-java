package kong.world;

import kong.entity.EntityBase;
import kong.entity.component.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class World {
    private final List<EntityBase> lstEntities = new ArrayList<>();

    public void addEntity(EntityBase entity) {
        lstEntities.add(entity);
    }

    public Collection<EntityBase> getEntities() {
        return Collections.unmodifiableCollection(lstEntities);
    }

    public Stream<EntityBase> entitiesWithComponent(Class<? extends Component> componentClass) {
        return lstEntities.stream().filter(e -> e.hasComponent(componentClass));
    }
}
