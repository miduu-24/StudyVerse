import { Link } from "expo-router";
import React, { useEffect, useState } from "react";
import { View, Text, StyleSheet, FlatList, Pressable } from "react-native";

function LessonsList({ type }) {
  const [lessons, setLessons] = useState([]);

  useEffect(() => {
    const queryParam = type ? `${encodeURIComponent(type)}` : "";
    const url = `http://192.168.100.9:8080/api/lessons/subject/${queryParam}`;
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => setLessons(data))
      .catch((err) => console.error("Eroare la preluare lecții:", err, url));
  }, [type]);

  const renderLesson = ({ item }) => (
    <View style={styles.lessonCard}>
      <Link href={`/lessons/${item.id}`} asChild>
        <Pressable>
          <Text style={styles.lessonTitle}>{item.title}</Text>
          <Text>{item.description}</Text>
          <Text style={styles.difficulty}>
            Dificultate: {item.difficultyLevel}
          </Text>
        </Pressable>
      </Link>
    </View>
  );

  return (
    <View>
      <Text style={styles.heading}>Lecții disponibile ({type})</Text>
      <FlatList
        data={lessons}
        keyExtractor={(item) => item.id.toString()}
        renderItem={renderLesson}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  heading: {
    fontSize: 24,
    fontWeight: "bold",
    marginVertical: 10,
    paddingHorizontal: 16,
  },
  lessonCard: {
    borderWidth: 1,
    borderColor: "#ccc",
    marginBottom: 12,
    padding: 16,
    marginHorizontal: 16,
    borderRadius: 8,
    backgroundColor: "#fff",
  },
  lessonTitle: {
    fontSize: 18,
    fontWeight: "600",
    marginBottom: 4,
  },
  difficulty: {
    marginTop: 4,
    fontStyle: "italic",
    color: "#666",
  },
  lessonImage: {
    marginTop: 10,
    height: 150,
    borderRadius: 8,
  },
});

export default LessonsList;
