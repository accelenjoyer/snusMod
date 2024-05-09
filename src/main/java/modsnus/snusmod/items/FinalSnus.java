package modsnus.snusmod.items;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import modsnus.snusmod.Snusmod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class FinalSnus extends Item {
    public FinalSnus() {
        super(new Item.Settings().group(ItemGroup.MISC).food(new FoodComponent.Builder().hunger(3).saturationModifier(2).build()));
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (user instanceof PlayerEntity) {

            Item SwordReward = Registry.ITEM.get(new Identifier("snusmod", "custom_sword"));
            PlayerEntity player = (PlayerEntity) user;
            ItemStack RewardFirst = new ItemStack(SwordReward);
            if (!player.inventory.insertStack(RewardFirst)) {player.dropItem(RewardFirst, false);}
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200 * 20,5));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200 * 20,5));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200 * 20,5));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200 * 20,5));
            world.playSound(null, player.getX(), player.getY(), player.getZ(), Snusmod.FINAL_SOUND, SoundCategory.PLAYERS, 1.0F, 1.0F);


        }
        return super.finishUsing(stack, world, user);
    }

    }