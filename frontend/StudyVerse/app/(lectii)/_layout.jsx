import React from 'react';
import { Colors } from '@/constants/Colors';
import { useColorScheme } from '@/hooks/useColorScheme';
import { HapticTab } from '@/components/HapticTab';
import TabBarBackground from '@/components/ui/TabBarBackground';
import { Platform } from 'react-native';

import {Tabs } from 'expo-router';

export default function LectiiLayout() {
  const colorScheme = useColorScheme();
  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: Colors[colorScheme ?? 'light'].tint,
        headerShown: false,
        tabBarButton: HapticTab,
        tabBarBackground: TabBarBackground,
        tabBarStyle: Platform.select({
          ios: {
            // Use a transparent background on iOS to show the blur effect
            position: 'absolute',
          },
          default: {},
        }),
      }}>
      <Tabs.Screen
        name="math_lecture"
        options={{
          title: 'Matematică',
          headerTitleAlign: 'center',
          headerShown: true,
        }}
      />
      <Tabs.Screen
        name="chim_lecture"
        options={{
          title: 'Chimie',
          headerTitleAlign: 'center',
          headerShown: true,
        }}
      />
      <Tabs.Screen
        name="tests"
        options={{
          title: 'Teste',
          headerTitleAlign: 'center',
          headerShown: true,
        }}
      />
    </Tabs>
  );
}