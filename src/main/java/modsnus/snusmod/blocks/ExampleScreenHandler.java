package modsnus.snusmod.blocks;

import modsnus.snusmod.slots.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static modsnus.snusmod.Snusmod.SCREEN_HANDLER_TYPE;
public class ExampleScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final Slot customSlot5; // Ссылка на пятый кастомный слот

    public ExampleScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(SCREEN_HANDLER_TYPE, syncId);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        this.initialize( playerInventory,this);

        // Получаем ссылку на пятый кастомный слот
        this.customSlot5 = this.slots.get(4);
    }

    @Override
    public ItemStack onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        ItemStack stack = super.onSlotClick(slotIndex, button, actionType, player);

        if (areAllCustomSlotsFilled() && stack.isEmpty()) {

            this.inventory.setStack(4, new ItemStack(Registry.ITEM.get(new Identifier("snusmod", "final_snus"))));
        }

        return stack;
    }

    private boolean areAllCustomSlotsFilled() {

        for (int i = 0; i < 4; i++) {
            Slot slot = this.slots.get(i); // Получаем слот по индексу
            if (slot.getStack().isEmpty()) {
                // Если хотя бы один кастомный слот пустой, вернем false
                return false;
            }
        }
        // Если все кастомные слоты заполнены, вернем true
        return true;
    }

    private void initialize(PlayerInventory playerInventory,ExampleScreenHandler handler) {
        this.addSlot(new CustomItemSlot(inventory, 0, 20, 27)); // Первый слот
        this.addSlot(new CustomItemSlot2(inventory, 1, 39, 27)); // Второй слот
        this.addSlot(new CustomItemSlot3(inventory, 2, 20, 42)); // Третий слот
        this.addSlot(new CustomItemSlot4(inventory, 3, 39, 42)); // Четвертый слот

        // Пятый кастомный слот
        this.addSlot(new FinalItemSlot(handler,inventory, 4, 116, 34));


        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true; // Логика проверки доступности
    }
}



