package modsnus.snusmod.entities;

import modsnus.snusmod.Infected;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Identifier;

public class InfectedRenderer extends MobEntityRenderer<Infected, BipedEntityModel<Infected>> {

    public InfectedRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new BipedEntityModel<>(0.0F), 0.5F);

    }

    @Override
    public Identifier getTexture(Infected entity) {

        return new Identifier("snusmod", "textures/entity/infected.png");
    }
}
