# Halloween Countdown — Fabric 1.21.11

A client-side Minecraft Java mod for Fabric. It displays a countdown at the top of the HUD to **Sunday, October 25, 2026 at 12:00 PM America/Toronto** and plays an original, looping spooky ambience track while you're in a world.

## Requirements
- Minecraft Java Edition **1.21.11**
- Java **21**
- Fabric Loader **0.18.1 or newer**
- Fabric API for Minecraft 1.21.11

## Build the mod
1. Install Java 21.
2. Install Gradle 8.14+ (or open this folder in IntelliJ IDEA and let Gradle import the project).
3. In this folder, run `gradle build`.
4. The mod JAR will be created in `build/libs/`, named similar to `halloween-countdown-1.0.0.jar`.

## Install
1. Install Fabric for Minecraft 1.21.11.
2. Put the built `halloween-countdown-1.0.0.jar` in your Minecraft `.minecraft/mods` folder.
3. Put a matching Fabric API JAR in the same `mods` folder.
4. Launch the Fabric 1.21.11 profile.

The mod is client-side: each player who wants the countdown and music must install it. It doesn't need to be installed on a server.

## Soundtrack
The included `assets/halloweencountdown/sounds/spooky_loop.ogg` is an original synthesized spooky ambience loop created for this mod. The mod uses Minecraft's MUSIC sound category, so the music slider controls its volume.

## Notes
- The countdown uses the fixed Toronto event instant `2026-10-25T16:00:00Z`, which is noon in Toronto on that date.
- Once the event time passes, the HUD switches to the event-started message.
- This source project targets the standard Yarn-mapped Fabric toolchain for 1.21.11.
