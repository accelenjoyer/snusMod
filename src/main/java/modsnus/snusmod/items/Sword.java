package modsnus.snusmod.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;


import net.minecraft.util.Identifier;

import net.minecraft.util.registry.Registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;



public class Sword extends SwordItem {


    public Sword() {
        super(ToolMaterials.DIAMOND, 11, -2.4f, new Item.Settings().group(ItemGroup.COMBAT));
    }

    public static void registerSwordItem(Identifier id, SwordItem sword) {
        Registry.register(Registry.ITEM, id, sword);
    }

    public static void onInitialize() {
        Sword sword = new Sword();
        registerSwordItem(new Identifier("snusmod", "custom_sword"), sword);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof MobEntity) {
            MobEntity mob = (MobEntity) target;
            mob.addVelocity(0, 3, 0);
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 10 * 20, 0));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            for (int i = 0; i < 2; i++) {
                VillagerEntity villager = EntityType.VILLAGER.create(world);
                if (villager != null) {

                    villager.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), world.random.nextFloat() * 360.0F, 0.0F);
                    villager.setMovementSpeed(0.2f);
                    villager.setHealth(20.0f);
                    world.spawnEntity(villager);
                }
            }
        }
        return super.use(world, user, hand);}}