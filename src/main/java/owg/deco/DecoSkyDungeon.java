package owg.deco;

import java.util.Random;

import owg.data.DungeonLoot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class DecoSkyDungeon extends WorldGenerator
{
	public boolean isEndDungeon = false;
	
    public DecoSkyDungeon(boolean end)
    {
		isEndDungeon = end;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
		boolean chain1 = false, chain2 = false, chain3 = false, chain4 = false;
	
		//Check for chains
		for(int ch1 = par4; ch1 < 60; ch1++) { if(!par1World.isAirBlock(par3 + 4, ch1 + 5, par5 + 4)) { chain1 = true; break; } }
		for(int ch2 = par4; ch2 < 60; ch2++) { if(!par1World.isAirBlock(par3 + 4, ch2 + 5, par5 - 4)) { chain2 = true; break; } }
		for(int ch3 = par4; ch3 < 60; ch3++) { if(!par1World.isAirBlock(par3 - 4, ch3 + 5, par5 + 4)) { chain3 = true; break; } }
		for(int ch4 = par4; ch4 < 60; ch4++) { if(!par1World.isAirBlock(par3 - 4, ch4 + 5, par5 - 4)) { chain4 = true; break; } }
		
		if(chain1 == false || chain2 == false || chain3 == false || chain4 == false )
		{
			return false;
		}
		
		//Build Chains
		for(int chy1 = par4 + 5; chy1 < 70 ; chy1++) { if(!par1World.isAirBlock(par3 + 4, chy1, par5 + 4)) { break; } par1World.setBlock(par3 + 4, chy1, par5 + 4, Blocks.iron_bars); }		
		for(int chy2 = par4 + 5; chy2 < 70 ; chy2++) { if(!par1World.isAirBlock(par3 + 4, chy2, par5 - 4)) { break; } par1World.setBlock(par3 + 4, chy2, par5 - 4, Blocks.iron_bars); }		
		for(int chy3 = par4 + 5; chy3 < 70 ; chy3++) { if(!par1World.isAirBlock(par3 - 4, chy3, par5 + 4)) { break; } par1World.setBlock(par3 - 4, chy3, par5 + 4, Blocks.iron_bars); }		
		for(int chy4 = par4 + 5; chy4 < 70 ; chy4++) { if(!par1World.isAirBlock(par3 - 4, chy4, par5 - 4)) { break; } par1World.setBlock(par3 - 4, chy4, par5 - 4, Blocks.iron_bars); }	

		par1World.setBlock(par3 + 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
		par1World.setBlock(par3 + 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);
		par1World.setBlock(par3 - 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
		par1World.setBlock(par3 - 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);
		
		//Build SkyDungeon
		for(int x1 = par3 - 4; x1 < par3 + 5 ; x1++)
		{
			for(int y1 = par4; y1 < par4 + 6 ; y1++)
			{
				for(int z1 = par5 - 4; z1 < par5 + 5 ; z1++)
				{
					if (par2Random.nextInt(10) == 0)
					{
						par1World.setBlock(x1, y1, z1, Blocks.air);
					}
					else
					{
						if (par2Random.nextInt(2) != 0)
						{
							par1World.setBlock(x1, y1, z1, Blocks.mossy_cobblestone);
						}
						else
						{
							par1World.setBlock(x1, y1, z1, Blocks.cobblestone);
						}
					}
				}
			}
		}
		
		//Fill with Air
		for(int x2 = par3 - 3; x2 < par3 + 4 ; x2++)
		{
			for(int y2 = par4 + 1; y2 < par4 + 5 ; y2++)
			{
				for(int z2 = par5 - 3; z2 < par5 + 4 ; z2++)
				{
					par1World.setBlock(x2, y2, z2, Blocks.air);
				}
			}
		}
		
		//Chests and Spawners
		if(isEndDungeon)
		{
			par1World.setBlock(par3 + 2, par4 + 1, par5 - 1, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 2, par4 + 1, par5 + 0, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 2, par4 + 1, par5 + 1, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			
			par1World.setBlock(par3 - 1, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 0, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 1, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			
			par1World.setBlock(par3 - 2, par4 + 1, par5 - 1, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 - 2, par4 + 1, par5 + 0, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 - 2, par4 + 1, par5 + 1, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			
			par1World.setBlock(par3 - 1, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 0, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
			par1World.setBlock(par3 + 1, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);

			par1World.setBlock(par3, par4 + 6, par5, Blocks.mob_spawner);
			TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.getTileEntity(par3, par4 + 6, par5);
			if (spawn3 != null) { spawn3.func_145881_a().setEntityName(pickMobSpawner(par2Random)); }
		}
		else
		{
			par1World.setBlock(par3, par4 + 1, par5, Blocks.mob_spawner);
			par1World.setBlock(par3, par4 + 3, par5, Blocks.mob_spawner);
			par1World.setBlock(par3, par4 + 6, par5, Blocks.mob_spawner);
			TileEntityMobSpawner spawn1 = (TileEntityMobSpawner)par1World.getTileEntity(par3, par4 + 1, par5);
			TileEntityMobSpawner spawn2 = (TileEntityMobSpawner)par1World.getTileEntity(par3, par4 + 3, par5);
			TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.getTileEntity(par3, par4 + 6, par5);
			if (spawn1 != null) { spawn1.func_145881_a().setEntityName(pickMobSpawner(par2Random)); }
			if (spawn2 != null) { spawn2.func_145881_a().setEntityName(pickMobSpawner(par2Random)); }
			if (spawn3 != null) { spawn3.func_145881_a().setEntityName(pickMobSpawner(par2Random)); }
			
			par1World.setBlock(par3, par4 + 2, par5, Blocks.chest);
			TileEntityChest var16 = (TileEntityChest)par1World.getTileEntity(par3, par4 + 2, par5);
			
			for (int var17 = 0; var17 < 14; ++var17)
			{
				ItemStack var18 = this.pickCheckLootItem(par2Random);

				if (var18 != null)
				{
					var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
				}
			}
		}
		return false;
    }
	
	
    private ItemStack pickCheckLootItem(Random random)
    {
        return DungeonLoot.pickItem();
    }

    private String pickMobSpawner(Random random)
    {
        int i = random.nextInt(4);
        if(i == 0)
        {
            return "Skeleton";
        }
        if(i == 1)
        {
            return "Zombie";
        }
        if(i == 2)
        {
            return "Zombie";
        }
        if(i == 3)
        {
            return "Spider";
        } else
        {
            return "";
        }
    }
}
