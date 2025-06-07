import React from "react";
import { Colors } from "@/constants/Colors";
import { useColorScheme } from "@/hooks/useColorScheme";
import { HapticTab } from "@/components/HapticTab";
import TabBarBackground from "@/components/ui/TabBarBackground";
import { Platform } from "react-native";
import MaterialCommunityIcons from "@expo/vector-icons/MaterialCommunityIcons";

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
        tabBarLabelStyle: {
          fontSize: 12,
          fontWeight: "bold",
        },
      }}
    >
      <Tabs.Screen
        name="math"
        options={{
          title: "Matematică",
          headerTitleAlign: "center",
          headerShown: false,
          tabBarIcon: ({ color }) => (
            <MaterialCommunityIcons name="calculator" size={24} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="chemistry"
        options={{
          title: "Chimie",
          headerTitleAlign: "center",
          headerShown: false,
          tabBarIcon: ({ color }) => (
            <MaterialCommunityIcons name="test-tube" size={24} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="tests"
        options={{
          title: "Teste",
          headerTitleAlign: "center",
          headerShown: false,
          tabBarIcon: ({ color }) => (
            <MaterialCommunityIcons
              name="pencil-ruler"
              size={24}
              color={color}
            />
          ),
        }}
      />
    </Tabs>
  );
}
