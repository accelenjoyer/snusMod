package modsnus.snusmod;
import modsnus.snusmod.blocks.EnergyContainer;
import modsnus.snusmod.blocks.EnergyScreenHandler;
import modsnus.snusmod.blocks.ExampleBlock;
import modsnus.snusmod.blocks.ExampleScreenHandler;
import modsnus.snusmod.entities.EnergyContainerEntity;
import modsnus.snusmod.entities.ExampleBlockEntity;
import modsnus.snusmod.items.FinalSnus;
import modsnus.snusmod.items.Sword;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.fabricmc.api.ModInitializer;


public class Snusmod implements ModInitializer {


    public static final Block ENERGY_CONTAINER = new EnergyContainer(FabricBlockSettings.of(Material.METAL).strength(4.0F));
    public static final BlockEntityType<EnergyContainerEntity> ENERGY_CONTAINER_ENTITY;
    public static final Block EXAMPLE_BLOCK = new ExampleBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final BlockEntityType<ExampleBlockEntity> EXAMPLE_BLOCK_ENTITY;
    public static final Identifier ID = new Identifier("snusmod", "altar");
    public static ScreenHandlerType<ExampleScreenHandler> SCREEN_HANDLER_TYPE;
    public static ScreenHandlerType<EnergyScreenHandler> ENERGY_HANDLER_TYPE;
    public static final Identifier SCREEN_HANDLER_ID = new Identifier("snusmod", "screen");
    public static final Identifier SCREEN_HANDLER_IDY = new Identifier("snusmod", "energy_screen");
    public static final EntityType<Infected> INFECTED = Registry.register(Registry.ENTITY_TYPE,new Identifier("snusmod","infected"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,Infected::new).dimensions(EntityDimensions.fixed(2f,2f)).build());
    public static final  Identifier INFECTED_SOUND_ID = new Identifier("snusmod","infected_sound");
    public static final SoundEvent INFECTED_SOUND = new SoundEvent(INFECTED_SOUND_ID);
    public static final  Identifier FINAL_SOUND_ID = new Identifier("snusmod","final_sound");
    public static final SoundEvent FINAL_SOUND = new SoundEvent(FINAL_SOUND_ID);
    public static final Identifier ENERGY_SOUND_ID = new Identifier("snusmod","energy_sound");
    public static final SoundEvent ENERGY_SOUND = new SoundEvent(ENERGY_SOUND_ID);
    public static final Item FINAL_SNUS = new FinalSnus();
    public static final Identifier FINAL_SNUS_ID = new Identifier("snusmod", "final_snus");
    static {

        EXAMPLE_BLOCK_ENTITY     = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                ID,
                BlockEntityType.Builder.create(ExampleBlockEntity::new, EXAMPLE_BLOCK).build(null));
        ENERGY_CONTAINER_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,new Identifier("snusmod","energycontainer"),BlockEntityType.Builder.create(EnergyContainerEntity::new, ENERGY_CONTAINER).build(null)
        );

    }

    @Override
    public void onInitialize() {



        Registry.register(Registry.BLOCK,new Identifier("snusmod","energycontainer"),ENERGY_CONTAINER);
        Sword.onInitialize();
        Registry.register(Registry.ITEM,new Identifier("snusmod","energycontainer"),new BlockItem(ENERGY_CONTAINER,new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM,FINAL_SNUS_ID,FINAL_SNUS);
        Registry.register(Registry.BLOCK, ID, EXAMPLE_BLOCK);
        Registry.register(Registry.ITEM, ID, new BlockItem(EXAMPLE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerExtended(SCREEN_HANDLER_ID,
                (syncId, inv, buf) -> {
                    BlockPos pos = buf.readBlockPos();
                    World world = inv.player.world;
                    Inventory blockInventory = getBlockInventory(world, pos);
                    return new ExampleScreenHandler(syncId, inv, blockInventory);
                });
        ENERGY_HANDLER_TYPE = ScreenHandlerRegistry.registerExtended(SCREEN_HANDLER_IDY,
                (syncId, inv, buf) -> {
                    BlockPos pos = buf.readBlockPos();
                    World world = inv.player.world;
                    Inventory blockInventory = getBlockInventory(world, pos);
                    return new EnergyScreenHandler(syncId, inv, blockInventory);
                });
        StatusEffectInstance hungerEffect = new StatusEffectInstance(StatusEffects.HUNGER, 20 * 20, 1);
        StatusEffectInstance weaknessEffect = new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * 20, 1);
        StatusEffectInstance strengthEffect = new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 20, 1);
        StatusEffectInstance poisonEffect = new StatusEffectInstance(StatusEffects.POISON, 20 * 20, 1);
        StatusEffectInstance waterEffect = new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20 * 20, 1);
        StatusEffectInstance visionEffect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 20, 1);
        StatusEffectInstance defenderEffect = new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 20, 1);
        StatusEffectInstance fireEffect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20 * 20, 1);
        StatusEffectInstance regenEffect = new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 20, 1);

        registerCustomItem(new StatusEffectInstance[]{hungerEffect, weaknessEffect, poisonEffect}, "custom_item_1");
        registerCustomItem(new StatusEffectInstance[]{hungerEffect, waterEffect}, "custom_item_2");
        registerCustomItem(new StatusEffectInstance[]{strengthEffect, visionEffect}, "custom_item_3");
        registerCustomItem(new StatusEffectInstance[]{strengthEffect, visionEffect, fireEffect, regenEffect, defenderEffect}, "custom_item_4");
        FabricDefaultAttributeRegistry.register(INFECTED,Infected.createMobAttributes());

    }

    private void registerCustomItem(StatusEffectInstance[] effects, String name) {
        FoodComponent.Builder builder = new FoodComponent.Builder();
        for (StatusEffectInstance effect : effects) {
            builder.statusEffect(effect, 1.0f);
        }
        FoodComponent foodComponent = builder.hunger(2).saturationModifier(0.5f).build();
        Item item = new Item(new Item.Settings().group(ItemGroup.MISC).food(foodComponent)) {
            @Override
            public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
                if (user instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) user;

                     {
                     
                    }
                    applyStatusEffects(player);
                }
                return super.finishUsing(stack, world, user);
            }

            private void applyStatusEffects(PlayerEntity player) {
                for (StatusEffectInstance effect : effects) {
                    player.addStatusEffect(new StatusEffectInstance(effect));
                }
            }
        };
        Registry.register(Registry.ITEM, new Identifier("snusmod", name), item);

    }
    private Inventory getBlockInventory(World world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof Inventory) {
            return (Inventory) world.getBlockEntity(pos);
        }
        return null;
    }


        }





