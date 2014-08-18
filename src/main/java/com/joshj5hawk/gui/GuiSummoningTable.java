package com.joshj5hawk.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.joshj5hawk.container.ContainerSummoningTable;
import com.joshj5hawk.crafting.SummoningRecipes;
import com.joshj5hawk.item.ItemSummoningBook;
import com.joshj5hawk.lib.Strings;
import com.joshj5hawk.tileentity.TileEntitySummoningTable;

public class GuiSummoningTable extends GuiContainer
{

    private ResourceLocation texutre = new ResourceLocation(Strings.modid + ":" + "textures/gui/summoningTable.png");
    private TileEntitySummoningTable summoningTable;
    private float rot;

    public GuiSummoningTable(InventoryPlayer invPlayer, TileEntitySummoningTable tileEntityST)
    {
        super(new ContainerSummoningTable(invPlayer, tileEntityST));
        summoningTable = tileEntityST;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String name = this.summoningTable.hasCustomInventoryName() ? this.summoningTable.getInventoryName() : I18n.format(this.summoningTable.getInventoryName(),
                new Object[0]);
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) - 70 / 2, 6, 4210752);
        this.fontRendererObj.drawString("Table", this.xSize / 2 - this.fontRendererObj.getStringWidth("Table") - 95 / 2, 15, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 63, this.ySize - 96 + 2, 4210752);

        ItemStack stack1 = summoningTable.getStackInSlot(0);
        ItemStack stack2 = summoningTable.getStackInSlot(1);
        if (stack1 != null && stack2 != null)
        {
            renderEntity(stack1, stack2);
        }
    }

    // mostly copied from bspkrs core, do not touch
    private void renderEntity(ItemStack stack1, ItemStack stack2)
    {
        float posX = (float) mc.thePlayer.posX;
        float posY = (float) mc.thePlayer.posY;

        ItemStack result = SummoningRecipes.INSTANCE.getSummoningResult(stack1.getItem(), stack2.getItem());
        EntityLiving ent = (EntityLiving) EntityList.createEntityByName(ItemSummoningBook.getSimpleEntityName(ItemSummoningBook.getTag(result).getString(ItemSummoningBook.ENTITY_KEY)), mc.theWorld);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glTranslatef(posX - 140, posY - 20, 50.0F);
        GL11.glScalef((-25), 25, 25);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(rot, 0, 1, 0);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        ent.renderYawOffset = (float) Math.atan(2.0F / 40.0F) * 20.0F;
        ent.rotationYaw = (float) Math.atan(2.0F / 40.0F) * 40.0F;
        ent.rotationPitch = -((float) Math.atan(2.0F / 40.0F)) * 20.0F;
        ent.rotationYawHead = ent.renderYawOffset;
        GL11.glTranslatef(0.0F, ent.yOffset, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        GL11.glPopMatrix();
        rot += 0.5f;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texutre);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (summoningTable.hasFuel())
        {
            int i1 = summoningTable.getFuelRemainingScaled(34);
            drawTexturedModalRect(guiLeft + 54, guiTop + 37 - i1, 227, 31 - i1, 12, i1);
        }

        int j1 = summoningTable.getSummoningProgressScaled(72);
        drawTexturedModalRect(guiLeft + 43, guiTop + 36, 176, 33, j1 + 1, 43);
    }
}
