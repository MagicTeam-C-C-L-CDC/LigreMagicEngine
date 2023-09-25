package ru.littleligr.magic.engine.spell.cast.form;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.common.PipeCallback;

import java.util.Optional;

public class DeliverySelf implements SpellDelivery{

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {
        callback.call(spellOwner);
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }
}
