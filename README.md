# Buttons

Buttons is a mod that adds utility buttons to the left side of your inventory screen. 

There is a large focus on stability, performance, and ease of use.

This means:

* not a coremod
* no dependencies
* clean API for developers
* nothing but buttons

## Download

Downloads can be found on [CurseForge](CURSE.LINK.HERE).

## Developing Addons

Add to your `build.gradle`:

```groovy
    repositories {
        maven {
            // location of the maven that hosts Buttons' files
            url "http://tehnut.info/maven/"
        }
    }

    dependencies {
        deobfCompile "info.tehnut.buttons:Buttons-${mc_version}:${buttons_version}"
    }
```

## Plugin Examples (how to use the API)

* [Default plugin](https://github.com/TehNut/Buttons/tree/1.10/src/main/java/tehnut/buttons/plugins/buttons)