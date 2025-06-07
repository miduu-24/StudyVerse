import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";
import { View, Text, ScrollView, StyleSheet, Image } from "react-native";

export default function LessonDetail() {
  const { id } = useLocalSearchParams();
  const [lesson, setLesson] = useState(null);

  useEffect(() => {
    fetch(`http://192.168.100.9:8080/api/lessons/${id}`)
      .then((res) => res.json())
      .then((data) => setLesson(data))
      .catch((err) => console.error("Eroare la preluare lecție:", err));
  }, [id]);

  if (!lesson) {
    return (
      <View style={styles.loading}>
        <Text>Se încarcă lecția...</Text>
      </View>
    );
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>{lesson.title}</Text>
      <Text style={styles.subject}>Materie: {lesson.subject}</Text>
      <Text style={styles.difficulty}>
        Dificultate: {lesson.difficultyLevel}
      </Text>
      {lesson.imageUrl && (
        <Image source={{ uri: lesson.imageUrl }} style={styles.image} />
      )}
      <Text style={styles.description}>{lesson.description}</Text>
      <Text style={styles.content}>{lesson.content}</Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: { padding: 16 },
  loading: { flex: 1, justifyContent: "center", alignItems: "center" },
  title: { fontSize: 24, fontWeight: "bold", marginBottom: 8 },
  subject: { fontSize: 16, marginBottom: 4 },
  difficulty: { fontStyle: "italic", marginBottom: 10 },
  image: { width: "100%", height: 200, borderRadius: 8, marginBottom: 16 },
  description: { fontSize: 16, marginBottom: 8 },
  content: { fontSize: 15, lineHeight: 22 },
});
