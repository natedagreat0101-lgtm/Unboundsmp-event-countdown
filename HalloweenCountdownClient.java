package com.nate.halloweencountdown;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.time.Duration;
import java.time.Instant;

public class HalloweenCountdownClient implements ClientModInitializer {
    private static final Identifier SPOOKY_ID = Identifier.of("halloweencountdown", "spooky_loop");
    // Sunday, October 25, 2026 at 12:00 PM in Toronto (EDT, UTC-4).
    private static final Instant EVENT_TIME = Instant.parse("2026-10-25T16:00:00Z");
    private static SoundEvent spookySound;
    private static SoundInstance spookyInstance;
    private static int musicCheckTicks = 0;

    @Override
    public void onInitializeClient() {
        spookySound = Registry.register(Registries.SOUND_EVENT, SPOOKY_ID, SoundEvent.of(SPOOKY_ID));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) {
                if (client.getSoundManager() != null && spookyInstance != null) {
                    client.getSoundManager().stop(spookyInstance);
                    spookyInstance = null;
                }
                return;
            }

            // Start the soundtrack once in a world, and recover if Minecraft replaces it.
            if (spookyInstance == null || !client.getSoundManager().isPlaying(spookyInstance)) {
                client.getSoundManager().stopSounds(null, SoundCategory.MUSIC);
                spookyInstance = new LoopingSpookySound(spookySound);
                client.getSoundManager().play(spookyInstance);
            }

            // Periodically clear any vanilla track that may have started in the MUSIC
            // category, then restart our loop on the next tick. This keeps vanilla music
            // from taking over; there may be a very brief restart between loops.
            musicCheckTicks++;
            if (musicCheckTicks >= 400) {
                musicCheckTicks = 0;
                client.getSoundManager().stopSounds(null, SoundCategory.MUSIC);
                spookyInstance = null;
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            long secondsLeft = Duration.between(Instant.now(), EVENT_TIME).getSeconds();
            String line;
            int color;
            if (secondsLeft > 0) {
                long days = secondsLeft / 86400;
                long hours = (secondsLeft % 86400) / 3600;
                long minutes = (secondsLeft % 3600) / 60;
                long seconds = secondsLeft % 60;
                line = String.format("§6☠ HALLOWEEN EVENT IN: §f%02dd %02dh %02dm %02ds", days, hours, minutes, seconds);
                color = 0xFFFF8A22;
            } else {
                line = "§6☠ THE HALLOWEEN EVENT IS HERE! ☠";
                color = 0xFFFF8A22;
            }

            int width = client.textRenderer.getWidth(Text.literal(line));
            int x = (client.getWindow().getScaledWidth() - width) / 2;
            drawContext.drawTextWithShadow(client.textRenderer, Text.literal(line), x, 8, color);
        });
    }

    private static final class LoopingSpookySound extends AbstractSoundInstance {
        private LoopingSpookySound(SoundEvent sound) {
            super(sound, SoundCategory.MUSIC, Random.create());
            this.repeat = true;
            this.repeatDelay = 0;
            this.volume = 0.75F;
            this.pitch = 1.0F;
            this.relative = true;
        }

        @Override
        public SoundInstance.AttenuationType getAttenuationType() {
            return SoundInstance.AttenuationType.NONE;
        }
    }
}
