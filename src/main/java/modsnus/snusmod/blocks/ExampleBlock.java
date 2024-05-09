package modsnus.snusmod.blocks;
import modsnus.snusmod.EnergyManager;
import modsnus.snusmod.entities.ExampleBlockEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
public class ExampleBlock extends Block implements BlockEntityProvider {
    public ExampleBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView World) {
        return new ExampleBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (EnergyManager.getEnergy() >= 1000) {
               System.out.println("gg");
            }
            if (entity instanceof ExtendedScreenHandlerFactory) {
                player.openHandledScreen((ExtendedScreenHandlerFactory) entity);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState();
    }

}