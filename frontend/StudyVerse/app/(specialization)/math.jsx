import {
  View,
  Text,
  StyleSheet,
  Appearance,
  Button,
  Pressable,
} from "react-native";
import React from "react";

import LessonsList from "@/components/LessonsList";
import AntDesign from "@expo/vector-icons/AntDesign";
import { Link } from "expo-router";
import { Colors } from "@/constants/Colors";

const colorScheme = Appearance.getColorScheme();

const theme = colorScheme === "dark" ? Colors.dark : Colors.light;

export default function MathLectures() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Matematică</Text>
      <Text>{"\n"}</Text>

      <Link href="../../(math)/lessons" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Lecții</Text>
        </Pressable>
      </Link>

      <Link href="../../(math)/tests" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Teste</Text>
        </Pressable>
      </Link>

      <Link href="../../(math)/drawing" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Desen geometric</Text>
        </Pressable>
      </Link>

      <Link href="../../(math)/games" asChild>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Jocuri</Text>
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
