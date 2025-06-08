import React from "react";
import { View, Text, StyleSheet, Pressable, ScrollView } from "react-native";
import { useRouter } from "expo-router";

export default function MathGamesScreen() {
  const router = useRouter();

  const games = [
    {
      title: "Ghicește Numărul",
      route: "/games/NumberGuessingGame",
      description:
        "Un joc simplu în care trebuie să ghicești un număr ales de calculator.",
    },
    {
      title: "Face 10",
      route: "/games/Make10Game",
      description:
        "Un joc provocator în care trebuie să folosești cifrele date pentru a obține 10.",
    },
    // poți adăuga aici alte jocuri în viitor
  ];

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.heading}>🎲 Jocuri de Matematică</Text>
      {games.map((game, index) => (
        <Pressable
          key={index}
          style={styles.card}
          onPress={() => router.push(game.route)}
        >
          <Text style={styles.title}>{game.title}</Text>
          <Text style={styles.description}>{game.description}</Text>
        </Pressable>
      ))}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 50,
    paddingBottom: 40,
    paddingHorizontal: 20,
    backgroundColor: "#fff",
  },
  heading: {
    fontSize: 26,
    fontWeight: "bold",
    marginBottom: 20,
  },
  card: {
    backgroundColor: "#e0e0e0",
    padding: 20,
    borderRadius: 12,
    marginBottom: 16,
  },
  title: {
    fontSize: 20,
    fontWeight: "600",
    marginBottom: 8,
  },
  description: {
    fontSize: 16,
    color: "#444",
  },
});
