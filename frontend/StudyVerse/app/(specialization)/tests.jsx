import {
  View,
  Text,
  StyleSheet,
  Appearance,
  Button,
  Pressable,
} from "react-native";
import React from "react";

import { Colors } from "@/constants/Colors";
import { Link } from "expo-router";

const colorScheme = Appearance.getColorScheme();

const theme = colorScheme === "dark" ? Colors.dark : Colors.light;

export default function Tests() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Teste</Text>
      <Text>{"\n"}</Text>
      <Link href="" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Teste de matematică</Text>
        </Pressable>
      </Link>
      <Link href="" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Teste de chimie</Text>
        </Pressable>
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    backgroundColor: theme.background,
  },
  title: {
    fontSize: 30,
    fontWeight: "bold",
    color: theme.text,
    textAlign: "center",
    marginTop: 50,
  },

  button: {
    backgroundColor: theme.button,
    borderColor: theme.tint,
    borderRadius: 5,
    margin: 10,
    padding: 10,
    borderWidth: 1,
    color: theme.text,
    textAlign: "center",
    width: "80%",
    height: 60,
    alignSelf: "center",
  },
  buttonText: {
    color: theme.text,
    fontSize: 20,
    textAlign: "center",
    fontWeight: "bold",
    marginTop: 5,
  },
});
