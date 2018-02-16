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

### Settings
The settings page allows the scorekeeper to identify themselves to the system. This is determined by alliance color and cryptobox id. The alliance color can be either _red_ or _blue_ and the cryptobox id can be either _front_ or _back_. A "front" cryptobox is defined as the cryptoboxes of each alliance color nearest their respective Relic Recovery mats.

**Important:** Every scorekeeper must have a **unique** alliance-cryptobox id combination 

## Contribution Guidelines
Contributions are welcome. If you'd like to contribute, fork this repository and submit a pull request. All changes should pass existing unit/instrumentation tests and new features should be accompanied by additional testing.
