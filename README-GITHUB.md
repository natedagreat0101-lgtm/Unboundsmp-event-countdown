# Halloween Countdown — GitHub Actions build

This is a client-side Minecraft Java mod project for Fabric 1.21.11. It includes an original looping spooky ambience track and a HUD countdown to Sunday, October 25, 2026 at 12:00 PM America/Toronto.

## Build the JAR through GitHub (no local Gradle setup)

1. On GitHub, create a new repository, e.g. `HalloweenCountdownFabric`.
2. Extract the downloaded project ZIP on your computer.
3. Upload the **contents inside** the `HalloweenCountdownFabric` folder to the root of your repository. Be sure to include the hidden `.github/workflows/build.yml` file. On Windows, hidden folders may not be visible by default.
4. Commit the files to the `main` branch.
5. Open the repository's **Actions** tab. If prompted, enable GitHub Actions.
6. Open **Build Halloween Countdown Mod** and select the newest workflow run.
7. Wait for a green check mark. At the bottom of the run, under **Artifacts**, download `halloween-countdown-fabric-1.21.11`.
8. Extract the downloaded artifact ZIP and put `halloween-countdown-1.0.0.jar` in `.minecraft/mods`.
9. Install Fabric Loader for Minecraft 1.21.11 and a matching Fabric API JAR, then launch the Fabric profile.

Every push to `main` or `master` automatically starts a build. You can also run one manually using **Actions → Build Halloween Countdown Mod → Run workflow**.

## Included features

- Live countdown HUD at the top of the screen.
- Target instant is `2026-10-25T16:00:00Z`, which is noon in Toronto on October 25, 2026.
- Original synthesized spooky ambience loop while in a world.
- Event-started message after the countdown ends.

This is client-side: every player who wants the countdown and music needs to install it. The server does not need the mod.

This is the source project, not a prebuilt JAR. If GitHub Actions fails, open the failed run and share the red error log to troubleshoot it.
