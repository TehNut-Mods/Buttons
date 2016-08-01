package tehnut.buttons;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.buttons.config.ConfigHandler;
import tehnut.buttons.network.MessageButtonClicked;
import tehnut.buttons.network.MessageDeleteItem;
import tehnut.buttons.network.MessageLoadInventory;
import tehnut.buttons.proxy.CommonProxy;

import java.io.File;

@Mod(modid = Buttons.MODID, name = Buttons.NAME, version = Buttons.VERSION, guiFactory = "tehnut.buttons.gui.config.GuiFactoryButtons")
public class Buttons {

    public static final String MODID = "buttons";
    public static final String NAME = "Buttons";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(clientSide = "tehnut.buttons.proxy.ClientProxy", serverSide = "tehnut.buttons.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Buttons instance;

    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final SimpleNetworkWrapper NETWORK_INSTANCE = new SimpleNetworkWrapper(MODID);

    public static File configDir;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), "Buttons");
        ConfigHandler.init(new File(configDir, NAME + ".cfg"));
        NETWORK_INSTANCE.registerMessage(MessageButtonClicked.Handler.class, MessageButtonClicked.class, 0, Side.SERVER);
        NETWORK_INSTANCE.registerMessage(MessageDeleteItem.Handler.class, MessageDeleteItem.class, 1, Side.SERVER);
        NETWORK_INSTANCE.registerMessage(MessageLoadInventory.Handler.class, MessageLoadInventory.class, 2, Side.SERVER);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        proxy.startup();
    }

    public static void debug(String message, Object... args) {
        if (ConfigHandler.isDebugMode())
            LOGGER.info(message, args);
    }
}
