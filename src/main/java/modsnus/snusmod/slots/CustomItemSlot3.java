package modsnus.snusmod.slots;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomItemSlot3 extends Slot {
    Item customItem3 = Registry.ITEM.get(new Identifier("snusmod", "custom_item_3"));
    public CustomItemSlot3(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() == customItem3;
    }
}

