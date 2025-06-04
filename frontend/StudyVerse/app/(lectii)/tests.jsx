import { View, Text, StyleSheet, Appearance, Button, Pressable} from 'react-native'
import React from 'react'

import { Colors } from '@/constants/Colors';
import LessonsList from '@/components/LessonsList';

const colorScheme = Appearance.getColorScheme()
  
const theme = colorScheme === 'dark' ? Colors.dark : Colors.light;

export default function Tests() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Teste</Text>
        {/* <LessonsList/> */}
    </View>
  )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: theme.background,
    },
    title: {
        fontSize: 30,
        fontWeight: 'bold',
        color: theme.text,
        textAlign: 'center',
        marginTop: 20,
    },
    })