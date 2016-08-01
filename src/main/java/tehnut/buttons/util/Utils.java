package tehnut.buttons.util;

import net.minecraft.entity.player.EntityPlayer;

public class Utils {

    public static boolean hasPermission(EntityPlayer player) {
        return player.canCommandSenderUseCommand(2, "");
    }
}
