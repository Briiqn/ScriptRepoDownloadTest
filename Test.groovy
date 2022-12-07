package briqn.scripts


import briqn.bad.client.event.impl.PacketEvent
import briqn.bad.client.event.impl.RenderOverlayEvent
import briqn.bad.client.event.impl.UpdateEvent
import briqn.bad.client.module.Module
import briqn.bad.client.module.ModuleManager
import briqn.bad.client.utils.misc.ChatUtils
import com.google.common.eventbus.Subscribe
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C0BPacketEntityAction


public static void init() {
    ModuleManager.modules.add(new Example());
}
init()
public class Example extends Module {



    public Example() {
        //name = "example"; description = "test" keybind=0x0 (none), Category= Script"
        super("Test", "test", 0x0, Category.SCRIPT);
    }
    @Override
    //Called When Mod is toggled
    public void onEnable() {ChatUtils.clientMessage("Hello World")  }
    @Subscribe
    //Called When Overlay is Rendered, Eg Blindness,Pumpkin,Gaurdian fx,etc
    public void onRenderOverlay(RenderOverlayEvent event) {
        if(mc.thePlayer != null)
            mc.thePlayer.removePotionEffect(15)

    }
    @Subscribe
    //Called when Packet is sent or recieved

    public void onPacket(PacketEvent event) {
        if(event.packet instanceof  C0BPacketEntityAction) {
            ChatUtils.clientMessage("Sent an Action Packet")
        }
    }


    @Subscribe
    //always called
    public void onUpdate(UpdateEvent event) {
        if(mc.thePlayer.motionY > 0) {
            ChatUtils.clientMessage("Your MotionY is " + mc.thePlayer.motionY + ":Your Onground status is " + mc.thePlayer.onGround + ":Your PosY is " + mc.thePlayer.posY)
        }
    }

    @Override
    //Called When Module is toggled off
    protected void onDisable() {
        ChatUtils.clientMessage("This is example")

    }
}