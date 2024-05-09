package modsnus.snusmod;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyManager {
    private final World world;
    private final BlockPos pos;
   public static int energy = 0;
    public EnergyManager(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int value) {
        energy = value;


}

    }
