package kong.ui;

import kong.entity.EntityPlayer;
import kong.entity.EntityStaticObject;
import kong.system.GameSystem;
import kong.system.GameSystemManager;
import kong.system.SystemPlayerMovement;
import kong.system.SystemVelocity;
import kong.world.World;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KongFrame extends JFrame {
    public KongFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        World world = new World();
        GameSystemManager sysmgr = new GameSystemManager(world);
        sysmgr.addSystem(new SystemPlayerMovement(world));
        sysmgr.addSystem(new SystemVelocity(world));
        {
            EntityStaticObject entity = new EntityStaticObject("/bridge_tb.png", 128, 8);
            entity.getLocationComponent().set(112, 80);
            world.addEntity(entity);
        }
        {
            EntityStaticObject entity = new EntityStaticObject("/bridge_bt.png", 128, 8);
            entity.getLocationComponent().set(128, 128);
            world.addEntity(entity);
        }
        {
            EntityPlayer charEntity = new EntityPlayer();
            charEntity.getLocationComponent().set(128, 116);
            world.addEntity(charEntity);
        }

        KongRenderView rv = new KongRenderView(world);
        setContentPane(rv);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        sysmgr.onControlKeyDown(GameSystem.ACTION_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        sysmgr.onControlKeyDown(GameSystem.ACTION_RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        sysmgr.onControlKeyDown(GameSystem.ACTION_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        sysmgr.onControlKeyDown(GameSystem.ACTION_DOWN);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        sysmgr.onControlKeyUp(GameSystem.ACTION_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        sysmgr.onControlKeyUp(GameSystem.ACTION_RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        sysmgr.onControlKeyUp(GameSystem.ACTION_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        sysmgr.onControlKeyUp(GameSystem.ACTION_DOWN);
                        break;
                }
            }
        });
        Timer timer = new Timer(33, ev -> {
            sysmgr.onTick();
            rv.repaint();
        });
        timer.start();

        setSize(640, 480);
        setResizable(false);
    }


}
