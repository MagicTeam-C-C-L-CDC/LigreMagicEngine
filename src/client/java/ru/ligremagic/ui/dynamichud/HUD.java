package ru.ligremagic.ui;

import com.tanishisherewith.dynamichud.interfaces.IWigdets;
import com.tanishisherewith.dynamichud.interfaces.WidgetLoading;
import com.tanishisherewith.dynamichud.util.DynamicUtil;
import com.tanishisherewith.dynamichud.widget.Widget;
import net.minecraft.client.MinecraftClient;
import ru.ligremagic.LigreMagic;

import java.util.LinkedList;
import java.util.List;

public class HUD implements IWigdets, WidgetLoading {

    protected List<Widget> widgets = new LinkedList<>();
    private final MinecraftClient client;

    public HUD(MinecraftClient client) {
        this.client = client;
        widgets.add(new ManaWidget(client, null));
    }

    @Override
    public void addWigdets(DynamicUtil dynamicUtil) {
        LigreMagic.LOGGER.info("ADD WIDGET");
        for (Widget wigdet : widgets)
            dynamicUtil.getWidgetManager().addWidget(wigdet);

    }

    @Override
    public void addMainMenuWigdets(DynamicUtil dynamicUtil) {
        LigreMagic.LOGGER.info("ADD WIDGET");
        for (Widget wigdet : widgets)
            dynamicUtil.getWidgetManager().addMainMenuWidget(wigdet);
    }

    @Override
    public void loadWigdets(DynamicUtil dynamicUtil) {

    }
}
