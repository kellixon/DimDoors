package org.dimdev.dimdoors.world.feature.gateway;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SandstonePillarsGateway extends SchematicGateway {
    public SandstonePillarsGateway() {
        super("sandstone_pillars");
    }

    @Override
    public Set<RegistryKey<Biome>> getBiomes() {
        return ImmutableSet.of( BiomeKeys.DESERT, BiomeKeys.DESERT_LAKES, BiomeKeys.DESERT_HILLS);
    }
}
