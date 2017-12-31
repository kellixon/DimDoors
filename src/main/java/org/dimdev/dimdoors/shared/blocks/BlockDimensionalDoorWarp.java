package org.dimdev.dimdoors.shared.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import org.dimdev.dimdoors.DimDoors;
import org.dimdev.dimdoors.shared.items.ModItems;
import org.dimdev.dimdoors.shared.tileentities.TileEntityEntranceRift;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class BlockDimensionalDoorWarp extends BlockDimensionalDoor {

    public static final String ID = "warp_dimensional_door";

    public BlockDimensionalDoorWarp() {
        super(Material.WOOD);
        setHardness(1.0F);
        setUnlocalizedName(ID);
        setRegistryName(new ResourceLocation(DimDoors.MODID, ID));
    }

    @Override
    public Item getItem() {
        return ModItems.WARP_DIMENSIONAL_DOOR;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Blocks.OAK_DOOR.getItemDropped(state, rand, fortune);
    }

    @Override
    public void setupRift(TileEntityEntranceRift rift) {
        // TODO
    }
}
