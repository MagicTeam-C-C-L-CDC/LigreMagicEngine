package ru.ligremagic.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ligremagic.utils.Scheduler;
import ru.ligremagic.utils.Task;

import java.util.LinkedList;

@Mixin(MinecraftServer.class)
public abstract class ServerSchedulerMixin {

	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		LinkedList<Task> toDelete = new LinkedList<>();

		Scheduler.getTasks().forEach(task -> {
			task.currentDelay += 1;
			if (task.currentDelay == task.delay) {
				task.currentDelay = 0;
				if (task.runnable.get())
					toDelete.add(task);
			}
		});

		toDelete.forEach(Scheduler::remove);
	}


}