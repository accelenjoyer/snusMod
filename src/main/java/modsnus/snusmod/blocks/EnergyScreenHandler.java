package modsnus.snusmod.blocks;

import modsnus.snusmod.slots.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import static modsnus.snusmod.Snusmod.ENERGY_HANDLER_TYPE;


public class EnergyScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public EnergyScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ENERGY_HANDLER_TYPE, syncId);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);


        this.initialize(playerInventory);
    }


    private void initialize(PlayerInventory playerInventory) {

        this.addSlot(new EnergySlot(inventory, 0, 91, 80));



        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }


    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
