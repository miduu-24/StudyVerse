import { useFonts } from "expo-font";
import { Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";
import { Appearance } from "react-native";
import { AuthProvider } from "../context/AuthContext";

import { Colors } from "@/constants/Colors";

export default function RootLayout() {
  const colorScheme = Appearance.getColorScheme();

  const theme = colorScheme === "dark" ? Colors.dark : Colors.light;

  const [loaded] = useFonts({
    SpaceMono: require("../assets/fonts/SpaceMono-Regular.ttf"),
  });

  if (!loaded) {
    // Async font loading only occurs in development.
    return null;
  }

  return (
    <AuthProvider>
      <Stack
        screenOptions={{
          headerStyle: { backgroundColor: theme.headerBackground },
          headerTintColor: theme.text,
          headerShadowVisible: false,
        }}
      >
        <Stack.Screen
          name="index"
          options={{ title: "Home", headerTitleAlign: "center" }}
        />
        <Stack.Screen
          name="(tabs)"
          options={{ title: "", headerShown: false }}
        />
        <Stack.Screen
          name="(specialization)"
          options={{ title: "", headerTitleAlign: "center", headerShown: true }}
        />
        <Stack.Screen
          name="(math)"
          options={{ title: "", headerTitleAlign: "center", headerShown: true }}
        />
        <StatusBar style={colorScheme === "dark" ? "light" : "dark"} />
      </Stack>
    </AuthProvider>
  );
}
