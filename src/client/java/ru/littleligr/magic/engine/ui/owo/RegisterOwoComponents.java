package ru.littleligr.magic.engine.ui.owo;

import io.wispforest.owo.ui.parsing.UIParsing;
import ru.littleligr.magic.engine.ui.owo.components.RoundSelector;
import ru.littleligr.magic.engine.ui.owo.components.TexturedButtonComponent;

public class RegisterOwoComponents{

    public void register() {
        UIParsing.registerFactory( "textured-button", TexturedButtonComponent::parse);
        UIParsing.registerFactory( "round-selector", RoundSelector::parse);
    }
}
