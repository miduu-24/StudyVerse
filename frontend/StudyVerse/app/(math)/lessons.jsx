import React, { useState } from "react";
import { View } from "react-native"; // pentru versiuni vechi
// Pentru versiunile noi: import { Picker } from '@react-native-picker/picker';
import LessonsList from "../../components/LessonsList"; // Asigură-te că calea este corectă

export default function LessonsScreen() {
  return (
    <View style={{ flex: 1 }}>
      <LessonsList type={"matematica"} />
    </View>
  );
}
