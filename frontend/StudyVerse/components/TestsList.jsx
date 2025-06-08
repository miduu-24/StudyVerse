import React, { useEffect, useState } from "react";
import { View, Text, FlatList, Pressable, StyleSheet } from "react-native";
import { useRouter } from "expo-router";

export default function TestSelectionScreen({ subject = "matematica" }) {
  const [quizzes, setQuizzes] = useState([]);
  const router = useRouter();

  useEffect(() => {
    fetch(`http://192.168.100.9:8080/api/quizzes/by-subject/${subject}`)
      .then((res) => res.json())
      .then(setQuizzes)
      .catch((err) => console.error(err));
  }, []);

  return (
    <View>
      <Text style={styles.title}>Alege un test la {subject}</Text>
      <FlatList
        data={quizzes}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <Pressable
            style={styles.card}
            onPress={() => router.push(`/test/${item.id}`)}
          >
            <Text style={styles.name}>{item.quizName}</Text>
            <Text>{item.quizDescription}</Text>
          </Pressable>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  title: { fontSize: 24, margin: 16, fontWeight: "bold" },
  card: { padding: 16, backgroundColor: "#eee", margin: 10, borderRadius: 8 },
  name: { fontWeight: "bold", fontSize: 16 },
});
