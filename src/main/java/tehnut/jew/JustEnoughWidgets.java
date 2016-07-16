package tehnut.jew;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.jew.config.ConfigHandler;
import tehnut.jew.network.MessageButtonClicked;
import tehnut.jew.network.MessageDeleteItem;
import tehnut.jew.proxy.CommonProxy;

import java.io.File;

@Mod(modid = JustEnoughWidgets.MODID, clientSideOnly = true, name = JustEnoughWidgets.NAME, version = JustEnoughWidgets.VERSION)
public class JustEnoughWidgets {

    public static final String MODID = "justenoughwidgets";
    public static final String NAME = "JustEnoughWidgets";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(clientSide = "tehnut.jew.proxy.ClientProxy", serverSide = "tehnut.jew.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static JustEnoughWidgets instance;

	public static final Logger LOGGER = LogManager.getLogger(NAME);
	public static final SimpleNetworkWrapper NETWORK_INSTANCE = new SimpleNetworkWrapper(MODID);

	public static File configDir;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		configDir = new File(event.getModConfigurationDirectory(), "JEW");
		ConfigHandler.init(new File(configDir, NAME + ".cfg"));
		NETWORK_INSTANCE.registerMessage(MessageButtonClicked.Handler.class, MessageButtonClicked.class, 0, Side.SERVER);
		NETWORK_INSTANCE.registerMessage(MessageDeleteItem.Handler.class, MessageDeleteItem.class, 1, Side.SERVER);

		proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
    }

	@Mod.EventHandler
	public void idMapping(FMLModIdMappingEvent event) {
		proxy.startup(event);
	}

	public static void debug(String message, Object... args) {
		if (ConfigHandler.isDebugMode())
			LOGGER.info(message, args);
	}
}
