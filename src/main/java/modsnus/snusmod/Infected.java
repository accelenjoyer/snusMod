package modsnus.snusmod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class Infected extends  PathAwareEntity   {
    private int ticksSinceLastSound = 0;
    private final int SOUND_COOLDOWN = 60;
    private boolean hasPlayedSound = false;
    private int ticksSinceLastAttack = 0;
    public Infected(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.setBoundingBox(new Box(2D, 3.0D, 1.3D, 0.3D, 1.8D, 0.3D));
    }


    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }
    private BlockPos targetPosition;
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isFire() || source.isExplosive()) {
            return false;
        }
        return super.damage(source, amount);
    }




    @Override
    protected void mobTick() {

        if (!this.isDead()) {
            BlockPos currentPos = this.getBlockPos();
            PlayerEntity nearestPlayer = this.world.getClosestPlayer(currentPos.getX(), currentPos.getY(), currentPos.getZ(), 15.0, true);
            if (nearestPlayer != null) {
                super.mobTick();
                this.setVelocity(this.getVelocity().multiply(0.0D, -0.5D, 0.0D));
                this.getNavigation().startMovingTo(nearestPlayer, 1.0);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,20*20,1));

                ticksSinceLastAttack++;
                if (ticksSinceLastAttack >= 60) {
                    FireballEntity fireball = new FireballEntity(this.world, this, 2, 2, 2);
                    fireball.setPos(this.getX(), this.getY(), this.getZ());
                    fireball.setVelocity(nearestPlayer.getX() - this.getX(), nearestPlayer.getY() - this.getY(), nearestPlayer.getZ() - this.getZ());
                    this.world.spawnEntity(fireball);
                    playSound(Snusmod.INFECTED_SOUND, 1.0F, 1.0F);
                    ticksSinceLastAttack = 0; // Сбрасываем счетчик времени с момента последней атаки
                }
            } else {
                BlockPos targetPos = currentPos.add(this.random.nextInt(21) - 10, 0, this.random.nextInt(21) - 10);
                this.getNavigation().startMovingTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1.0);
            }
        }
    }

}




