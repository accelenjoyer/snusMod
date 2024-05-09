package modsnus.snusmod.blocks;
import modsnus.snusmod.EnergyManager;
import modsnus.snusmod.Snusmod;
import modsnus.snusmod.entities.EnergyContainerEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnergyContainer extends Block implements BlockEntityProvider {

    public EnergyContainer(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new EnergyContainerEntity();
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            System.out.println("onUse method is called");

            // Проверяем, достаточно ли энергии для активации генерации руды
            if (EnergyManager.energy >= 1000) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), Snusmod.ENERGY_SOUND, SoundCategory.PLAYERS, 1.0F, 1.0F);
                EnergyManager.setEnergy(EnergyManager.getEnergy()- 1000);
                System.out.println("gg");

                // Список возможных руд
                List<Block> ores = Arrays.asList(
                        Blocks.DIAMOND_ORE,
                        Blocks.EMERALD_ORE,
                        Blocks.IRON_ORE,
                        Blocks.GOLD_ORE,
                        Blocks.LAPIS_ORE,
                        Blocks.REDSTONE_ORE
                );


                Random random = new Random();
                for (int x = pos.getX() -5; x <= pos.getX() + 3; x++) {
                    for (int y = pos.getY() -5; y <= pos.getY() + 3; y++) {
                        for (int z = pos.getZ() -5 ; z <= pos.getZ() + 3; z++) {
                            BlockPos targetPos = new BlockPos(x, y, z);
                            BlockState targetState = world.getBlockState(targetPos);
                            if (targetState.isAir() && world.getBlockState(targetPos.down()).isOpaqueFullCube(world, targetPos.down())) {
                                // Выбираем случайную руду из списка и устанавливаем ее в текущую позицию
                                Block randomOre = ores.get(random.nextInt(ores.size()));
                                world.setBlockState(targetPos, randomOre.getDefaultState());
                            }
                        }
                    }
                }
            }

            // Открываем экран обработчика для блока, если он поддерживает интерфейс
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof ExtendedScreenHandlerFactory) {
                player.openHandledScreen((ExtendedScreenHandlerFactory) entity);
                System.out.println(EnergyManager.getEnergy());
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }


}
