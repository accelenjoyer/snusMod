package modsnus.snusmod.slots;

import modsnus.snusmod.EnergyManager;
import modsnus.snusmod.entities.EnergyContainerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnergySlot extends Slot {


    Set<Item> allowedItems = new HashSet<>(Arrays.asList(
            Registry.ITEM.get(new Identifier("snusmod", "custom_item_2")),
            Registry.ITEM.get(new Identifier("snusmod", "custom_item_3.json")),
            Registry.ITEM.get(new Identifier("snusmod", "custom_item_4")),
            Registry.ITEM.get(new Identifier("snusmod", "custom_item_1")),
            Registry.ITEM.get(new Identifier("snusmod", "final_snus")),
            Items.COAL
    ));

    public EnergySlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);

    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return allowedItems.contains(stack.getItem());
    }
    @Override
    public void setStack(ItemStack stack) {

        ItemStack currentStack = this.getStack();


        super.setStack(stack);


        if (!ItemStack.areEqual(currentStack, stack)) {

            onContentsChanged(currentStack, stack);
        }
    }
    protected void onContentsChanged(ItemStack oldStack, ItemStack newStack) {
        if (!ItemStack.areEqual(oldStack, newStack)) {

            Item newItem = newStack.getItem();

            if (allowedItems.contains(newItem)) {
                if (newItem == Registry.ITEM.get(new Identifier("snusmod", "custom_item_2"))) {
                    EnergyManager.setEnergy(EnergyManager.getEnergy() + 10);
                } else if (newItem == Registry.ITEM.get(new Identifier("snusmod", "custom_item_3"))) {
                    EnergyManager.setEnergy(EnergyManager.getEnergy() + 20);
                } else if (newItem == Registry.ITEM.get(new Identifier("snusmod", "custom_item_4"))) {
                    EnergyManager.setEnergy(EnergyManager.getEnergy() + 50);
                }
                else if (newItem == Registry.ITEM.get(new Identifier("snusmod", "custom_item_1"))) {
                    EnergyManager.setEnergy(EnergyManager.getEnergy()+ 100);
            }
                else if (newItem == Registry.ITEM.get(new Identifier("snusmod", "final_snus"))) {
                    EnergyManager.setEnergy(EnergyManager.getEnergy()+ 200);
        }
                this.setStack(ItemStack.EMPTY);
    }

        }
    }
}