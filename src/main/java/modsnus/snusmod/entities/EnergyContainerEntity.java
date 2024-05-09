package modsnus.snusmod.entities;

import modsnus.snusmod.EnergyManager;
import modsnus.snusmod.blocks.EnergyScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import static modsnus.snusmod.Snusmod.ENERGY_CONTAINER_ENTITY;


public class EnergyContainerEntity extends BlockEntity implements NamedScreenHandlerFactory, ExtendedScreenHandlerFactory, Inventory {
    private DefaultedList<ItemStack> itemss = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private final EnergyManager energyManager;
    public EnergyContainerEntity() {
        super(ENERGY_CONTAINER_ENTITY);
        this.energyManager = new EnergyManager(world,pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.of("");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new EnergyScreenHandler(syncId, inv, this);
    }

    @Override
    public int size() {
        return itemss.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : itemss) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return itemss.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(itemss, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(itemss, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        itemss.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        itemss.clear();
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);

    }


    }






