package modsnus.snusmod.slots;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomItemSlot extends Slot {
    Item customItem1 = Registry.ITEM.get(new Identifier("snusmod", "custom_item_1"));
    public CustomItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @Override
    public boolean canInsert(ItemStack stack) {

        return stack.getItem() == customItem1;
    }
}



