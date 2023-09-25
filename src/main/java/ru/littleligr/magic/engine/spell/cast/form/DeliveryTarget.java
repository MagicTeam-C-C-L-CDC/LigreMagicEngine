package ru.littleligr.magic.engine.spell.cast.form;


import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.common.PipeCallback;
import ru.littleligr.magic.engine.utils.RaycastUtil;

import java.util.Optional;

public class DeliveryTarget implements SpellDelivery {

    private final int range;

    public DeliveryTarget(int range) {
        this.range = range;
    }

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {
        RaycastUtil.raycast(spellOwner, range, callback);
    }

    @Override
    public float getCost() {
        return 140;
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }

    @Override
    public Optional<Identifier> getSpellCastVisualEffect() {
        return Optional.empty();
    }

    @Override
    public Optional<Identifier> getSpellCastAnimation() {
        return Optional.empty();
    }
}
