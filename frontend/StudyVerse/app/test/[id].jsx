import React, { useEffect, useState } from "react";
import { View, Text, Button, Pressable, ScrollView } from "react-native";
import { useLocalSearchParams, useRouter } from "expo-router";

export default function TestTakingScreen() {
  const { id } = useLocalSearchParams(); // quizId
  const [quiz, setQuiz] = useState(null);
  const [answers, setAnswers] = useState({});
  const router = useRouter();

  useEffect(() => {
    fetch(`http://192.168.100.9:8080/api/quizzes/${id}`)
      .then((res) => res.json())
      .then(setQuiz)
      .catch((err) => console.error("Eroare la preluarea testului:", err));
  }, []);

  const handleSelect = (questionId, answer) => {
    setAnswers((prev) => ({ ...prev, [questionId]: answer }));
  };

  const handleSubmit = () => {
    router.push({
      pathname: "/test/result",
      params: {
        userAnswers: JSON.stringify(answers),
        quizId: id,
        userId: 1, // înlocuiește cu ID-ul userului logat dacă ai auth
      },
    });
  };

  if (!quiz) return <Text>Se încarcă...</Text>;

  return (
    <ScrollView>
      <Text style={{ fontSize: 24, fontWeight: "bold", margin: 10 }}>
        {quiz.quizName}
      </Text>
      {quiz.questions.map((q) => (
        <View key={q.id} style={{ marginBottom: 20, paddingHorizontal: 10 }}>
          <Text style={{ fontWeight: "bold" }}>{q.question}</Text>
          {["A", "B", "C", "D"].map((opt) => {
            const value = q[`option${opt}`];
            const selected = answers[q.id] === value;
            return (
              <Pressable
                key={opt}
                onPress={() => handleSelect(q.id, value)}
                style={{
                  padding: 8,
                  backgroundColor: selected ? "#cce5ff" : "#f0f0f0",
                  borderRadius: 4,
                  marginTop: 5,
                }}
              >
                <Text>{value}</Text>
              </Pressable>
            );
          })}
        </View>
      ))}
      <Button title="Trimite testul" onPress={handleSubmit} />
    </ScrollView>
  );
}
