import React, { useState } from "react";
import { View, Text, TextInput, Button, StyleSheet, Alert } from "react-native";

function generateRandomNumber() {
  return (Math.floor(1 + Math.random() * 100 + (Date.now() % 100)) % 100) + 1;
}

export default function NumberGuessingGame() {
  const [targetNumber, setTargetNumber] = useState(generateRandomNumber());
  const [guess, setGuess] = useState("");
  const [message, setMessage] = useState("");
  const [gameOver, setGameOver] = useState(false);
  const [attempts, setAttempts] = useState(0);

  const handleGuess = () => {
    const numericGuess = parseInt(guess);
    if (isNaN(numericGuess)) {
      Alert.alert("Introduce un număr valid.");
      return;
    }
    setAttempts((prev) => prev + 1);
    if (numericGuess === targetNumber) {
      setMessage(`✔️ Ai ghicit numărul în ${attempts + 1} încercări!`);
      setGameOver(true);
    } else if (numericGuess < targetNumber) {
      setMessage("⬆️ Numărul este mai mare.");
    } else {
      setMessage("⬇️ Numărul este mai mic.");
    }
    setGuess("");
  };

  const resetGame = () => {
    setTargetNumber(generateRandomNumber());
    setGuess("");
    setMessage("");
    setGameOver(false);
    setAttempts(0);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>🎯 Ghicește numărul (1-100)</Text>
      <TextInput
        style={styles.input}
        keyboardType="numeric"
        value={guess}
        onChangeText={setGuess}
        editable={!gameOver}
        placeholder="Introdu un număr"
      />
      {!gameOver && <Button title="Ghicește" onPress={handleGuess} />}
      {message !== "" && <Text style={styles.message}>{message}</Text>}
      <Text style={styles.attempts}>Încercări: {attempts}</Text>
      {gameOver && <Button title="Joacă din nou" onPress={resetGame} />}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f5f5f5",
    padding: 20,
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
    fontWeight: "bold",
  },
  input: {
    height: 50,
    width: 200,
    borderColor: "#ccc",
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 10,
    backgroundColor: "#fff",
  },
  message: {
    fontSize: 18,
    marginTop: 20,
    textAlign: "center",
  },
  attempts: {
    fontSize: 16,
    marginTop: 10,
    fontWeight: "bold",
  },
});
