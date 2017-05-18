package nightkosh.gravestone_extended.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.GameType;
import nightkosh.gravestone_extended.ModGravestoneExtended;
import nightkosh.gravestone_extended.core.MessageHandler;
import nightkosh.gravestone_extended.core.Resources;
import nightkosh.gravestone_extended.gui.container.AltarContainer;
import nightkosh.gravestone_extended.packets.AltarMessageToServer;
import nightkosh.gravestone_extended.tileentity.TileEntityAltar;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSAltarGui extends GuiContainer {

    private final String requirementsStr = ModGravestoneExtended.proxy.getLocalizedString("gui.altar.requirements");
    private final String resurrectionButtonStr = ModGravestoneExtended.proxy.getLocalizedString("gui.altar.resurrect");
    private AltarContainer container;
    private GuiButton resurrectionButton;
    private TileEntityAltar tileEntity = null;
    private EntityPlayer player = null;
    private boolean isCreative = false;

    public GSAltarGui(InventoryPlayer inventoryPlayer, TileEntityAltar tileEntity) {
        super(new AltarContainer(inventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.player = inventoryPlayer.player;
        this.container = (AltarContainer) this.inventorySlots;
        isCreative = player.worldObj.getWorldInfo().getGameType().equals(GameType.CREATIVE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(resurrectionButton = new GuiButton(0, (width - xSize) / 2 + 100, (height - ySize) / 2 + 25, 70, 20, resurrectionButtonStr));
        resurrectionButton.enabled = false;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                MessageHandler.networkWrapper.sendToServer(new AltarMessageToServer(this.player, tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), AltarMessageToServer.MOB_TYPE.LIVED));
                break;
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.renderEngine.bindTexture(Resources.ALTAR_GUI);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);


        this.drawString(this.fontRendererObj, String.format(requirementsStr, container.getResurrectionLevel()), this.width / 2 - 40, (height - ySize) / 2 + 55, 16777215);
        if (player != null) {
            resurrectionButton.enabled = isCreative || player.experienceLevel >= container.getResurrectionLevel();
        }
    }
}
