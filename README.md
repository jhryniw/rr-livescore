<img
    src="/app/src/main/res/drawable/logo.png"
    width="160"
    height="120"
    title="Logo"
    align="right"
    alt="Relic Recovery Logo"/>

# FTC Relic Recovery Livescoring
The Android mobile component of a livescoring system for the Relic Recovery game. The web component, where the scores are displayed, is 
hosted at [livescoring.ftcalberta.ca](livescoring.ftcalberta.ca) with source code available on Github at [rr-livescore-web](https://github.com/jhryniw/rr-livescore-web).

## Usage Guide
In a competition scenario, **four phones** are intended to be running the app simulataneously. Ideally, each phone should be held by a seperate scorekeeper, responsible for one quarter of the field. 

<img
    src="/docs/settings.png"
    width="160"
    title="Settings"
    align="right"
    alt="Settings Screen"/>
### Settings
The settings page, accessed via the overflow menu at the top right of the main screen, allows the scorekeeper to identify themselves to the system. This is determined by alliance color and cryptobox id. The alliance color can be either _red_ or _blue_ and the cryptobox id can be either _front_ or _back_. A "front" cryptobox is defined as the cryptoboxes of each alliance color nearest their respective Relic Recovery mats.

**Important:** Every scorekeeper must have a **unique** alliance-cryptobox id combination 

### Scoring
Scoring is split into two modes: `Autonomous` mode and `Teleop` mode. You can switch between modes by swiping left or right or using the navigation bar at the bottom of the main screen. To reset the score, press the refresh icon in the app bar.

<p align="center">
    <img
        src="/docs/autonomous.png"
        width="240"
        title="Autonomous"
        alt="Autonomous Screen"/>
    <img
        src="/docs/teleop.png"
        width="240"
        title="Teleop"
        alt="Teleop Screen"/>
</p>
    
#### Placing Glyphs `Autonomous` `Teleop`
Glyphs are placed by clicking the square buttons inside the cryptobox in either autonomous or teleop mode. When clicked again, the color alternates between gray and brown. Long-clicking a scored glyph de-scores that glyph.

<p align="center">
    <img
        src="/docs/clear_glyph.png"
        width="80"
        title="Clear Glyph"
        alt="Clear Glyph"/>
    <img
        src="/docs/gray_glyph.png"
        width="83"
        title="Gray Glyph"
        alt="Gray Glyph"/>
    <img
        src="/docs/brown_glyph.png"
        width="83"
        title="Brown Glyph"
        alt="Brown Glyph"/>
    <img
        src="/docs/ghost_glyph.png"
        width="79"
        title="Ghost Glyph"
        alt="Ghost Glyph"/>
</div>

In teleop mode, long clicking an un-scored glyph produces a "ghost" glyph. Ghost glyphs do not count towards the glyph score, but can create row bonuses. In the following screenshot, blue counts 6 glyphs and has **2 row bonuses**.

<p align="center">
    <img
            src="/docs/ghost_row.png"
            width="240"
            title="Ghost Row"
            alt="Ghost Row"/>
</p>

Notes:
* The two scoring modes are independent. Glyphs scored in autonomous must be re-placed in teleop when the game period changes.
* Descoring a glyph in autonomous that was awarded the key column bonus **does not descore the key column bonus**. If the bonus was awarded in error, reset the score to try again.

#### Key Column Bonus `Autnomous`
The key cokumn can be set by pressing in the space above each cryptobox column. The key column can only be set **before** the first glyph is placed.

Notes:
* The cryptobox in the app is as viewed from the front. This means that a right key column corresponds to the right column on screen.
* Key column bonuses cannot be scored/descored once the first glyph is placed -- the phone score must be reset.

#### Jewet Set `Autonomous`
The jewels are meant to be scored by matching the state of the jewels on the field. Pressing a jewel toggles its presence.

<p align="center">
    <img
            src="/docs/jewel_set.png"
            height="85"
            title="Jewel Set"
            alt="Jewel Set"/>
</p>

Notes:
* This is the only type of scoring that can score for the other team. Consider this if transferring the score to the official scoresheets.

#### Robot Parking `Autonomous`
Pressing toggles the parked state -- no robot icon means not parked.

<p align="center">
    <img
            src="/docs/nopark.png"
            height="85"
            title="No Park"
            alt="No Park"/>
    <img
            src="/docs/park.png"
            height="85"
            title="Park"
            alt="Park"/>
</p>

#### Row, Column and Cipher Bonuses `Teleop`
These bonuses are tracked **automatically**. Simply place the glyphs in the cryptobox as you see them.

Notes:
* These bonuses are determined based on the teleop cryptobox state, not the autonomous one.

#### Relic Recovery `Teleop`
Pressing a relic mat zone (1-3) scores the relic in that respective zone. Further presses toggle whether the relic is upright or not. Long-pressing descores the relic. Each scorekeeper is responsible for scoring the relic placed in their quarter of the field (ex blue-back) **at the start of the match**. 

<p align="center">
    <img
            src="/docs/relic.png"
            height="85"
            title="Relic"
            alt="Relic"/>
</p>

#### Robot Balance `Teleop`
Pressing toggles the balanced state. Each scorekeeper tracks their respective balancing stone.

<p align="center">
    <img
            src="/docs/nobalance.png"
            height="85"
            title="No Balance"
            alt="No Balance"/>
    <img
            src="/docs/balance.png"
            height="85"
            title="Balance"
            alt="Balance"/>
</p>

## Troubleshooting
#### "Authentication Failed"
The Google Play drivers are likely out of date. Update them on the `Google Play Store` to restore function. 

## Contribution Guidelines
Contributions are welcome. If you'd like to contribute, fork this repository and submit a pull request. All changes should pass existing unit/instrumentation tests and new features should be accompanied by additional testing.
