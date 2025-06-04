import { View, Text, StyleSheet, Appearance, Pressable} from 'react-native'
import React from 'react'
import AntDesign from '@expo/vector-icons/AntDesign';
import { Link } from 'expo-router';

import { Colors } from '@/constants/Colors';

const colorScheme = Appearance.getColorScheme()
  
const theme = colorScheme === 'dark' ? Colors.dark : Colors.light;

const App = () => {
  return (
    <View style={styles.container}>
      <AntDesign name="checkcircle" style={styles.icon}/>

      <Text style={styles.title}>Bine ai venit în StudyVerse!</Text>
      
      <Link href="/(lectii)/math_lecture" asChild >
      <Pressable style={styles.button}>
        <Text style={styles.buttonText}>Lecții Matematică</Text>
      </Pressable>
      </Link>

      <Link href="/(lectii)/chim_lecture" asChild >
      <Pressable style={styles.button}>
        <Text style={styles.buttonText}>Lecții Chimie</Text>
      </Pressable>
      </Link>

      <Link href="/(lectii)/tests" asChild >
      <Pressable style={styles.button}>
        <Text style={styles.buttonText}>Teste</Text>
      </Pressable>
      </Link>
    </View>
  )
}

export default App

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: theme.background
  },
  title: {
    fontSize: 35,
    textAlign: 'center',
    fontWeight: 'bold',
    color: theme.text,
    paddingBottom: 20,
  },
  icon: {
    fontSize: 110,
    color: theme.tint,
    textAlign: 'center',
    margin: 20,
    paddingTop: 20,
  },
  button: {
    backgroundColor: theme.button,
    borderColor: theme.tint,
    borderRadius: 5,
    margin: 10,
    padding: 10,
    borderWidth: 1,
    color: theme.text,
    textAlign: 'center',
    width: '80%',
    height: 60,
    alignSelf: 'center',
  },
  buttonText: {
    color: theme.text,
    fontSize: 20,
    textAlign: 'center',
    fontWeight: 'bold',
    marginTop: 5,
  },
})