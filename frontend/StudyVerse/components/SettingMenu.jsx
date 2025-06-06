import React, { useState } from "react";
import { View, Pressable, Text, StyleSheet, Dimensions } from "react-native";
import Ionicons from "@expo/vector-icons/Ionicons";
import Feather from "@expo/vector-icons/Feather";

export default function SettingMenu({ actions = [] }) {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <>
      {/* 🕶️ Overlay: umbra pe tot ecranul */}
      {menuOpen && (
        <Pressable style={styles.overlay} onPress={() => setMenuOpen(false)} />
      )}

      {/* 🔘 Meniul */}
      <View style={styles.container}>
        {menuOpen && (
          <View style={styles.actionsContainer}>
            {actions.map((action, index) => (
              <Pressable
                key={index}
                style={[styles.actionButton, { bottom: 70 + index * 60 }]}
                onPress={() => {
                  setMenuOpen(false);
                  action.onPress();
                }}
              >
                <Text style={styles.actionText}>{action.label}</Text>
              </Pressable>
            ))}
          </View>
        )}

        {/* 🎯 Butonul principal */}
        <Pressable
          style={styles.fab}
          onPress={() => setMenuOpen((prev) => !prev)}
        >
          {menuOpen ? (
            <Feather name="x" size={28} color="#fff" />
          ) : (
            <Ionicons name="reorder-three-sharp" size={32} color="#fff" />
          )}
        </Pressable>
      </View>
    </>
  );
}

const { width, height } = Dimensions.get("window");

const styles = StyleSheet.create({
  overlay: {
    position: "absolute",
    top: 0,
    left: 0,
    width,
    height,
    backgroundColor: "rgba(0, 0, 0, 0.4)",
    zIndex: 998,
  },
  container: {
    position: "absolute",
    bottom: 100,
    right: 20,
    zIndex: 999,
  },
  fab: {
    backgroundColor: "#000",
    width: 60,
    height: 60,
    borderRadius: 30,
    justifyContent: "center",
    alignItems: "center",
    elevation: 6,
  },
  actionsContainer: {
    position: "absolute",
    bottom: 0,
    right: 0,
  },
  actionButton: {
    position: "absolute",
    right: 0,
    backgroundColor: "#fff",
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 20,
    elevation: 4,
  },
  actionText: {
    color: "#333",
    fontWeight: "500",
  },
});
