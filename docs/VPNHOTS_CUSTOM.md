# VPNHots UI customization

This branch contains the source of [Mygod/VPNHotspot](https://github.com/Mygod/VPNHotspot)
at commit `6e3d4a4f9db4f72d0d4a422350b5234cddb52c48` with the
Modern Dark + Mint Green UI patch applied.

- Built and tested as a debug APK on GitHub Actions (no on-device testing yet).
- UI improvements: dark mint color scheme, tethering dashboard and bottom navigation styling.
- VPN and tethering core (including native Rust daemon) are retained from upstream.
- V2RayTun start/stop and saved-server selection are **not** implemented.
- Data caps and game-mode shown in mockups are **not** implemented.
- Original Apache-2.0 licensing and attributions remain in the codebase.
- Native AOSP submodule: clone with `git clone --recurse-submodules`.

This is an independently maintained customization, not an official upstream release.
