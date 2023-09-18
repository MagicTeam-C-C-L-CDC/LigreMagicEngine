package ru.ligremagic.spell.mana;

import ru.ligremagic.animations.PlayerAnimator;
import ru.ligremagic.spell.PlayerMagicData;
import ru.ligremagic.spell.PlayerMagicManager;

public interface IMagicPlayer {
    PlayerMagicManager getPlayerManaManager();
    PlayerMagicData getPlayerMagicData();

    PlayerAnimator getPlayerAnimator();
}
