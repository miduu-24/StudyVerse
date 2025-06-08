import React, { useState, useEffect } from "react";
import { View, Text, TextInput, Button, StyleSheet, Alert } from "react-native";

function getRandomDigits() {
  const digits = [];
  while (digits.length < 4) {
    const num = Math.floor(Math.random() * 9) + 1;
    digits.push(num);
  }
  return digits;
}

export default function Make10Game() {
  const [digits, setDigits] = useState(getRandomDigits());
  const [expression, setExpression] = useState("");
  const [feedback, setFeedback] = useState("");

  const resetGame = () => {
    setDigits(getRandomDigits());
    setExpression("");
    setFeedback("");
  };

  const isValidExpression = (expr, digits) => {
    const digitCount = {};
    for (const d of digits) {
      digitCount[d] = (digitCount[d] || 0) + 1;
    }
    const usedDigits = expr.match(/\d/g);
    const usedCount = {};
    for (const d of usedDigits || []) {
      usedCount[d] = (usedCount[d] || 0) + 1;
    }
    for (const d in digitCount) {
      if ((usedCount[d] || 0) !== digitCount[d]) return false;
    }
    const open = (expr.match(/\(/g) || []).length;
    const close = (expr.match(/\)/g) || []).length;
    const validOperators = /^[\d+\-*/()\s]+$/;
    return open <= 1 && close <= 1 && validOperators.test(expr);
  };

  const evaluateExpression = () => {
    try {
      if (!isValidExpression(expression, digits)) {
        setFeedback(
          "❗ Expresia trebuie să conțină exact cifrele date, maxim o pereche de paranteze și doar operatorii: + - * /."
        );
        return;
      }
      const result = eval(expression);
      if (Math.abs(result - 10) < 1e-6) {
        setFeedback("✔️ Corect! Ai obținut 10.");
      } else {
        setFeedback("❌ Rezultatul nu este 10. Încearcă din nou.");
      }
    } catch (e) {
      setFeedback("⚠️ Expresie invalidă. Verifică sintaxa.");
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>🔢 Creează 10</Text>
      <Text style={styles.digits}>Cifre: {digits.join(", ")}</Text>
      <Text style={styles.instructions}>
        Folosește toate cifrele exact o dată și doar aceste operații: + - * /
        (maxim o pereche de paranteze)
      </Text>
      <TextInput
        style={styles.input}
        placeholder="Ex: (2+3)*2"
        value={expression}
        onChangeText={setExpression}
      />
      <Button title="Verifică" onPress={evaluateExpression} />
      {feedback !== "" && <Text style={styles.feedback}>{feedback}</Text>}
      <Button title="Joacă din nou" onPress={resetGame} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 20,
    backgroundColor: "#fff",
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    marginBottom: 20,
  },
  digits: {
    fontSize: 20,
    marginBottom: 10,
  },
  instructions: {
    fontSize: 14,
    marginBottom: 10,
    color: "#555",
    textAlign: "center",
  },
  input: {
    height: 50,
    width: "80%",
    borderColor: "#ccc",
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 10,
    backgroundColor: "#f9f9f9",
  },
  feedback: {
    fontSize: 16,
    marginVertical: 15,
    textAlign: "center",
  },
});
