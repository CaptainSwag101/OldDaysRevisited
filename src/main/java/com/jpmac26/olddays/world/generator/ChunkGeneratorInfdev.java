package com.jpmac26.olddays.world.generator;

import java.util.List;
import java.util.Random;

import owg.data.DungeonLoot;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenCactus;
import owg.deco.OldGenClay;
import owg.deco.OldGenDungeons;
import owg.deco.OldGenFlowers;
import owg.deco.OldGenLiquids;
import owg.deco.OldGenMinable;
import owg.deco.OldGenReed;
import owg.deco.OldGenTrees;
import owg.map.MapGenOLD;
import owg.map.MapGenOLDCaves;
import owg.noise.NoiseOctavesInfdev;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorInfdev implements IChunkProvider
{
    private Random field_913_j;
    private NoiseOctavesInfdev field_912_k;
    private NoiseOctavesInfdev field_911_l;
    private NoiseOctavesInfdev field_910_m;
    private NoiseOctavesInfdev field_909_n;
    private NoiseOctavesInfdev field_908_o;
    public NoiseOctavesInfdev field_922_a;
    public NoiseOctavesInfdev field_921_b;
    public NoiseOctavesInfdev field_920_c;
	
    private World field_907_p;
    private final boolean mapFeaturesEnabled;
    private double field_906_q[];
    private double field_905_r[];
    private double field_904_s[];
    private double field_903_t[];
    private MapGenOLD field_902_u;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	
    double field_919_d[];
    double field_918_e[];
    double field_917_f[];
    double field_916_g[];
    double field_915_h[];
    int field_914_i[][];
    
    private final boolean alpha;

    public ChunkGeneratorInfdev(World world, long l, boolean par4, boolean a)
    {
        field_905_r = new double[256];
        field_904_s = new double[256];
        field_903_t = new double[256];
        field_902_u = new MapGenOLDCaves();
        field_914_i = new int[32][32];
        field_907_p = world;
        mapFeaturesEnabled = par4;
        alpha = a;
        
        DungeonLoot.init(l);
        
        field_913_j = new Random(l);
        field_912_k = new NoiseOctavesInfdev(field_913_j, 16);
        field_911_l = new NoiseOctavesInfdev(field_913_j, 16);
        field_910_m = new NoiseOctavesInfdev(field_913_j, 8);
        field_909_n = new NoiseOctavesInfdev(field_913_j, 4);
        field_908_o = new NoiseOctavesInfdev(field_913_j, 4);
        field_922_a = new NoiseOctavesInfdev(field_913_j, 10);
        field_921_b = new NoiseOctavesInfdev(field_913_j, 16);
        field_920_c = new NoiseOctavesInfdev(field_913_j, 8);
    }

    public void generateTerrain(int i, int j, Block blocks[])
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        field_906_q = initializeNoiseField(field_906_q, i * byte0, 0, j * byte0, k, byte2, l);
        for(int i1 = 0; i1 < byte0; i1++)
        {
            for(int j1 = 0; j1 < byte0; j1++)
            {
                for(int k1 = 0; k1 < 16; k1++)
                {
                    double d = 0.125D;
                    double d1 = field_906_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d2 = field_906_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d3 = field_906_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d4 = field_906_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d5 = (field_906_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d1) * d;
                    double d6 = (field_906_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d2) * d;
                    double d7 = (field_906_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d3) * d;
                    double d8 = (field_906_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d4) * d;
                    for(int l1 = 0; l1 < 8; l1++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for(int i2 = 0; i2 < 4; i2++)
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            char c = '\200';
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for(int k2 = 0; k2 < 4; k2++)
                            {
                                Block l2 = Blocks.air;
                                if(k1 * 8 + l1 < byte1)
                                {
									l2 = Blocks.water;
                                }
                                if(d15 > 0.0D)
                                {
                                    l2 = Blocks.stone;
                                }
                                blocks[j2] = l2;
                                j2 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                }

            }

        }

    }

    public void replaceBlocksForBiome(int i, int j, Block blocks[])
    {
        byte byte0 = 64;
        double d = 0.03125D;
        field_905_r = field_909_n.func_807_a(field_905_r, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
        field_904_s = field_909_n.func_807_a(field_904_s, j * 16, 109.0134D, i * 16, 16, 1, 16, d, 1.0D, d);
        field_903_t = field_908_o.func_807_a(field_903_t, i * 16, j * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                boolean flag = field_905_r[k + l * 16] + field_913_j.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = field_904_s[k + l * 16] + field_913_j.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int)(field_903_t[k + l * 16] / 3D + 3D + field_913_j.nextDouble() * 0.25D);
                int j1 = -1;
                Block byte1 = Blocks.grass;
                Block byte2 = Blocks.dirt;
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = (k * 16 + l) * 128 + k1;
                    if(k1 <= (0 + field_913_j.nextInt(6)) - 1)
                    {
                    	blocks[l1] = Blocks.bedrock;
                        continue;
                    }
                    Block byte3 = blocks[l1];
                    if(byte3 == Blocks.air)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte3 != Blocks.stone)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte1 = Blocks.air;
                            byte2 = Blocks.stone;
                        } else
                        if(k1 >= byte0 - 4 && k1 <= byte0 + 1)
                        {
                            byte1 = Blocks.grass;
                            byte2 = Blocks.dirt;
                            if(flag1)
                            {
                                byte1 = Blocks.air;
                            }
                            if(flag1)
                            {
                                byte2 = Blocks.gravel;
                            }
                            if(flag)
                            {
                                byte1 = Blocks.sand;
                            }
                            if(flag)
                            {
                                byte2 = Blocks.sand;
                            }
                        }
                        if(k1 < byte0 && byte1 == Blocks.air)
                        {
                            byte1 = Blocks.flowing_water;
                        }
                        j1 = i1;
                        if(k1 >= byte0 - 1)
                        {
                        	blocks[l1] = byte1;
                        } else
                        {
                        	blocks[l1] = byte2;
                        }
                        continue;
                    }
                    if(j1 > 0)
                    {
                        j1--;
                        blocks[l1] = byte2;
                    }
                }

            }

        }

    }
	
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    public Chunk provideChunk(int i, int j)
    {
        field_913_j.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        Block blocks[] = new Block[32768];
        generateTerrain(i, j, blocks);
        replaceBlocksForBiome(i, j, blocks);
        field_902_u.generate(this, field_907_p, i, j, blocks);
		
        if (mapFeaturesEnabled)
        {
			strongholdGenerator.func_151539_a(this, field_907_p, i, j, blocks);
			mineshaftGenerator.func_151539_a(this, field_907_p, i, j, blocks);
		}	
		
        Chunk chunk = new Chunk(field_907_p, blocks, i, j);
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_916_g = field_922_a.func_807_a(field_916_g, i, j, k, l, 1, j1, 1.0D, 0.0D, 1.0D);
        field_915_h = field_921_b.func_807_a(field_915_h, i, j, k, l, 1, j1, 100D, 0.0D, 100D);
        field_919_d = field_910_m.func_807_a(field_919_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_918_e = field_912_k.func_807_a(field_918_e, i, j, k, l, i1, j1, d, d1, d);
        field_917_f = field_911_l.func_807_a(field_917_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        for(int i2 = 0; i2 < l; i2++)
        {
            for(int j2 = 0; j2 < j1; j2++)
            {
                double d2 = (field_916_g[l1] + 256D) / 512D;
                if(d2 > 1.0D)
                {
                    d2 = 1.0D;
                }
                double d3 = 0.0D;
                double d4 = field_915_h[l1] / 8000D;
                if(d4 < 0.0D)
                {
                    d4 = -d4;
                }
                d4 = d4 * 3D - 3D;
                if(d4 < 0.0D)
                {
                    d4 /= 2D;
                    if(d4 < -1D)
                    {
                        d4 = -1D;
                    }
                    d4 /= 1.3999999999999999D;
                    d4 /= 2D;
                    d2 = 0.0D;
                } else
                {
                    if(d4 > 1.0D)
                    {
                        d4 = 1.0D;
                    }
                    d4 /= 6D;
                }
                d2 += 0.5D;
                d4 = (d4 * (double)i1) / 16D;
                double d5 = (double)i1 / 2D + d4 * 4D;
                l1++;
                for(int k2 = 0; k2 < i1; k2++)
                {
                    double d6 = 0.0D;
                    double d7 = (((double)k2 - d5) * 12D) / d2;
                    if(d7 < 0.0D)
                    {
                        d7 *= 4D;
                    }
                    double d8 = field_918_e[k1] / 512D;
                    double d9 = field_917_f[k1] / 512D;
                    double d10 = (field_919_d[k1] / 10D + 1.0D) / 2D;
                    if(d10 < 0.0D)
                    {
                        d6 = d8;
                    } else
                    if(d10 > 1.0D)
                    {
                        d6 = d9;
                    } else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }
                    d6 -= d7;
                    if(k2 > i1 - 4)
                    {
                        double d11 = (float)(k2 - (i1 - 4)) / 3F;
                        d6 = d6 * (1.0D - d11) + -10D * d11;
                    }
                    if((double)k2 < d3)
                    {
                        double d12 = (d3 - (double)k2) / 4D;
                        if(d12 < 0.0D)
                        {
                            d12 = 0.0D;
                        }
                        if(d12 > 1.0D)
                        {
                            d12 = 1.0D;
                        }
                        d6 = d6 * (1.0D - d12) + -10D * d12;
                    }
                    ad[k1] = d6;
                    k1++;
                }

            }

        }

        return ad;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
		BiomeGenBase biomegenbase = field_907_p.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
        field_913_j.setSeed(field_907_p.getSeed());
        long l1 = (field_913_j.nextLong() / 2L) * 2L + 1L;
        long l2 = (field_913_j.nextLong() / 2L) * 2L + 1L;
        field_913_j.setSeed((long)i * l1 + (long)j * l2 ^ field_907_p.getSeed());
        double d = 0.25D;
		
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, field_907_p, field_913_j, i, j, false));
        
		if (mapFeaturesEnabled)
        {
			strongholdGenerator.generateStructuresInChunk(field_907_p, field_913_j, i, j);
			mineshaftGenerator.generateStructuresInChunk(field_907_p, field_913_j, i, j);
		}	

        for(int i1 = 0; i1 < 8; i1++)
        {
            int i4 = k + field_913_j.nextInt(16) + 8;
            int j6 = field_913_j.nextInt(128);
            int i11 = l + field_913_j.nextInt(16) + 8;
            (new OldGenDungeons()).generate(field_907_p, field_913_j, i4, j6, i11);
        }
        
        if(alpha)
        {
	        for(int j1 = 0; j1 < 10; j1++)
	        {
	            int j4 = k + field_913_j.nextInt(16);
	            int k6 = field_913_j.nextInt(128);
	            int j11 = l + field_913_j.nextInt(16);
	            (new OldGenClay(32, 0)).generate(field_907_p, field_913_j, j4, k6, j11);
	        }
        }
		
        for(int k1 = 0; k1 < 20; k1++)
        {
            int k4 = k + field_913_j.nextInt(16);
            int l6 = field_913_j.nextInt(128);
            int k11 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.dirt, 32, 0)).generate(field_907_p, field_913_j, k4, l6, k11);
        }

        for(int i2 = 0; i2 < 10; i2++)
        {
            int l4 = k + field_913_j.nextInt(16);
            int i7 = field_913_j.nextInt(128);
            int l11 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gravel, 32, 0)).generate(field_907_p, field_913_j, l4, i7, l11);
        }

        for(int j2 = 0; j2 < 20; j2++)
        {
            int i5 = k + field_913_j.nextInt(16);
            int j7 = field_913_j.nextInt(128);
            int i12 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.coal_ore, 16, 0)).generate(field_907_p, field_913_j, i5, j7, i12);
        }

        for(int k2 = 0; k2 < 20; k2++)
        {
            int j5 = k + field_913_j.nextInt(16);
            int k7 = field_913_j.nextInt(64);
            int j12 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.iron_ore, 8, 0)).generate(field_907_p, field_913_j, j5, k7, j12);
        }

        for(int i3 = 0; i3 < 2; i3++)
        {
            int k5 = k + field_913_j.nextInt(16);
            int l7 = field_913_j.nextInt(32);
            int k12 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gold_ore, 8, 0)).generate(field_907_p, field_913_j, k5, l7, k12);
        }

        for(int j3 = 0; j3 < 8; j3++)
        {
            int l5 = k + field_913_j.nextInt(16);
            int i8 = field_913_j.nextInt(16);
            int l12 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.redstone_ore, 7, 0)).generate(field_907_p, field_913_j, l5, i8, l12);
        }

        for(int k3 = 0; k3 < 1; k3++)
        {
            int i6 = k + field_913_j.nextInt(16);
            int j8 = field_913_j.nextInt(16);
            int i13 = l + field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.diamond_ore, 7, 0)).generate(field_907_p, field_913_j, i6, j8, i13);
        }

        d = 0.5D;
        int l3 = (int)((field_920_c.func_806_a((double)k * d, (double)l * d) / 8D + field_913_j.nextDouble() * 4D + 4D) / 3D);
        if(l3 < 0)
        {
            l3 = 0;
        }
        if(field_913_j.nextInt(10) == 0)
        {
            l3++;
        }
		Object obj = new OldGenTrees(0);
        if(field_913_j.nextInt(10) == 0 && alpha)
        {
            obj = new OldGenBigTree(0);
        }
        for(int k8 = 0; k8 < l3; k8++)
        {
            int j13 = k + field_913_j.nextInt(16) + 8;
            int l15 = l + field_913_j.nextInt(16) + 8;
			WorldGenerator worldgenerator = new OldGenTrees(0);
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(field_907_p, field_913_j, j13, field_907_p.getHeightValue(j13, l15), l15);
        }

        for(int l8 = 0; l8 < 2; l8++)
        {
            int k13 = k + field_913_j.nextInt(16) + 8;
            int i16 = field_913_j.nextInt(128);
            int j18 = l + field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.yellow_flower)).generate(field_907_p, field_913_j, k13, i16, j18);
        }

        if(field_913_j.nextInt(2) == 0)
        {
            int i9 = k + field_913_j.nextInt(16) + 8;
            int l13 = field_913_j.nextInt(128);
            int j16 = l + field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.red_flower)).generate(field_907_p, field_913_j, i9, l13, j16);
        }

        if(alpha)
        {
	        if(field_913_j.nextInt(4) == 0)
	        {
	            int j9 = k + field_913_j.nextInt(16) + 8;
	            int i14 = field_913_j.nextInt(128);
	            int k16 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenFlowers(Blocks.brown_mushroom)).generate(field_907_p, field_913_j, j9, i14, k16);
	        }
	        if(field_913_j.nextInt(8) == 0)
	        {
	            int k9 = k + field_913_j.nextInt(16) + 8;
	            int j14 = field_913_j.nextInt(128);
	            int l16 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenFlowers(Blocks.red_mushroom)).generate(field_907_p, field_913_j, k9, j14, l16);
	        }
        }
        else
        {
	        if(field_913_j.nextInt(6) == 0)
	        {
	            int j9 = k + field_913_j.nextInt(16) + 8;
	            int i14 = field_913_j.nextInt(64);
	            int k16 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenFlowers(Blocks.brown_mushroom)).generate(field_907_p, field_913_j, j9, i14, k16);
	        }
	        if(field_913_j.nextInt(12) == 0)
	        {
	            int k9 = k + field_913_j.nextInt(16) + 8;
	            int j14 = field_913_j.nextInt(64);
	            int l16 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenFlowers(Blocks.red_mushroom)).generate(field_907_p, field_913_j, k9, j14, l16);
	        }
        }

        if(alpha)
        {
	        for(int l9 = 0; l9 < 10; l9++)
	        {
	            int k14 = k + field_913_j.nextInt(16) + 8;
	            int i17 = field_913_j.nextInt(128);
	            int k18 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenReed()).generate(field_907_p, field_913_j, k14, i17, k18);
	        }
        }

        if(alpha)
        {
	        for(int i10 = 0; i10 < 1; i10++)
	        {
	            int l14 = k + field_913_j.nextInt(16) + 8;
	            int j17 = field_913_j.nextInt(128);
	            int l18 = l + field_913_j.nextInt(16) + 8;
	            (new OldGenCactus()).generate(field_907_p, field_913_j, l14, j17, l18);
	        }
        }

        for(int j10 = 0; j10 < 50; j10++)
        {
            int i15 = k + field_913_j.nextInt(16) + 8;
            int k17 = field_913_j.nextInt(field_913_j.nextInt(120) + 8);
            int i19 = l + field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_water)).generate(field_907_p, field_913_j, i15, k17, i19);
        }

        for(int k10 = 0; k10 < 20; k10++)
        {
            int j15 = k + field_913_j.nextInt(16) + 8;
            int l17 = field_913_j.nextInt(field_913_j.nextInt(field_913_j.nextInt(112) + 8) + 8);
            int j19 = l + field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_lava)).generate(field_907_p, field_913_j, j15, l17, j19);
        }

		SpawnerAnimals.performWorldGenSpawning(field_907_p, biomegenbase, k + 8, l + 8, 16, 16, field_913_j);
        k += 8;
        l += 8;
		
        for (int sn1 = 0; sn1 < 16; ++sn1)
        {
            for (int sn2 = 0; sn2 < 16; ++sn2)
            {
                int sn3 = field_907_p.getPrecipitationHeight(k + sn1, l + sn2);

                if (field_907_p.isBlockFreezable(sn1 + k, sn3 - 1, sn2 + l))
                {
                    field_907_p.setBlock(sn1 + k, sn3 - 1, sn2 + l, Blocks.ice, 0, 2);
                }

                Block b = field_907_p.getBlock(sn1 + k, sn3 - 1, sn2 + l);
                if (field_907_p.func_147478_e(sn1 + k, sn3, sn2 + l, false) && b != Blocks.ice && b != Blocks.water && sn3 > 63)
                {
                    field_907_p.setBlock(sn1 + k, sn3, sn2 + l, Blocks.snow_layer, 0, 2);
                }
            }
        }
		
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, field_907_p, field_913_j, i, j, false));
        
        BlockSand.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }
	
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.field_907_p.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }
	
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2)
    {        
		if (mapFeaturesEnabled)
        {
			strongholdGenerator.func_151539_a(this, field_907_p, par1, par2, (Block[])null);
			mineshaftGenerator.func_151539_a(this, field_907_p, par1, par2, (Block[])null);
		}	
	}
}
