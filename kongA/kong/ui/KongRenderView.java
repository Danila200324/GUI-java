package kong.ui;

import kong.entity.EntityBase;
import kong.entity.component.ComponentLocation;
import kong.entity.component.ComponentStaticSprite;
import kong.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class KongRenderView extends JComponent {
    private final World world;

    public KongRenderView(World world) {
        this.world = world;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (EntityBase entity : world.getEntities()) {
            ComponentLocation location = entity.getLocationComponent();
            ComponentStaticSprite sprite = (ComponentStaticSprite) entity.findComponent(ComponentStaticSprite.class);

            if (location != null && sprite != null) {
                BufferedImage image = sprite.getImage();
                g.drawImage(
                        image,
                        (int) (location.x - image.getWidth(this) / 2),
                        (int) (location.y - image.getHeight(this) / 2),
                        this
                );
            }
        }
    }
}
