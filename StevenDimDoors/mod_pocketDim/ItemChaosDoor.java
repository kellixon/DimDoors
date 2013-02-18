package StevenDimDoors.mod_pocketDim;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemChaosDoor extends itemDimDoor
{
    private Material doorMaterial;

    public ItemChaosDoor(int par1, Material par2Material)
    {
    	  super(par1, par2Material);
          this.doorMaterial = par2Material;
          this.setCreativeTab(CreativeTabs.tabTransport);
    }
    @Override
	 public String getTextureFile()
	 {
		 return "/PocketBlockTextures.png";
	 }
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	
    
    		par3List.add("Caution- leads to random destination");
    	
    	


    	
    
   
    }
    
}