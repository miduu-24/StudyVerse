import React from "react";
import { View, Text, StyleSheet, Appearance } from "react-native";
import { Colors } from "@/constants/Colors";

const colorScheme = Appearance.getColorScheme();
const theme = colorScheme === "dark" ? Colors.dark : Colors.light;

export default function ProgressBar({ score = 0 }) {
  const level = Math.floor(score / 100);
  const progress = score % 100;

  return (
    <View style={styles.container}>
      <Text style={styles.levelText}>Level {level}</Text>
      <View style={styles.barContainer}>
        <View style={[styles.progress, { width: `${progress}%` }]} />
      </View>
      <Text style={styles.percentText}>{progress}%</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    marginTop: 0,
    paddingHorizontal: 20,
    width: "100%",
  },
  levelText: {
    fontSize: 16,
    marginBottom: 6,
    fontWeight: "bold",
    color: "#333",
  },
  barContainer: {
    height: 16,
    backgroundColor: "#e0e0e0",
    borderRadius: 8,
    overflow: "hidden",
  },
  progress: {
    height: "100%",
    backgroundColor: theme.button,
    borderRadius: 8,
  },
  percentText: {
    marginTop: 4,
    fontSize: 12,
    color: "#666",
    textAlign: "right",
  },
});
