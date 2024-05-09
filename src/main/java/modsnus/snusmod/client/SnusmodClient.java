package modsnus.snusmod.client;
import modsnus.snusmod.entities.InfectedRenderer;
import modsnus.snusmod.Snusmod;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class SnusmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Snusmod.INFECTED, (dispatcher, context) -> new InfectedRenderer(dispatcher));
        ScreenRegistry.register(Snusmod.SCREEN_HANDLER_TYPE, ExampleScreen::new);
        ScreenRegistry.register(Snusmod.ENERGY_HANDLER_TYPE, EnergyScreen::new);

    }

    }




















