/**
 * Below are the colors that are used in the app. The colors are defined in the light and dark mode.
 * There are many other ways to style your app. For example, [Nativewind](https://www.nativewind.dev/), [Tamagui](https://tamagui.dev/), [unistyles](https://reactnativeunistyles.vercel.app), etc.
 */

import { Button, HeaderBackground } from "@react-navigation/elements";

const tintColorLight = '#0a7ea4';
const tintColorDark = '#fff';

export const Colors = {
  light: {
    text: 'rgb(0, 0, 0)',
    background: '#fff',
    headerBackground: 'rgb(88, 88, 88)',
    tint: tintColorLight,
    icon: '#687076',
    tabIconDefault: '#687076',
    tabIconSelected: tintColorLight,
    button: 'rgb(20, 41, 32)',
    linklogin: 'rgb(50, 120, 201)',
    contentBackground: 'rgb(218, 218, 218)',
    shadowColor: 'rgb(0, 0, 0)',
  },
  dark: {
    text: 'rgb(255, 255, 255)',
    background: '#151718',
    headerBackground: 'rgb(88, 88, 88)',
    tint: tintColorDark,
    icon: '#9BA1A6',
    tabIconDefault: '#9BA1A6',
    tabIconSelected: tintColorDark,
    button: 'rgb(112, 162, 136)',
    linklogin: 'rgb(50, 120, 201)',
    contentBackground: 'rgb(14, 15, 15)',
    shadowColor: 'rgb(255, 255, 255)',
  },
};
