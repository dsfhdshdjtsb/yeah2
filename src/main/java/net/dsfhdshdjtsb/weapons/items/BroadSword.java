package net.dsfhdshdjtsb.weapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class BroadSword extends AxeItem {
    public BroadSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        List<LivingEntity> list = attacker.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(5.0D, 0.25D, 5.0D));
        boolean bl = false;
        for(LivingEntity e : list)
        {
            if(!e.equals(target) && !e.equals(attacker)) {
                bl = true;
                e.takeKnockback(1.5, -(e.getX() - attacker.getX()), -(e.getZ() - attacker.getZ()));
            }
        }

        if (bl && attacker.world instanceof ServerWorld) {
                for (double x = -6; x <= 6; x = x + 0.5) {
                    double y = Math.sqrt(36 - x * x);
                    ((ServerWorld) attacker.world).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                    ((ServerWorld) attacker.world).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
                }
        }

        return super.postHit(stack, target, attacker);
    }
}
