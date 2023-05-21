package kong.system;

import kong.entity.component.ComponentPlayerMovement;
import kong.world.World;

public class SystemPlayerMovement extends GameSystem {
    private int controlsDown = 0;

    public SystemPlayerMovement(World world) {
        super(world);
    }

    @Override
    public void onControlKeyDown(int action) {
        controlsDown |= 1 << action;
    }

    @Override
    public void onControlKeyUp(int action) {
        controlsDown &= ~(1 << action);
    }

    @Override
    public void onTick() {
        updateMovementComponents();
    }

    private void updateMovementComponents() {
        world.entitiesWithComponent(ComponentPlayerMovement.class).forEach(entity -> {
            ComponentPlayerMovement movementComp = entity.findComponent(ComponentPlayerMovement.class);
            updateMovementComponent(movementComp);
        });
    }

    private void updateMovementComponent(ComponentPlayerMovement comp) {
        comp.jumpAcceleration -= comp.jumpAcceleration * 0.5f;
        comp.xVel = 0;
        comp.yVel += 0.981f + comp.jumpAcceleration;
        if((controlsDown & (1 << ACTION_LEFT)) != 0) {
            comp.xVel -= comp.playerMovementSpeed;
        }
        if((controlsDown & (1 << ACTION_RIGHT)) != 0) {
            comp.xVel += comp.playerMovementSpeed;
        }
        if((controlsDown & (1 << ACTION_UP)) != 0) {
            if(comp.onGround) {
                comp.jumpAcceleration = -5.1f;
            }
        }
    }
}
