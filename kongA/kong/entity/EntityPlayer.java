package kong.entity;

import kong.entity.component.*;

public class EntityPlayer extends EntityBase {
    public EntityPlayer() {
        addComponent(new ComponentLocation());
        addComponent(new ComponentPlayerMovement());
        addComponent(new ComponentDynamicCollisionBox(8, 8));
        addComponent(new ComponentStaticSprite("/char.png"));
    }
}
