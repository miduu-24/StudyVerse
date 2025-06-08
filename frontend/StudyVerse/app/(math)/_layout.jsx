import React from "react";
import { Colors } from "@/constants/Colors";
import { useColorScheme } from "@/hooks/useColorScheme";
import { HapticTab } from "@/components/HapticTab";
import TabBarBackground from "@/components/ui/TabBarBackground";
import { Platform } from "react-native";

import { Tabs } from "expo-router";

export default function LectiiLayout() {
  const colorScheme = useColorScheme();
  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: Colors[colorScheme ?? "light"].tint,
        headerShown: false,
        tabBarButton: HapticTab,
        tabBarBackground: TabBarBackground,
        tabBarStyle: Platform.select({
          ios: {
            // Use a transparent background on iOS to show the blur effect
            position: "absolute",
          },
          default: {},
        }),
      }}
    >
      <Tabs.Screen
        name="lessons"
        options={{
          title: "Lectii",
          headerTitleAlign: "center",
          headerShown: false,
        }}
      />
      <Tabs.Screen
        name="tests"
        options={{
          title: "Teste",
          headerTitleAlign: "center",
          headerShown: false,
        }}
      />
      <Tabs.Screen
        name="drawing"
        options={{
          title: "Desen",
          headerTitleAlign: "center",
          headerShown: true,
        }}
      />
      <Tabs.Screen
        name="games"
        options={{
          title: "Jocuri",
          headerTitleAlign: "center",
          headerShown: true,
        }}
      />
    </Tabs>
  );
}
