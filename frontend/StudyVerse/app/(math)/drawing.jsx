import React, { useState } from "react";
import {
  View,
  TextInput,
  StyleSheet,
  Dimensions,
  Alert,
  Button,
  ScrollView,
  TouchableOpacity,
  Text,
} from "react-native";
import Svg, { Line, Path } from "react-native-svg";
import { parse } from "mathjs";

const { width } = Dimensions.get("window");
const GRAPH_WIDTH = width;
const GRAPH_HEIGHT = 300;
const STEP = 1;

export default function GraphScreen() {
  const [input, setInput] = useState("x^2");
  const [parsedExpr, setParsedExpr] = useState(() => parse("x^2"));

  const handleSubmit = () => {
    if (!input.trim()) {
      Alert.alert("Eroare", "Funcția nu poate fi goală.");
      return;
    }

    if (!input.includes("x")) {
      Alert.alert("Eroare", "Funcția trebuie să conțină variabila x.");
      return;
    }

    try {
      const expr = parse(input);
      expr.evaluate({ x: 1 });
      setParsedExpr(() => expr);
    } catch (e) {
      Alert.alert("Eroare", "Expresie invalidă.");
    }
  };

  const handleInsert = (fragment) => {
    setInput((prev) => prev + fragment);
  };

  const getPath = () => {
    const points = [];
    const scaleX = 20;
    const scaleY = 20;
    const centerX = GRAPH_WIDTH / 2;
    const centerY = GRAPH_HEIGHT / 2;

    for (let x = -GRAPH_WIDTH / 2; x < GRAPH_WIDTH / 2; x += STEP) {
      const xVal = x / scaleX;
      let yVal;

      try {
        yVal = parsedExpr.evaluate({ x: xVal });
      } catch {
        yVal = 0;
      }

      const xPos = centerX + x;
      const yPos = centerY - yVal * scaleY;

      if (!isNaN(yPos)) points.push(`${xPos},${yPos}`);
    }

    return `M${points.join(" L")}`;
  };

  const quickFunctions = [
    "x^2",
    "x^3",
    "sin(x)",
    "cos(x)",
    "sqrt(x)",
    "abs(x)",
  ];

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Svg width={GRAPH_WIDTH} height={GRAPH_HEIGHT} style={styles.graph}>
        <Line
          x1={0}
          y1={GRAPH_HEIGHT / 2}
          x2={GRAPH_WIDTH}
          y2={GRAPH_HEIGHT / 2}
          stroke="gray"
        />
        <Line
          x1={GRAPH_WIDTH / 2}
          y1={0}
          x2={GRAPH_WIDTH / 2}
          y2={GRAPH_HEIGHT}
          stroke="gray"
        />
        <Path d={getPath()} stroke="blue" fill="none" strokeWidth={2} />
      </Svg>

      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        style={styles.quickButtons}
      >
        {quickFunctions.map((fn) => (
          <TouchableOpacity
            key={fn}
            style={styles.quickButton}
            onPress={() => handleInsert(fn)}
          >
            <Text style={styles.quickButtonText}>{fn}</Text>
          </TouchableOpacity>
        ))}
      </ScrollView>

      <View style={styles.inputContainer}>
        <TextInput
          style={styles.input}
          value={input}
          onChangeText={setInput}
          placeholder="Scrie o funcție, ex: x^2 + sin(x)"
        />
        <Button title="Desenează" onPress={handleSubmit} />
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingTop: 40,
    paddingBottom: 24,
    backgroundColor: "#fff",
  },
  graph: {
    backgroundColor: "#f9f9f9",
  },
  quickButtons: {
    paddingHorizontal: 16,
    marginTop: 16,
  },
  quickButton: {
    backgroundColor: "#007AFF",
    paddingVertical: 8,
    paddingHorizontal: 14,
    borderRadius: 20,
    marginRight: 10,
  },
  quickButtonText: {
    color: "#fff",
    fontSize: 16,
  },
  inputContainer: {
    marginTop: 20,
    marginHorizontal: 16,
    flexDirection: "row",
    gap: 12,
    alignItems: "center",
  },
  input: {
    flex: 1,
    borderWidth: 1,
    borderColor: "#ccc",
    padding: 12,
    borderRadius: 8,
    fontSize: 18,
  },
});
