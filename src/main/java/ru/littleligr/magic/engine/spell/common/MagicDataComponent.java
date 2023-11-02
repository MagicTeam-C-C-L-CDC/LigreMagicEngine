package ru.littleligr.magic.engine.spell.common;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.storage.info.FormInfo;

import java.util.Optional;

public interface MagicDataComponent extends Component {
    void setForm(Identifier identifier);
    Identifier getForm();
    Optional<FormInfo> getFormInfo();
}
