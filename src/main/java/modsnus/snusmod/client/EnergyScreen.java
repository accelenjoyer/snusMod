package modsnus.snusmod.client;
import modsnus.snusmod.EnergyManager;
import modsnus.snusmod.blocks.EnergyScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnergyScreen extends HandledScreen<EnergyScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier("snusmod", "textures/gui/energygui.png");
    private static final int ENERGY_BAR_WIDTH = 100;
    private static final int ENERGY_BAR_HEIGHT = 10;
    private static final int ENERGY_BAR_X = 50;
    private static final int ENERGY_BAR_Y = 10;

    public EnergyScreen(EnergyScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        this.backgroundWidth = 200;
        this.backgroundHeight = 200;
        // Установка координат GUI
        this.x = this.width / 2 - backgroundWidth / 2;
        this.y = this.height / 2 - backgroundHeight / 2 + 20;
    }

    @Override
    public void tick() {
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
        drawEnergyBar(matrices);
        drawItemSlots(matrices);
        drawItemSlot(matrices,230,106,17);
    }

    private void drawEnergyBar(MatrixStack matrices) {
        int energy = Math.min(EnergyManager.getEnergy(), 1000);
        int filledWidth = (int) ((double) energy / 1000 * ENERGY_BAR_WIDTH);
        fill(matrices, this.x + ENERGY_BAR_X, this.y + ENERGY_BAR_Y, this.x + ENERGY_BAR_X + ENERGY_BAR_WIDTH, this.y + ENERGY_BAR_Y + ENERGY_BAR_HEIGHT, 0xFF000000);
        fill(matrices, this.x + ENERGY_BAR_X, this.y + ENERGY_BAR_Y, this.x + ENERGY_BAR_X + filledWidth, this.y + ENERGY_BAR_Y + ENERGY_BAR_HEIGHT, 0xFFFF0000); // Красный цвет
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
    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        super.drawForeground(matrices, mouseX, mouseY);
        int energy = EnergyManager.getEnergy();
        drawEnergyValue(matrices, energy);
    }

    private void drawEnergyValue(MatrixStack matrices, int energy) {
        textRenderer.drawWithShadow(matrices, "Energy: " + energy + "/1000", x -50, y + 10, 0xFFFFFF);
    }
}


