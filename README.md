# Just Enough Widgets (JEW)

JustEnoughWidgets (working title) is a mod that adds different kinds of widgets to the left side of your inventory screen. 

There is a large focus on stability, performance, and ease of use.

This means:

* not a coremod
* no dependencies
* clean API for developers
* nothing but widgets

## Download

Downloads can be found on [CurseForge](CURSE.LINK.HERE).

## Developing Addons

Add to your `build.gradle`:

```groovy
    repositories {
        maven {
            // location of the maven that hosts JEW files
            url "http://tehnut.info/maven/"
        }
    }

    dependencies {
        deobfCompile "info.tehnut.jew:JustEnoughWidgets-${mc_version}:${jew_version}"
    }
```

## Plugin Examples (how to use the API)

***To come soon***