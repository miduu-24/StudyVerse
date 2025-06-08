import React, { useEffect, useState } from "react";
import { View, Text, Button } from "react-native";
import { Link, useLocalSearchParams, useRouter } from "expo-router";

export default function TestResultScreen() {
  const { userAnswers, quizId, userId } = useLocalSearchParams();
  const [result, setResult] = useState(null);
  const [subjectScore, setSubjectScore] = useState(null);
  const router = useRouter();

  useEffect(() => {
    fetch("http://192.168.100.9:8080/api/results", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        quizId: parseInt(quizId),
        userId: parseInt(userId),
        answers: JSON.parse(userAnswers),
      }),
    })
      .then((res) => res.json())
      .then((data) => {
        setResult(data);

        // ✅ Dacă a luat 10, luăm scorul userului actualizat pentru acel subiect
        if (data.score === 10 && data.subject) {
          fetch(`http://192.168.100.9:8080/api/users/${userId}`)
            .then((res) => res.json())
            .then((user) => {
              const key =
                data.subject.toLowerCase() === "matematica"
                  ? "scoreMath"
                  : data.subject.toLowerCase() === "chimie"
                  ? "scoreChemistry"
                  : null;
              if (key) setSubjectScore(user[key]);
            });
        }
      })
      .catch((err) => console.error("Eroare la trimiterea rezultatului:", err));
  }, []);

  if (!result) return <Text>Se calculează scorul...</Text>;

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 22, fontWeight: "bold" }}>Rezultatul tău:</Text>
      <Text style={{ fontSize: 18, marginTop: 10 }}>
        Scor: {result.score}/10
      </Text>
      <Text style={{ fontSize: 16, marginTop: 10 }}>
        {result.passed ? "✅ Ai trecut testul!" : "❌ Nu ai trecut testul."}
      </Text>

      {subjectScore !== null && (
        <Text style={{ marginTop: 15, fontStyle: "italic" }}>
          🎉 Noul tău scor la {result.subject}: {subjectScore}
        </Text>
      )}

      <Link
        href="/(math)/tests"
        style={{
          marginTop: 20,
          padding: 10,
          backgroundColor: "#007bff",
          color: "#fff",
          textAlign: "center",
          borderRadius: 5,
        }}
      >
        Înapoi la teste
      </Link>
    </View>
  );
}
