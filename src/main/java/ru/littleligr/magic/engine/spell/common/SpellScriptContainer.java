package ru.littleligr.magic.engine.spell.common;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import ru.littleligr.magic.engine.storage.info.SpellInfo;

import java.util.List;

public interface SpellScriptContainer extends Component, AutoSyncedComponent {

    void selectSpell(int id);

    SpellInfo getSpellInfo();

    int selectedId();

    List<SpellInfo> getSpells();

    int spellCapacity();
}
