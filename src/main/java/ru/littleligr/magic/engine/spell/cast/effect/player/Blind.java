package ru.littleligr.magic.engine.spell.cast.effect.player;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.cast.effect.SpellEffect;
import ru.littleligr.magic.engine.spell.common.PipeCallback;

import java.util.Optional;

public class Blind implements SpellEffect {

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {
        StatusEffects.BLINDNESS.applyInstantEffect(spellOwner, spellOwner, spellOwner, 1, 1);
    }

    @Override
    public void call(PlayerEntity spellOwner, LivingEntity target, PipeCallback callback) {
        StatusEffects.BLINDNESS.applyInstantEffect(spellOwner, spellOwner, target, 1, 1);
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }
}
