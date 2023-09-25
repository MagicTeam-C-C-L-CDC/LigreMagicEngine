package ru.littleligr.magic.engine.ui.dynamichud;

import com.tanishisherewith.dynamichud.DynamicHUD;
import com.tanishisherewith.dynamichud.huds.MoveableScreen;
import com.tanishisherewith.dynamichud.interfaces.IWigdets;
import com.tanishisherewith.dynamichud.interfaces.WidgetLoading;
import com.tanishisherewith.dynamichud.util.DynamicUtil;
import com.tanishisherewith.dynamichud.widget.Widget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import ru.littleligr.magic.engine.ui.dynamichud.widgets.ArmWidget;
import ru.littleligr.magic.engine.ui.dynamichud.widgets.HotbarWidget;
import ru.littleligr.magic.engine.ui.dynamichud.widgets.ManaWidget;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.tanishisherewith.dynamichud.DynamicHUD.WIDGETS_FILE;

public class HUD implements IWigdets, WidgetLoading {
    protected List<Widget> widgets = new LinkedList<>();
    private final MinecraftClient client;

    public static DynamicUtil dynamicutil;

    private HotbarWidget hotbar;


    public HUD(MinecraftClient client) {
        this.client = client;

        dynamicutil = DynamicHUD.getDynamicUtil();

        DynamicHUD.setAbstractScreen(new MoveableScreen(Text.of("Editor Screen"), dynamicutil));
        DynamicHUD.setIWigdets(this);
        dynamicutil.getWidgetManager().setWidgetLoading(this);
    }

    @Override
    public void addWigdets(DynamicUtil dynamicUtil) {
        widgets.add(new ManaWidget(client));
        hotbar = new HotbarWidget(client);
        widgets.add(hotbar);
        widgets.add(new ArmWidget(client));

        for (Widget wigdet : widgets)
            dynamicUtil.getWidgetManager().addWidget(wigdet);
        dynamicUtil.WidgetAdded = true;
    }

    @Override
    public void addMainMenuWigdets(DynamicUtil dynamicUtil) {
        dynamicUtil.MainMenuWidgetAdded = true;
    }

    @Override
    public void loadWigdets(DynamicUtil dynamicUtil) {
        Set<Widget> widgets = dynamicUtil.getWidgetManager().loadWigdets(WIDGETS_FILE);
        Set<Widget> MainMenuWidget = dynamicUtil.getWidgetManager().loadMainMenuWigdets(WIDGETS_FILE);

        for (Widget widget : widgets) {
            dynamicUtil.getWidgetManager().addWidget(widget);
            if (widget instanceof HotbarWidget)
                hotbar = (HotbarWidget) widget;
        }

        for (Widget widgetItem : MainMenuWidget)
            dynamicUtil.getWidgetManager().addMainMenuWidget(widgetItem);

        dynamicUtil.WidgetLoaded = true;
    }

    @Override
    public Widget loadWidgetsFromTag(String className, NbtCompound widgetTag) {
        if (className.equals(ManaWidget.class.getName())) {
            ManaWidget widget = new ManaWidget(MinecraftClient.getInstance());
            widget.readFromTag(widgetTag);
            return widget;
        }
        if (className.equals(ArmWidget.class.getName())) {
            ArmWidget widget = new ArmWidget(MinecraftClient.getInstance());
            widget.readFromTag(widgetTag);
            return widget;
        }
        if (className.equals(HotbarWidget.class.getName())) {
            HotbarWidget widget = new HotbarWidget(MinecraftClient.getInstance());
            widget.readFromTag(widgetTag);
            return widget;
        }
        return WidgetLoading.super.loadWidgetsFromTag(className, widgetTag);
    }

    public HotbarWidget getHotbarWidget() {
        return hotbar;
    }
}
