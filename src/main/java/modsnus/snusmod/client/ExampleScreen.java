package modsnus.snusmod.client;
import modsnus.snusmod.blocks.ExampleScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExampleScreen extends HandledScreen<ExampleScreenHandler> {

    private static final Identifier BACKGROUND_TEXTURE = new Identifier("snusmod", "textures/gui/background.png");

    public ExampleScreen(ExampleScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 200;
        this.backgroundHeight = 200;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        drawItemSlots(matrices);


        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
    }
    private void drawItemSlots(MatrixStack matrices) {

        int slotSize = 17;
        int offsetX = 7;
        int offsetY = 141;
        int gap = 1;


        for (int i = 0; i < 9; i++) {
            int x = this.x + offsetX + i * (slotSize + gap);
            int y = this.y + offsetY;
            drawItemSlot(matrices, x, y, slotSize);
        }
    }

    private void drawItemSlot(MatrixStack matrices, int x, int y, int size) {

        fill(matrices, x, y, x + size, y + size, 0xFFA0A0A0);


        drawHorizontalLine(matrices, x, x + size, y, 0xFF404040);
        drawHorizontalLine(matrices, x, x + size, y + size, 0xFF404040);


        drawVerticalLine(matrices, x, y, y + size, 0xFF404040);
        drawVerticalLine(matrices, x + size, y, y + size, 0xFF404040);
    }
}


