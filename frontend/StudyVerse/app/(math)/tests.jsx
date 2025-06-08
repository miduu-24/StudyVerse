import React, { useState } from "react";
import { View } from "react-native"; // pentru versiuni vechi
// Pentru versiunile noi: import { Picker } from '@react-native-picker/picker';
import TestSelectionScreen from "../../components/TestsList"; // Asigură-te că calea este corectă

export default function LessonsScreen() {
  return (
    <View style={{ flex: 1 }}>
      <TestSelectionScreen subject={"matematica"} />
    </View>
  );
}
