package ru.littleligr.magic.engine.ui.owo;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.control.RegisterKeys;

public class DeliverySelectScreen  extends BaseUIModelScreen<FlowLayout> {
    public DeliverySelectScreen() {
        super(FlowLayout.class, BaseUIModelScreen.DataSource.asset(new Identifier(LigreMagicEngine.MOD_ID, "delivery_select")));
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (RegisterKeys.openMagicScreen.matchesKey(keyCode, scanCode))
            this.close();
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    protected void build(FlowLayout rootComponent) {

    }
}