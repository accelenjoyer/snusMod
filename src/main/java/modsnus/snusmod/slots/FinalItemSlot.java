package modsnus.snusmod.slots;



import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FinalItemSlot extends Slot {
    private final ScreenHandler handler;
    private final Item finalItem = Registry.ITEM.get(new Identifier("snusmod", "final_snus"));
    private boolean shouldGiveItem = false;

    public FinalItemSlot(ScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }
    @Override
    public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {

        for (int i = 0; i < 4; i++) {
            Slot slot = this.handler.slots.get(i);
            slot.setStack(ItemStack.EMPTY);
        }
        return super.onTakeItem(player, stack);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }


    @Override
    public ItemStack takeStack(int amount) {
        return super.takeStack(amount);
    }

    public void setShouldGiveItem(boolean shouldGiveItem) {
        this.shouldGiveItem = shouldGiveItem;
    }

    @Override
    public ItemStack getStack() {
        if (shouldGiveItem) {

            shouldGiveItem = false;
            return new ItemStack(finalItem);
        }
        return super.getStack();
    }
}

